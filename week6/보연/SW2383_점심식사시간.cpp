#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

struct Stair {
	int r, c;
	int len; //계단의 길이
	int num; //계단을 오르는 사람의 수
};

struct Person {
	int r, c;
	int move_to_stair; //계단 까지 이동시간
	int start_time; //계단을 내려가는 시작시간
	int end_time; //계단을 다 내려간 시간
	bool is_finish; //내려갔는지 여부
};

int N, ans;
int board[11][11];
//Stair stairs[2];
//Person persons[11];
vector<Stair> stairs;
vector<Person> persons;
vector<int> select;
queue<int> wait_stair[2]; //각 계단 대기줄

void init() {
	ans = 2e9;
	memset(board, 0, sizeof(board));
	stairs.clear();
	persons.clear();
	select.clear();
	wait_stair[0] = queue<int>();
	wait_stair[1] = queue<int>();
}

void input() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> board[i][j];
			if (board[i][j] == 1) {
				persons.push_back({ i, j, 0,0,0,false });
			} if (board[i][j] >= 2) {
				stairs.push_back({ i, j, board[i][j], 0 });
			}
		}
	}
}

int dist(Person p, Stair s) {
	return abs(p.r - s.r) + abs(p.c + s.c);
}

bool is_finish() {
	for (int i = 0; i < persons.size(); i++) {
		if (!persons[i].is_finish) {
			return false;
		}
	}
	return true;
}

void moveStair() {
	for (int i = 0; i < persons.size(); i++) {
		persons[i].move_to_stair = dist(persons[i], stairs[select[i]]);
	}

	int t = 0;
	while (true) {
		//계단을 내려간 사람이 있는지
		for (int i = 0; i < persons.size(); i++) {
			if (persons[i].end_time == t) {
				persons[i].is_finish = true;
				stairs[select[i]].num--;
			}
		}
		//종료조건 : 모든 사람이 다 내려감
		if (is_finish()) break;
		//계단에 도착한 사람
		for (int i = 0; i < persons.size(); i++) {
			if (persons[i].start_time == t) {
				wait_stair[select[i]].push(i);
			}
		}
		//계단 내려가기
		for (int i = 0; i < 2; i++) {
			int stair_num = stairs[i].num; //계단을 내려가고 있는 사람 수
			int wait_len = wait_stair[i].size(); //현재 대기줄 수
			if (stair_num < 3 && wait_len > 0) {
				while (!wait_stair[i].empty()) {
					if (stairs[i].num == 3) break;
					int curr = wait_stair[i].front();
					persons[curr].start_time = t;
					persons[curr].end_time = t + stairs[i].len;
					stairs[i].num++;
					wait_stair[i].pop();
				}
			}
		}
		t++;
	}

	ans = min(ans, t);
}

void solve(int cnt) {
	if (cnt == persons.size()) {
		//N명 선택이 완료 되었음
		for (int i = 0; i < cnt; i++) {
			persons[i].move_to_stair = persons[i].start_time = persons[i].end_time = -1;
			persons[i].is_finish = false;
		}
		wait_stair[0] = queue<int>();
		wait_stair[1] = queue<int>();
		moveStair();
		return;
	}

	for (int i = 0; i < 2; i++) {
		select.push_back(i);
		solve(cnt + 1);
		select.pop_back();
	}

}

int main() {
	int T;
	cin >> T;
	for (int tc = 1; tc <= T; tc++)
	{
		init();
		input();
		solve(0);
	
		cout << "#" << tc << " " << ans << '\n';
	}
	
	return 0;
}