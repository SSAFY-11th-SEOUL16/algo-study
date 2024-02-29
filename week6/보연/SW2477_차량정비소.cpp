#include <iostream>
#include <cstring>
#include <queue>
#include <vector>

using namespace std;

struct Customer {
	int id; //����ȣ
	int arrive; //�����ð�
	int recept_id;
	int repair_id;
	int t; //�ɸ� �ð�
};

struct cmp1 {
	bool operator()(Customer c1, Customer c2) {
		return c1.id > c2.id; //����ȣ ��������
	}
};

struct cmp2 {
	bool operator()(Customer c1, Customer c2) {
		if (c1.t == c2.t) {
			return c1.recept_id > c2.recept_id; //����â����ȣ ��������
		}
		return c1.t > c2.t;
	}
};
//���� â���� ���� N, ���� â���� ���� M, ���� ����Ҹ� �湮�� ���� �� K
//������ �ΰ� �� ���� �̿��� ���� â����ȣ A�� ���� â����ȣ B
int N, M, K, A, B;
int reception[10][2]; //����â�� (�ð�, ��)
int repair[10][2]; //����â��
Customer cust[1001]; //��
//vector<Customer> cust;

bool cmp(Customer c1, Customer c2) {
	return c1.arrive < c2.arrive; //�����ð� ��������
}

void init() {
	memset(reception, 0, sizeof(reception));
	memset(repair, 0, sizeof(repair));
	memset(cust, 0, sizeof(cust));
	//cust.clear();
}

void input() {
	cin >> N >> M >> K >> A >> B;
	for (int i = 0; i < N; i++) {
		cin >> reception[i][0];
	}
	for (int i = 0; i < M; i++) {
		cin >> repair[i][0];
	}
	for (int i = 1; i <= K; i++) {
		int t;
		cin >> t;
		cust[i].id = i;
		cust[i].arrive = t;
		cust[i].recept_id = 0;
		cust[i].repair_id = 0;
		cust[i].t = -1;
	}
}

int solve() {
	vector<int> answer;

	int time = 0; //���� �ð�
	int end = 0; //���� ���� ��
	priority_queue<Customer, vector<Customer>, cmp1> wait_recept;
	priority_queue<Customer, vector<Customer>, cmp2> wait_repair;
	while (true) {
		//��������
		if (end == K) break;

		//�� ����
		for (int i = 1; i <= K; i++) {
			//���� �����
			if (cust[i].arrive > time) break;
			if (cust[i].arrive == time) {
				wait_recept.push(cust[i]);
			}
			
		}
		//���� ��, ���� �������� Ȯ��
		for (int j = 0; j < N; j++) {
			if (reception[j][1] != 0) {
				//���� ������
				if (cust[reception[j][1]].t == time) {
					wait_repair.push(cust[reception[j][1]]);
					reception[j][1] = 0;
				}
			}
		}
		//���� ����
		if (!wait_recept.empty()) {
			for (int j = 0; j < N; j++) {
				if (wait_recept.empty()) break;
				Customer curr = wait_recept.top();
				if (reception[j][1] == 0) { //��â���� ������
					reception[j][1] = curr.id; //�� idx
					cust[curr.id].recept_id = j + 1;
					cust[curr.id].t = time + reception[j][0];
					wait_recept.pop();
				}
			}
		}
		
		//���� ��, ���� �������� Ȯ��
		for (int i = 0; i < M; i++) {
			if (repair[i][1] != 0) {
				if (cust[repair[i][1]].t == time) {
					if (A == cust[repair[i][1]].recept_id && B == cust[repair[i][1]].repair_id) {
						answer.push_back(cust[repair[i][1]].id);
					}
					repair[i][1] = 0;
					end++;
				}
			}
		}

		//�����ϱ�
		if (!wait_repair.empty()) {
			for (int i = 0; i < M; i++) {
				if (wait_repair.empty()) break;
				Customer curr = wait_repair.top();
				if (repair[i][1] == 0) {
					repair[i][1] = curr.id;
					cust[curr.id].repair_id = i + 1;
					cust[curr.id].t = time + repair[i][0];
					wait_repair.pop();
				}
				
			}
		}
		

		time++;
	}

	int sum = 0;
	if (answer.size() == 0) {
		return -1;
	} else {
		for (int i = 0; i < answer.size(); i++) {
			sum += answer[i];
		}
	}
	return sum;
}

int main() {
	int T;
	cin >> T;
	for (int tc = 1; tc <= T; tc++)
	{
		init();
		input();
		int ans = solve();
		cout << "#" << tc << " " << ans << '\n';
	}

	return 0;
}