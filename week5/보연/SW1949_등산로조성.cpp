#include <iostream>
#include <cstring>
#include <vector>

using namespace std;

typedef pair<int, int> ci;

int N, K;
int ans;
int map[9][9];
bool visited[9][9];
vector<ci> high;

int dr[4] = { -1, 0, 1, 0 };
int dc[4] = { 0, 1, 0, -1 };

void dfs(int r, int c, int cnt, bool flag) {
	if (ans < cnt) { //종료 조건 : 더 이상 탐색가능한 곳이 없을 때
		ans = cnt;
	}
	visited[r][c] = true;

	for (int i = 0; i < 4; i++) {
		int nr = r + dr[i];
		int nc = c + dc[i];
		if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
		if (visited[nr][nc]) continue;
		if (map[nr][nc] < map[r][c]) {
			visited[nr][nc] = true;
			dfs(nr, nc, cnt + 1, flag);
			visited[nr][nc] = false;
		}
		else if (map[nr][nc] >= map[r][c]) {
			if (!flag && map[nr][nc] - K < map[r][c]) {
				//등산로를 깎을 수 있음
				int t = map[nr][nc];
				map[nr][nc] = map[r][c] - 1;
				visited[nr][nc] = true;
				dfs(nr, nc, cnt + 1, true);
				map[nr][nc] = t;
				visited[nr][nc] = false;
			}

		}
	}
}

int main() {
	int T;
	cin >> T;
	for (int tc = 1; tc <= T; tc++) {
		cin >> N >> K;
		memset(map, 0, sizeof(map));
		memset(visited, 0, sizeof(visited));
		int MAX = 0;
		ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cin >> map[i][j];
				if (MAX < map[i][j]) MAX = map[i][j];
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (MAX == map[i][j]) high.push_back({ i, j });
			}
		}

		for (int i = 0; i < high.size(); i++) {
			memset(visited, 0, sizeof(visited));
			dfs(high[i].first, high[i].second, 1, false);
		}

		cout << "#" << tc << " " << ans << '\n';
	}

	return 0;
}