#include <iostream>
#include <cstring>
#include <queue>
using namespace std;

typedef pair<int, int> ci;

const int MAX_SIZE = 51;
int N, M, R, C, L, ans;
int board[MAX_SIZE][MAX_SIZE];

int dr[4] = { -1, 0, 1, 0 };
int dc[4] = { 0, 1, 0, -1 };

bool is_connect(int dir, int a, int b) {
	//dir : 0, 1, 2, 3 (상, 우, 하, 좌)

	if (a == 1) {
		if (b == 1) return true;
		if (dir == 0 && (b == 2 || b == 5 || b == 6)) return true;
		if (dir == 1 && (b == 3 || b == 6 || b == 7)) return true;
		if (dir == 2 && (b == 2 || b == 4 || b == 7)) return true;
		if (dir == 3 && (b == 3 || b == 4 || b == 5)) return true;
	}

	if (a == 2) {
		//상, 하 터널
		if (dir == 0 && (b == 1 || b == 2 || b == 5 || b == 6)) return true;
		if (dir == 2 && (b == 1 || b == 2 || b == 4 || b == 7)) return true;
	}

	if (a == 3) {
		//좌, 우 터널
		if (dir == 3 && (b == 1 || b == 3 || b == 4 || b == 5))	return true;
		if (dir == 1 && (b == 1 || b == 3 || b == 6 || b == 7)) return true;
		
	}

	if (a == 4) {
		//상, 우 터널
		if (dir == 0 && (b == 1 || b == 2 || b == 5 || b == 6)) return true;
		if (dir == 1 && (b == 1 || b == 3 || b == 6 || b == 7)) return true;
	}

	if (a == 5) {
		//하, 우 터널
		if (dir == 2 && (b == 1 || b == 2 || b == 4 || b == 7)) return true;
		if (dir == 1 && (b == 1 || b == 3 || b == 6 || b == 7))return true;

	}

	if (a == 6) {
		//하, 좌 터널
		if (dir == 2 && (b == 1 || b == 2 || b == 4 || b == 7)) return true;
		if (dir == 3 && (b == 1 || b == 3 || b == 4 || b == 5)) return true;
	}

	if (a == 7) {
		//상, 좌 터널
		if (dir == 0 && (b == 1 || b == 2 || b == 5 || b == 6)) return true;
		if (dir == 3 && (b == 1 || b == 3 || b == 4 || b == 5)) return true;
	}

	return false;
}

void getPos() {
	queue<ci> que;
	bool visited[MAX_SIZE][MAX_SIZE] = { 0, };
	que.push(make_pair(R, C));
	visited[R][C] = true;
	ans = 0; //맨홀 뚜껑 = 시작점
	int cnt = 1; //시간

	while (!que.empty()) {
		if (cnt == L) {
			break;
		}
		int size = que.size(); //레벨별 탐색을 위한 변수
		cnt++;
		for (int s = 0; s < size; s++) {
			ci curr = que.front();
			que.pop();

			//인접한 노드 넣기
			for (int i = 0; i < 4; i++) {
				int nr = curr.first + dr[i];
				int nc = curr.second + dc[i];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
				if (visited[nr][nc]) continue;
				if (board[nr][nc] == 0) continue;

				//현재 파이프와 연결되어 있는 부분이면 push
				if (is_connect(i, board[curr.first][curr.second], board[nr][nc])) {
					que.push({ nr, nc });
					visited[nr][nc] = true;
				}
			}
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (visited[i][j]) ans++;
		}
	}
}

int main() {
	int T;
	cin >> T;

	for (int tc = 1; tc <= T; tc++) {
		memset(board, 0, sizeof(board));
		ans = 0;
		cin >> N >> M >> R >> C >> L;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				cin >> board[i][j];
			}
		}

		getPos();

		cout << "#" << tc << " " << ans << '\n';
	}

	return 0;
}