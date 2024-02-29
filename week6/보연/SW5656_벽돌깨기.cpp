//#include <iostream>
//#include <cstring>
//#include <vector>
//#include <algorithm>
//#include <queue>
//using namespace std;
//
//typedef pair<int, int> ci;
//
//const int W_SIZE = 13; //열
//const int H_SIZE = 16; //행
//int N, W, H, ans;
//int board[H_SIZE][W_SIZE];
//int copy_board[H_SIZE][W_SIZE];
//vector<int> order;
//int dr[4] = { -1, 0, 1, 0 };
//int dc[4] = { 0, 1, 0, -1 };
//
//void init() {
//	memset(board, 0, sizeof(board));
//	ans = 200;
//}
//
//void input() {
//	cin >> N >> W >> H;
//	for (int i = 0; i < H; i++) {
//		for (int j = 0; j < W; j++)
//		{
//			cin >> board[i][j];
//			copy_board[i][j] = board[i][j];
//		}
//	}
//}
//
//void copyBoard() {
//	for (int i = 0; i < H; i++) {
//		for (int j = 0; j < W; j++)
//		{
//			copy_board[i][j] = board[i][j];
//		}
//	}
//}
//
//void hit(int r, int c) {
//	queue<ci> que;
//	bool visited[H_SIZE][W_SIZE] = { 0, };
//	que.push({ r, c });
//	visited[r][c] = true;
//
//	while (!que.empty()) {
//		ci curr = que.front();
//		que.pop();
//
//		int range = copy_board[curr.first][curr.second];
//		if (range == 1) {
//			copy_board[curr.first][curr.second] = 0;
//			visited[curr.first][curr.second] = true;
//			continue;
//		}
//		for (int d = 0; d < 4; d++) {
//			for (int r = 0; r < range; r++) {
//				int nr = curr.first+ dr[d];
//				int nc = curr.second + dc[d];
//				if (copy_board[nr][nc] != 0) {
//					copy_board[nr][nc] = 0;
//					que.push({ nr, nc });
//				}
//			}
//		}
//	}
//
//}
//
//int cntBrick() {
//	int cnt = 0;
//	for (int i = 0; i < H; i++) {
//		for (int j = 0; j < W; j++)
//		{
//			if (copy_board[i][j] == 0) continue;
//			cnt++;
//		}
//	}
//	return cnt;
//}
//
//void solve() {
//	vector<int> temp(W, 0);
//	for (int i = W - 1; i >= W - N; i--) {
//		temp[i] = 1;
//	}
//
//	do {
//		//떨어뜨릴 N개 선택
//		vector<int> comb;
//		for (int i = 0; i < W; i++) {
//			if (temp[i] == 1) {
//				comb.push_back(i);
//			}
//		}
//
//		do {
//			copyBoard();
//			//벽돌 떨어뜨리기
//			for (int i = 0; i < comb.size(); i++) {
//				for (int j = 0; j < H; j++) {
//					if (copy_board[i][j] > 0) {
//						hit(i, j);
//						break;
//					}
//				}
//			}
//
//			//남은 벽돌 개수 세기
//			int cnt = cntBrick();
//			if (cnt < ans) ans = cnt;
//
//		} while (next_permutation(comb.begin(), comb.end()));
//		
//	} while (next_permutation(temp.begin(), temp.end()));
//}
//
//int main() {
//	int T;
//	cin >> T;
//	for (int tc = 1; tc < T; tc++)
//	{
//		init();
//		input();
//		solve();
//
//		cout << "#" << tc << " " << ans << '\n';
//	}
//
//	return 0;
//}