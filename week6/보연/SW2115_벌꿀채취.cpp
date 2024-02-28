//#define _CRT_SECURE_NO_WARNINGS
//#include <iostream>
//#include <cstring>
//#include <queue>
//
//using namespace std;
//
//const int SIZE = 11;
//int N, M, C, ans;
//int honeycomb[SIZE][SIZE];
//
//void init() {
//	memset(honeycomb, 0, sizeof(honeycomb));
//	ans = -1;
//}
//
//void input() {
//	cin >> N >> M >> C;
//	
//	for (int i = 0; i < N; i++) {
//		for (int j = 0; j < N; j++) {
//			cin >> honeycomb[i][j];
//		}
//	}
//}
//
//void copyMap() {
//	for (int i = 0; i < N; i++) {
//		for (int j = 0; j < N; j++) {
//			cin >> honeycomb[i][j];
//		}
//	}
//}
//
//int solve() {
//	priority_queue<int, vector<int>, greater<int>> pq1, pq2;
//	bool visited[SIZE][SIZE] = { 0, };
//	for (int i = 0; i < N; i++) {
//		//초기화
//		memset(visited, 0, sizeof(visited));
//		for (int s = i; s < i + M; s++) {
//			visited[i][s] = true;
//			pq1.push(honeycomb[i][s]);
//		}
//		for (int j = i + M; j < N; j++) {
//			if (visited[i][j]) continue;
//
//		}
//	}
//}
//
//int main() {
//	int T;
//	cin >> T;
//	for (int tc = 1; tc <= T; tc++) {
//		init();
//		input();
//		solve();
//		cout << "#" << tc << ' ' << ans << '\n';
//	}
//}