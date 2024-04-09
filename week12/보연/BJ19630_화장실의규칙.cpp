#include <iostream>
#include <queue>

using namespace std;

/*

[화장실의 규칙]
1. M개의 줄에서 선두 중 근무 일수가 높은 순서(내림차순)
2. 선두 중 화장실의 가장 급한 정도가 높은 순서 (내림차순)
3. 선두 중 줄의 번호가 낮은 순서 (오름차순)

- 위 규칙대로 우선순위 큐를 선언한다.
- 선두에서 한명씩 우선순위 큐에 넣어 관리한다.
- 주의할 점은, 우선순위 큐에 넣을 때 해당 줄이 비었는지 확인해야한다.
- 선두는 큐 배열을 이용한다.

*/

struct Emp {
	int idx; //줄을 선 곳
	int d; // 근무 일수
	int h; // 화장실 급한 정도
	bool is_deka = false;

	bool operator<(const Emp& other) const {
		if (d == other.d) {
			if (h == other.h) {
				return idx > other.idx;
			}
			return h < other.h;
		}
		return d < other.d;
	}
};

const int SIZE = 100001;
int N, M, K;
Emp arr[SIZE];
queue<Emp> que[SIZE];

void input() {
	cin >> N >> M >> K;

	for (int i = 0; i < N; i++) {
		Emp tmp;
		int d, h;
		cin >> d >> h;
		bool is_deka = false;
		if (i == K) {
			is_deka = true;
		}
		tmp.d = d;
		tmp.h = h;
		tmp.is_deka = is_deka;
		tmp.idx = i % M + 1;
		//M개의 줄로 나누기
		que[i % M + 1].push(tmp);
	}
}

void solve() {
	/*
	int line = N / M;
	int id = 1;
	int cnt = 1;
	for (int i = 0; i < N; i++) {
		if (id == M + 1) {
			id = 1;
		}
		arr[i].idx = id++;
		que[arr[i].idx].push(arr[i]);
	}

	for (int i = 0; i < N; i++) {
		que[i % M].push(arr[i]);
	}
	*/
	//선두를 저장할 우선순위 큐
	priority_queue<Emp> pq;
	for (int i = 1; i <= M; i++) {
		if (!que[i].empty()) {
			Emp curr = que[i].front();
			que[i].pop();
			pq.push(curr);
		}
	}

	int cnt = 0;
	while (!pq.empty()) {
		Emp curr = pq.top();
		pq.pop();
		if (curr.is_deka) {
			cout << cnt;
			break;
		}
		cnt++;
		if (!que[curr.idx].empty()) {
			Emp next = que[curr.idx].front();
			que[curr.idx].pop();
			pq.push(next);
		}

	}

}

int main()
{
	input();
	solve();

	return 0;
}
