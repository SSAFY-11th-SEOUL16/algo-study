import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * JAVA
 * 132 MS
 * 
 * DP
 * 
 * 블록들을 이용하여 특정 높이의 탑을 만들 수 있는 경우의 수
 * dp[i][j]를 첫 번째 학생부터 i번째 학생까지 고려했을 때 높이 j를 만들 수 있는 경우의 수
 */
public class BJ18427_함께_블록_쌓기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int[][] dp = new int[N + 1][H + 1];

		ArrayList<Integer>[] blocks = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			blocks[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				blocks[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		for (int i = 0; i <= N; i++) {
			dp[i][0] = 1;
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= H; j++) {
				for (int block : blocks[i]) {
					if (j >= block) {
						dp[i][j] += dp[i - 1][j - block];
					}
				}
				dp[i][j] += dp[i - 1][j];
				dp[i][j] %= 10_007;
			}
		}
		System.out.println(dp[N][H]);
	}
}