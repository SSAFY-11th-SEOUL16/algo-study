import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1890_점프 {
	private static int[][] board;
	private static long[][] dp;
	
	private static long count(int x, int y) {
		int i;
		
		if (dp[x][y] != -1) {
			return dp[x][y];
		}
		dp[x][y] = 0;
		for (i = x - 1; i >= 0; i--) {
			if (board[i][y] == x - i) {
				dp[x][y] += count(i, y);
			}
		}
		for (i = y - 1; i >= 0; i--) {
			if (board[x][i] == y - i) {
				dp[x][y] += count(x, i);
			}
		}
		return dp[x][y];
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, i, j;
		
		n = Integer.parseInt(br.readLine());
		board = new int[n][n];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new long[n][n];
		for (i = 0; i < n; i++) {
			Arrays.fill(dp[i], -1);
		}
		dp[0][0] = 1;
		System.out.println(count(n - 1, n - 1));
	}
}