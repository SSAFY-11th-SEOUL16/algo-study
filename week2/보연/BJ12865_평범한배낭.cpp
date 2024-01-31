#include <iostream>
#include <cstring>
#include <algorithm>
using namespace std;

struct Obj {
	int w, v;
};

int N, K, ans;
Obj objs[101];

int main() {
	cin >> N >> K;
	ans = -1;
	int dp[101][100001];

	memset(dp, 0, sizeof(dp));

	for (int i = 1; i <= N; i++) {
		cin >> objs[i].w >> objs[i].v;
	}

	for (int i = 0; i <= N; i++) {
		dp[i][0] = 0;
	}

	for (int i = 0; i <= K; i++) {
		dp[0][i] = 0;
	}

	for (int i = 1; i <= K; i++) { // 담을 수 있는 무게
		for (int j = 1; j <= N; j++) { // 물품 수
			if (objs[j].w <= i) {
				//현재 물품을 담을 수 있으면
				dp[j][i] = max(objs[j].v + dp[j - 1][i - objs[j].w], dp[j - 1][i]);
			}
			else {
				dp[j][i] = dp[j - 1][i];
			}

		}
	}
	cout << dp[N][K];

	return 0;
}
//
//import java.util.Scanner;
//
//class Obj {
//	int w, v;
//}
//
//public class Main {
//	static int N, K, ans;
//	static Obj[] objs = new Obj[101];
//
//	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//
//		N = scanner.nextInt();
//		K = scanner.nextInt();
//		ans = -1;
//		int[][] dp = new int[101][100001];
//
//		for (int i = 1; i <= N; i++) {
//			objs[i] = new Obj();
//			objs[i].w = scanner.nextInt();
//			objs[i].v = scanner.nextInt();
//		}
//
//		for (int i = 0; i <= N; i++) {
//			dp[i][0] = 0;
//		}
//
//		for (int i = 0; i <= K; i++) {
//			dp[0][i] = 0;
//		}
//
//		for (int i = 1; i <= K; i++) { // 담을 수 있는 무게
//			for (int j = 1; j <= N; j++) { // 물품 수
//				if (objs[j].w <= i) {
//					// 현재 물품을 담을 수 있으면
//					dp[j][i] = Math.max(objs[j].v + dp[j - 1][i - objs[j].w], dp[j - 1][i]);
//				}
//				else {
//					dp[j][i] = dp[j - 1][i];
//				}
//			}
//		}
//
//		System.out.println(dp[N][K]);
//	}
//}
