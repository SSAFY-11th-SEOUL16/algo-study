#include <iostream>
#include <cstring>
using namespace std;

const int SIZE = 100001;
int N, cnt;
int friends[SIZE];
bool is_team[SIZE];
bool visited[SIZE];

void input() {
	cin >> N;
	memset(friends, 0, sizeof(friends));
	memset(visited, 0, sizeof(visited));
	memset(is_team, 0, sizeof(is_team));
	cnt = 0; //싸이클 개수
	for (int i = 1; i <= N; i++) {
		cin >> friends[i];
	}
}

void dfs(int curr) {
	visited[curr] = true;
	int next = friends[curr];
	if (!visited[next]) {
		dfs(next);
	}
	else if (!is_team[next]) {
		//싸이클
		for (int node = next; node != curr; node = friends[node]) {
			cnt++;
			is_team[node] = true;
		}
		cnt++;
	}
	is_team[curr] = true;
}

void solve() {
	for (int i = 1; i <= N; i++) {
		if (visited[i]) continue;
		dfs(i);
	}
	cout << N - cnt << '\n';
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);
	int T;
	cin >> T;
	for (int tc = 1; tc <= T; tc++) {
		input();
		solve();
	}

	return 0;
}
