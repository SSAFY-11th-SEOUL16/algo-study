import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * JAVA
 * 132 ms
 * 
 * DP
 * 
 * dp[i][j]를 앱 i까지 고려했을 때 비용 j로 확보할 수 있는 최대 메모리
 */
public class BJ7579_앱 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int result = Integer.MAX_VALUE;
		int[] memory = new int[N + 1];
		int[] cost = new int[N + 1];
		int[][] dp = new int[N + 1][10001];

		st = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
			cost[i] = Integer.parseInt(st2.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			int costVal = cost[i];
			int memoryVal = memory[i];

			for (int j = 0; j <= 10000; j++) {
				if (i == 0) {
					if (j >= costVal)
						dp[i][j] = memoryVal;
				} else {
					if (j >= costVal) {
						int tmp1 = dp[i - 1][j - costVal] + memoryVal;
						int tmp2 = dp[i - 1][j];
						dp[i][j] = Math.max(tmp1, tmp2);
					} else {
						dp[i][j] = dp[i - 1][j];
					}
					if (dp[i][j] >= M) {
						result = Math.min(result, j);
					}
				}
			}
		}
		System.out.println(result);
	}
}
