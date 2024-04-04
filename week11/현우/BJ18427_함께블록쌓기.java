import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ18427_함께블록쌓기 {
	
	/**
	 * 104 ms
	 * Knapsack
	 * dp[i]에 dp[i - 블록 높이]를 더하면서 경우의 수 탐색
	 * 1 차원 Knapsack 사용
	 */
	
	private static final int P = 10007;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, h, i, j, k, l;
		int[] dp, blocks;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		blocks = new int[m];
		dp = new int[h + 1];
		dp[0] = 1;
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; st.hasMoreTokens(); j++) {
				blocks[j] = Integer.parseInt(st.nextToken()); // 블록 입력
			}
			for (k = h; k > 0; k--) { // 1 차원 Knapsack 뒤에서 부터 탐색
				for (l = 0; l < j; l++) { // 블록 종류별로
					if (k >= blocks[l]) { // 블록을 쌓을 수 있으면
						dp[k] = (dp[k] + dp[k - blocks[l]]) % P; // 경우의 수 추가
					}
				}
			}
		}
		System.out.print(dp[h]);
	}
}
