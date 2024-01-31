import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1149_RGB거리 {
	private static int[][] cost, dp;
	
	private static int getDp(int x, int y) {
		if (dp[x][y] != 0) {
			return dp[x][y];
		}
		dp[x][y] = Math.min(getDp(x - 1, (y + 1) % 3), getDp(x - 1, (y + 2) % 3)) + cost[x][y];
		return dp[x][y];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, i;
		
		n = Integer.parseInt(br.readLine());
		cost = new int[n][3];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = Integer.parseInt(st.nextToken());
			cost[i][2] = Integer.parseInt(st.nextToken());
		}
		dp = new int[n][3];
		dp[0] = cost[0];
		System.out.print(Math.min(getDp(n - 1, 0), Math.min(getDp(n - 1, 1), getDp(n - 1, 2))));
	}
}
