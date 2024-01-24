package week1.지환;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1890_점프 {
	static int n;
	static int[][] board;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		board = new int[n][n];
		StringTokenizer st;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		long[][] dp = new long[n][n];
		dp[0][board[0][0]] = 1;
		dp[board[0][0]][0] = 1;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(i==n-1 && j==n-1) {
					break;
				}
				int next = board[i][j];
				if(dp[i][j] > 0) {
					if(checkRange(i, j+next)) {
						dp[i][j+next] += dp[i][j];
					}
					if(checkRange(i+next, j)) {
						dp[i+next][j] += dp[i][j];
					}
				}
			}
		}
		System.out.println(dp[n-1][n-1]);
	}

	public static boolean checkRange(int x, int y) {
		return x>=0 && x<n && y>=0 && y<n;
	}
}
