#include <iostream>
#include <cstring>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

typedef pair<int, int> ci;

const int SIZE = 10001;
int N, ans;
int in_degrees[SIZE];
int work_time[SIZE];
int dp[SIZE];
vector<int> graph[SIZE];

// 212ms

void init() {
	memset(in_degrees, 0, sizeof(in_degrees));
	memset(work_time, 0, sizeof(work_time));
	memset(dp, 0, sizeof(dp));
}

void input() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		int weight, num;
		cin >> weight >> num;
		work_time[i + 1] = weight; //현재 작업의 소요 시간
		in_degrees[i + 1] = num; //현재 작업의 선행 작업 수
		for (int j = 0; j < num; j++) {
			int n;
			cin >> n;
			graph[n].push_back(i + 1);
		}
	}
}

void solve() {
	queue<int> que;
	for (int i = 0; i < N; i++) {
		if (in_degrees[i + 1] == 0) {
			que.push(i + 1);
			dp[i + 1] = work_time[i + 1];
		}
	}

	while (!que.empty()) {
		int curr = que.front();
		que.pop();

		for (int i = 0; i < graph[curr].size(); i++) {
			int next = graph[curr][i];
			in_degrees[next]--;
			dp[next] = max(dp[curr] + work_time[next], dp[next]);
			if (in_degrees[next] == 0) {
				que.push(next);
			}
		}
	}

	for (int i = 1; i <= N; i++) {
		if (ans < dp[i]) {
			ans = dp[i];
		}
	}
}

int main()
{
	init();
	input();
	solve();
	cout << ans;
	return 0;
}
