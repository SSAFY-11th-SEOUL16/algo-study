#include <iostream>
#include <vector>
#include <cstring>
#include <queue>
#include <algorithm>

using namespace std;

const int SIZE = 501;
int N;
vector<int> node_list[SIZE];
int count_node[SIZE];
int build_time[SIZE];
int result[SIZE];
queue<int> que;

void input() {
	cin >> N;
	for (int i = 1; i <= N; i++) {
		int t;
		cin >> t;
		build_time[i] = t;

		cin >> t;
		while (t != -1) {
			node_list[t].push_back(i);
			count_node[i]++;
			cin >> t;
		}

	}
}

void solve() {
	for (int i = 1; i <= N; i++) {
		if (count_node[i] == 0) {
			result[i] = build_time[i];
			que.push(i);
		}
	}

	while (!que.empty()) {
		int curr = que.front();
		que.pop();

		for (int i = 0; i < node_list[curr].size(); i++) {
			int next = node_list[curr][i];
			result[next] = max(result[next], result[curr] + build_time[next]);
			count_node[next]--;
			if (count_node[next] == 0) {
				que.push(next);
			}
		}
	}

	for (int i = 1; i <= N; i++) {
		cout << result[i] << '\n';
	}
}

int main() {
	input();
	solve();
	return 0;
}
