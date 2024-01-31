import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	static int N, K;
	static int[] W;
	static int[] V;
	static int[][] dp; // dp[i][w] 무게가 w인 가방에서 i번째 물건까지 판단했을 때의 최대 가치

	public int knapsack(int i, int k) {
		if (i < 0)
			return 0;
		if (dp[i][k] == -1) {
			if (W[i] > k)
				dp[i][k] = knapsack(i - 1, k); // 이전 i값 저장
			else {
				dp[i][k] = Math.max(knapsack(i - 1, k), knapsack(i - 1, k - W[i]) + V[i]);
			}
		}
		return dp[i][k];
	}

	public static void main(String[] args) throws IOException {
		Main M = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[N][K + 1];
		for (int[] col : dp) {
			Arrays.fill(col, -1);
		}
		W = new int[N];
		V = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());

		}

		System.out.println(M.knapsack(N - 1, K));
	}
}