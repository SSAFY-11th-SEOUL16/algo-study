#include <iostream>
#include <cstring>
#include <vector>
#include <map>

using namespace std;

typedef pair<int, int> ci;
const int MAX_SIZE = 101;

int T, N, ans;
int board[MAX_SIZE][MAX_SIZE];
map<int, vector<ci>> wormhole;

int dr[4] = { -1, 0, 1, 0 }; //상 우 하 좌 (시계)
int dc[4] = { 0, 1, 0, -1 };
int start_r, start_c;
int MAX = -1;

void dfs(ci start, int r, int c, int dir) {
	//현재 출발점에서부터 상하좌우로 날려 dfs탐색
	//다음 위치
	int nr = r + dr[dir];
	int nc = c + dc[dir];

	if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
		//벽을 부딪히면
		dir = (dir + 2) % 4; //정반대로
		ans++;
 		dfs(start, nr, nc, dir);

		return;
	}

	if (board[nr][nc] == -1 || nr == start.first && nc == start.second) {
		//출발 위치로 돌아오면 종료
		//블랙홀을 만나면 종료
		if (MAX < ans) {
			MAX = ans;
		}
		return;
	}


	if (board[nr][nc] == 0) {
		dfs(start, nr, nc, dir);
		return;
	}

	if (board[nr][nc] == 1) {
		switch (dir){
		case 0:
			dir = (dir + 2) % 4;
			break;
		case 1:
			dir = (dir + 2) % 4;
			break;
		case 2:
			dir = 1;
			break;
		case 3:
			dir = 0;
			break;
		}
		ans++;
		dfs(start, nr, nc, dir);
		return;
	}

	if (board[nr][nc] == 2) {
		switch (dir) {
		case 0: //상
			dir = 1;
			break;
		case 1: //우
			dir = (dir + 2) % 4;
			break;
		case 2: //하
			dir = (dir + 2) % 4;
			break;
		case 3: //좌
			dir = 2;
			break;
		}
		ans++;
		dfs(start, nr, nc, dir);
		return;
	}
	
	if (board[nr][nc] == 3) {
		switch (dir) {
		case 0: //상
			dir = 3;
			break;
		case 1: //우
			dir = 2;
			break;
		case 2: //하
			dir = (dir + 2) % 4;
			break;
		case 3: //좌
			dir = (dir + 2) % 4;
			break;
		}
		ans++;
		dfs(start, nr, nc, dir);
		return;
	}

	if (board[nr][nc] == 4) {
		switch (dir) {
		case 0: //상
			dir = (dir + 2) % 4;
			break;
		case 1: //우
			dir = 0;
			break;
		case 2: //하
			dir = 3;
			break;
		case 3: //좌
			dir = (dir + 2) % 4;
			break;
		}
		ans++;
		dfs(start, nr, nc, dir);
		return;
	}

	if (board[nr][nc] == 5) {
		dir = (dir + 2) % 4;
		ans++;
		dfs(start, nr, nc, dir);
		return;
	}

	if (board[nr][nc] >= 6 && board[nr][nc] <= 10) {
		//웜홀을 만나면
		vector<ci> curr = wormhole[board[nr][nc]];
		
		if (curr[0].first == nr && curr[0].second == nc) {
			nr = curr[1].first;
			nc = curr[1].second;
			//dfs(start, nr, nc, dir);
			//return;
		} 
		else if (curr[1].first == nr && curr[1].second == nc) {
			nr = curr[0].first;
			nc = curr[0].second;
		}

		dfs(start, nr, nc, dir);
		return;
	}
}

void game() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (board[i][j] != 0) continue;
			ci start = make_pair(i, j);
			for (int d = 0; d < 4; d++) {
				ans = 0;
 				dfs(start, i, j, d);
			}
		}
	}
}

int main() {
	cin >> T; 
	for (int tc = 1; tc <= T; tc++) {
		cin >> N;
		ans = 0;
		MAX = -1;
		memset(board, 0, sizeof(board));
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cin >> board[i][j];
				if (board[i][j] >= 6 && board[i][j] <= 10) {
					wormhole[board[i][j]].push_back(make_pair(i, j));
				}
			}
		}

		game();

		cout << "#" << tc << " " << MAX << '\n';
	}

	return 0;
}