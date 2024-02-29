//#include <iostream>
//#include <cstring>
//using namespace std;
//
//int D, W, K, ans;
//int film[14][21];
//int copy_film[14][21];
//
//void init() {
//	ans = 0;
//	memset(film, 0, sizeof(film));
//}
//
//void input() {
//	cin >> D >> W >> K;
//	for (int i = 0; i < D; i++) {
//		for (int j = 0; j < W; j++)
//		{
//			cin >> film[i][j];
//			copy_film[i][j] = film[i][j];
//		}
//	}
//}
//
//bool check(int arr[][21]) {
//	for (int i = 0; i < W; i++) {
//		int a_cnt = 0;
//		int b_cnt = 0;
//		bool flag = false;
//
//		for (int j = 0; j < D; j++) {
//			if (arr[i][j] == 0) {
//				b_cnt = 0;
//				a_cnt++;
//			}
//
//			if (arr[i][j] == 1) {
//				a_cnt = 0;
//				b_cnt++;
//			}
//		}
//
//		if (a_cnt == K || b_cnt == K) {
//			flag = true;
//			break; // K개 이상이면 종료
//		}
//		//한 열이라도 false라면
//		if (!flag) return false;
//	}
//
//	return true;
//}
//
//void dfs(int cnt, int idx) {
//
//}
//
//void solve() {
//	//각 열을 check하면서 연속된 K개가 있는지 확인
//	if (check(film) || K == 1) {
//		ans = 0;
//		return;
//	}
//
//	//약품을 뿌려보기 (부분집합)
//	dfs(0, 0);
//}
//
//int main() {
//	int T;
//	cin >> T;
//	for (int tc = 1; tc <= T; tc++) {
//		init();
//		input();
//		solve();
//		cout << "#" << tc << " " << ans << '\n';
//	}
//}