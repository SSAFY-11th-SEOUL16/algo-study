#include<iostream>
#include <cstring>

using namespace std;

const int MAX_SIZE = 1025;
int N, M;
int ans;
int map[MAX_SIZE][MAX_SIZE];
int dp[MAX_SIZE][MAX_SIZE];

void calcSum(int x, int y) {
	dp[1][1] = map[1][1];
	for (int i = 2; i <= y; i++) {
		dp[1][i] = dp[1][i - 1] + map[1][i];
	}

	for (int i = 2; i <= x; i++) {
		dp[i][1] = dp[i - 1][1] + map[i][1];
	}


	for (int i = 2; i <= x; i++) {
		for (int j = 2; j <= y; j++) {
			dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1] + map[i][j];
		}
	}
}

int findSum(int x1, int y1, int x2, int y2) {
	if (x1 == x2 && y1 == y2) {
		return map[x1][y1];
	}
	int sum = dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1];
	return sum;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(0);

	cin >> N >> M;
	memset(map, 0, sizeof(map));
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			cin >> map[i][j];
		}
	}

	calcSum(N, N);

	for (int i = 0; i < M; i++) {
		int x1, y1, x2, y2;
		cin >> x1 >> y1 >> x2 >> y2;
		cout << findSum(x1, y1, x2, y2) << '\n';

	}

	return 0;
}

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//public class Main {
//	static final int MAX_SIZE = 1025;
//	static int N, M;
//	static int ans;
//	static int[][] map = new int[MAX_SIZE][MAX_SIZE];
//	static int[][] dp = new int[MAX_SIZE][MAX_SIZE];
//
//	static void calcSum(int x, int y) {
//		dp[1][1] = map[1][1];
//		for (int i = 2; i <= y; i++) {
//			dp[1][i] = dp[1][i - 1] + map[1][i];
//		}
//
//		for (int i = 2; i <= x; i++) {
//			dp[i][1] = dp[i - 1][1] + map[i][1];
//		}
//
//		for (int i = 2; i <= x; i++) {
//			for (int j = 2; j <= y; j++) {
//				dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1] + map[i][j];
//			}
//		}
//	}
//
//	static int findSum(int x1, int y1, int x2, int y2) {
//		if (x1 == x2 && y1 == y2) {
//			return map[x1][y1];
//		}
//		int sum = dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1];
//		return sum;
//	}
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//
//		N = Integer.parseInt(st.nextToken());
//		M = Integer.parseInt(st.nextToken());
//
//		for (int i = 1; i <= N; i++) {
//			st = new StringTokenizer(br.readLine());
//			for (int j = 1; j <= N; j++) {
//				map[i][j] = Integer.parseInt(st.nextToken());
//			}
//		}
//
//		calcSum(N, N);
//
//		for (int i = 0; i < M; i++) {
//			st = new StringTokenizer(br.readLine());
//			int x1 = Integer.parseInt(st.nextToken());
//			int y1 = Integer.parseInt(st.nextToken());
//			int x2 = Integer.parseInt(st.nextToken());
//			int y2 = Integer.parseInt(st.nextToken());
//
//			System.out.println(findSum(x1, y1, x2, y2));
//		}
//	}
//}
