#include <iostream>
#include <cstring>
#include <vector>
#include <string>

using namespace std;
typedef pair<int, int> ci;

int N, M;
int MAX = -1; //최대한 많은 곡
int MIN = 20; //기타 최소 개수
bool songs[11][51];
int is_selected[51];
int ans;

void getMaxGuitar(int cnt, bool flag) {
	if (cnt == N) {
		//모든 기타의 부분집합을 구했다면
		if (flag) { //공집합 제외
			bool sum[51] = { 0, };
			//or 연산 후 MAX값 갱신
			int guitar_num = 0;
			for (int i = 0; i < N; i++) {
				if (is_selected[i]) {
					//선택된 기타라면
					for (int j = 0; j < M; j++) {
						sum[j] |= songs[i][j];
					}
					guitar_num++;
				}
			}

			//곡의 개수
			int cnt = 0;
			for (int i = 0; i < M; i++) {
				if (sum[i]) cnt++;
			}
			if (MAX <= cnt) {
				MAX = cnt;
				if (MIN > guitar_num)
					MIN = guitar_num;
			}
			if (cnt == 0) {
				ans = -1;
			}
		}
		return;
	}

	is_selected[cnt] = true;
	getMaxGuitar(cnt + 1, true);
	is_selected[cnt] = false;
	getMaxGuitar(cnt + 1, flag);
}

int main() {
	memset(songs, 0, sizeof(songs));
	memset(is_selected, 0, sizeof(is_selected));
	cin >> N >> M;
	bool is_possible = false;
	for (int i = 0; i < N; i++) {
		string name, song;
		cin >> name >> song;
		//cout << name << " : ";
		for (int j = 0; j < M; j++) {
			if (song[j] == 'Y') {
				songs[i][j] = true;
				is_possible = true;
			}
			if (song[j] == 'N') songs[i][j] = false;

			//cout << songs[i][j] << ' ';
		}
		//cout << '\n';
	}
	ans = 11; //기타 최소 개수

	if (!is_possible) {
		cout << "-1";
		return 0;
	}

	//부분집합 구하기
	getMaxGuitar(0, 0);

	if (MIN > 0) ans = MIN;
	cout << ans;

	return 0;
}

//import java.util.Scanner;
//
//public class Main {
//	static int N, M;
//	static int MAX = -1; // 최대한 많은 곡
//	static int MIN = 20; // 기타 최소 개수
//	static boolean[][] songs;
//	static boolean[] is_selected;
//	static int ans;
//
//	public static void getMaxGuitar(int cnt, boolean flag) {
//		if (cnt == N) {
//			// 모든 기타의 부분집합을 구했다면
//			if (flag) { // 공집합 제외
//				boolean[] sum = new boolean[M];
//				// or 연산 후 MAX값 갱신
//				int guitar_num = 0;
//				for (int i = 0; i < N; i++) {
//					if (is_selected[i]) {
//						// 선택된 기타라면
//						for (int j = 0; j < M; j++) {
//							sum[j] |= songs[i][j];
//						}
//						guitar_num++;
//					}
//				}
//
//				// 곡의 개수
//				int cnt = 0;
//				for (boolean b : sum) {
//					if (b) cnt++;
//				}
//				if (MAX <= cnt) {
//					MAX = cnt;
//					if (MIN > guitar_num)
//						MIN = guitar_num;
//				}
//				if (cnt == 0) {
//					ans = -1;
//				}
//			}
//			return;
//		}
//
//		is_selected[cnt] = true;
//		getMaxGuitar(cnt + 1, true);
//		is_selected[cnt] = false;
//		getMaxGuitar(cnt + 1, flag);
//	}
//
//	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//		N = scanner.nextInt();
//		M = scanner.nextInt();
//		songs = new boolean[N][M];
//		is_selected = new boolean[N];
//		boolean is_possible = false;
//
//		for (int i = 0; i < N; i++) {
//			String name = scanner.next();
//			String song = scanner.next();
//			for (int j = 0; j < M; j++) {
//				if (song.charAt(j) == 'Y') {
//					songs[i][j] = true;
//					is_possible = true;
//				}
//				else {
//					songs[i][j] = false;
//				}
//			}
//		}
//		ans = 11; // 기타 최소 개수
//
//		if (!is_possible) {
//			System.out.println("-1");
//			return;
//		}
//
//		// 부분집합 구하기
//		getMaxGuitar(0, false);
//
//		if (MIN > 0) ans = MIN;
//		System.out.println(ans);
//	}
//}
