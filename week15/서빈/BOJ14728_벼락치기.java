import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14728_벼락치기 {
	static int N, T;
	static int[][] dp;
	static int[][] exam;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		dp = new int[N + 1][T + 1];
		exam = new int[N + 1][2];

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			exam[i][0] = Integer.parseInt(st.nextToken());
			exam[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < T + 1; j++) {
				dp[i][j] = dp[i - 1][j];
				if (j - exam[i][0] >= 0)
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - exam[i][0]] + exam[i][1]);
			}
		}
		System.out.println(dp[N][T]);
	}
}