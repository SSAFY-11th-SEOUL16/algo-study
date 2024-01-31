import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static int N;
	static int[][] dp; // dp[i번째집][색] 일 때의 최소 비용
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		Main M = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1][3];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int G = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + R;
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + G;
			dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + B;
		}

		int result = Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2]));

		System.out.println(result);
	}
}