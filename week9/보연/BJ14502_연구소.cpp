#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

typedef pair<int, int> ci;

int N, M, ans;
int board[8][8];
int copy_board[8][8];
vector<ci> cand;
vector<ci> virus;
int dr[] = { -1,0,1,0 };
int dc[] = { 0,1,0,-1 };
int cand_num;
int virus_cnt;

// 40ms

void input() {
	cin >> N >> M;
	cand_num = 0;
	ans = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> board[i][j];
			copy_board[i][j] = board[i][j];
			if (board[i][j] == 0) {
				cand.push_back({ i, j });
				cand_num++;
			}
			if (board[i][j] == 2) {
				virus.push_back({ i, j });
				virus_cnt++;
			}
		}
	}
}

void printBoard() {
	cout << "----------------------" << '\n';
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cout << copy_board[i][j] << ' ';
		}
		cout << '\n';
	}
}

void copyBoard() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			copy_board[i][j] = board[i][j];
		}
	}
}

void bfs() {
	queue<ci> que;
	bool visited[8][8] = { 0, };
	for (int i = 0; i < virus_cnt; i++) {
		que.push(virus[i]);
		visited[virus[i].first][virus[i].second] = true;
	}

	while (!que.empty()) {
		ci curr = que.front();
		que.pop();

		for (int i = 0; i < 4; i++) {
			int nr = curr.first + dr[i];
			int nc = curr.second + dc[i];
			if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
			if (visited[nr][nc]) continue;
			if (copy_board[nr][nc] != 0) continue;
			visited[nr][nc] = true;
			copy_board[nr][nc] = 2;
			que.push({ nr, nc });
		}
	}


}

int cntSafe() {
	int cnt = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (copy_board[i][j] != 0) continue;
			cnt++;
		}
	}
	return cnt;
}

void solve() {
	//벽을 세우는 조합 cand_num C 3
	vector<int> temp;
	temp.resize(cand_num);
	for (int i = cand_num - 1; i >= cand_num - 3; i--) {
		temp[i] = 1;
	}
	do {
		//맵 초기화
		copyBoard();

		for (int i = 0; i < cand_num; i++) {
			if (temp[i]) {
				ci curr = cand[i];
				copy_board[curr.first][curr.second] = 3;
			}
		}
		//BFS 탐색
		bfs();
		//안전 영역의 크기 구하기
		int cnt = cntSafe();
		if (cnt > ans) ans = cnt;
	} while (next_permutation(temp.begin(), temp.end()));

}

int main()
{
	input();
	solve();
	cout << ans;
	return 0;
}
