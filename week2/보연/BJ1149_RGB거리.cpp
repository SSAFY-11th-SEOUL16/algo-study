#include <iostream>
#include <cstring>
#include <algorithm>

using namespace std;

int main() {
	int N, ans;
	cin >> N;
	ans = 0;

	int house[1001][1001];
	int cost[3];

	memset(house, 0, sizeof(house));
	house[0][0] = 0;
	house[0][1] = 0;
	house[0][2] = 0;

	for (int i = 1; i <= N; i++) {
		cin >> cost[0] >> cost[1] >> cost[2]; //R,G,B 비용
		house[i][0] = min(house[i - 1][1], house[i - 1][2]) + cost[0]; //R을 칠할때 최소비용
		house[i][1] = min(house[i - 1][0], house[i - 1][2]) + cost[1]; //G을 칠할때 최소비용
		house[i][2] = min(house[i - 1][0], house[i - 1][1]) + cost[2]; //B을 칠할때 최소비용
	}

	ans = min(house[N][0], min(house[N][1], house[N][2]));

	cout << ans;

	return 0;
}

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
