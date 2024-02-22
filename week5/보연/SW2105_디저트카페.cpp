#include <iostream>
#include <cstring>
using namespace std;

typedef pair<int, int> ci;

const int MAX_SIZE = 21;
int N, ans;
int board[MAX_SIZE][MAX_SIZE];

int dr[4] = { 1, 1, -1, -1 };
int dc[4] = { 1, -1, -1, 1 };

bool visited[101];
//start:시작점, r,c: 현재위치, cnt:방문한디저트가게, size:사각형선분, dir:현재 진행방향
void dfs(ci start, int r, int c, int cnt, int size, int dir) {
	if (size > 4) return;

	if (size == 4) {
		if (start.first == r && start.second == c) {
			if (cnt > ans) ans = cnt;
			return;
		}

	}

	int nr = r + dr[dir];
	int nc = c + dc[dir];
	if (visited[board[nr][nc]]) return;
	if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
		dir = (dir + 1) % 4;
		nr = r + dr[dir];
		nc = c + dc[dir];
		dfs(start, nr, nc, cnt + 1, size + 1, dir);
	}

	visited[board[nr][nc]] = true;
	dfs(start, nr, nc, cnt+1, size, dir);
	visited[board[nr][nc]] = false;

}

int main() {
	int T;
	cin >> T;
	for (int tc = 1; tc <= T; tc++) {
		cin >> N;
		ans = -1;
		memset(board, 0, sizeof(board));
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cin >> board[i][j];
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N-1; j++) {
				memset(visited, 0, sizeof(visited));
				visited[board[i][j]] = true;

				int nr = i + dr[0];
				int nc = j + dc[0];
				if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				if (visited[board[nr][nc]]) continue;
				visited[board[nr][nc]] = true;
				dfs(make_pair(i, j), i+dr[0], j+dc[0], 1, 1, 0);
				visited[board[nr][nc]] = false;

				visited[board[i][j]] = false;
			}
		}

		if (ans == 1) ans = -1;

		cout << "#" << tc << " " << ans << '\n';
	}

	return 0;
}