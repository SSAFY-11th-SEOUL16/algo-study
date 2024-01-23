#include <iostream>
#include <cstring>
using namespace std;

typedef long long ll;

const int MAX_SIZE = 101;
int board[MAX_SIZE][MAX_SIZE];
ll dp[MAX_SIZE][MAX_SIZE];
int N;
ll ans;

void simulation() {
	dp[0][0] = 1;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (dp[i][j] == 0) continue;
			if (i == (N - 1) && j == (N - 1)) break;
			int dist = board[i][j]; //점프를 뛸 수 있는 거리

			if (i + dist < N) dp[i + dist][j] += dp[i][j];
			if (j + dist < N) dp[i][j + dist] += dp[i][j];
		}
	}
}

int main() {
	cin >> N;
	memset(board, 0, sizeof(board));
	memset(dp, 0, sizeof(dp));
	ans = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> board[i][j];

		}
	}

	simulation();
	ans = dp[N - 1][N - 1];
	cout << ans;

	return 0;
}

//import java.util.Scanner;

//public class Main {
//	static final int MAX_SIZE = 101;
//	static int[][] board = new int[MAX_SIZE][MAX_SIZE];
//	static long[][] dp = new long[MAX_SIZE][MAX_SIZE];
//	static int N;
//	static long ans;
//
//	static void simulation() {
//		dp[0][0] = 1;
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				if (dp[i][j] == 0) continue;
//				if (i == (N - 1) && j == (N - 1)) break;
//				int dist = board[i][j]; // 점프를 뛸 수 있는 거리
//
//				if (i + dist < N) dp[i + dist][j] += dp[i][j];
//				if (j + dist < N) dp[i][j + dist] += dp[i][j];
//			}
//		}
//	}
//
//	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//
//		N = scanner.nextInt();
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				board[i][j] = scanner.nextInt();
//			}
//		}
//
//		simulation();
//		ans = dp[N - 1][N - 1];
//		System.out.println(ans);
//	}
//}
