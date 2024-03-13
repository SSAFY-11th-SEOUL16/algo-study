import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1943_동전분배 {
	
	/**
	 * 124 ms
	 * 1 차원 DP
	 * dp 배열 INF로 초기화
	 * dp[x] : x 원을 만들기 위해 사용한 i 번 동전의 개수
	 * = dp[x - (동전 금액)] + 1
	 * INF가 아닌 dp 전부 0으로 초기화
	 * dp[금액 총합 / 2] != INF 이면 1 반환
	 * 다음 동전 탐색
	 */
	
	private static final int INF = Integer.MAX_VALUE;
	private static final int T = 3;
	private static final int MAX_SIZE = 101;
	private static final int MAX_SUM = 50001;
	
	private static int solution(BufferedReader br, StringTokenizer st, int[] coins, int[] nums, int[] dp) throws IOException {
		int n, sum, coin, num, prev, i, j;
		
		n = Integer.parseInt(br.readLine());
		sum = 0;
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			coin = Integer.parseInt(st.nextToken());
			num = Integer.parseInt(st.nextToken());
			sum += coin * num; // 총 금액 합
			coins[i] = coin; // i 번 동전 금액
			nums[i] = num; // i 번 동전 개수
		}
		if ((sum & 1) == 1) { // 금액 합이 홀수이면
			return 0; // 반으로 나눌 수 없음
		}
		sum >>= 1; // 금액 합 절반
		for (i = 1; i <= sum; i++) { // dp[x] : x 원을 만들기 위해 사용한 i 번 동전의 개수
			dp[i] = INF; // dp[j] == INF : 현재까지의 동전으로 j 원이 만들어지지 않음
		}
		prev = 0;
		for (i = 1; i <= n; i++) { // 동전 종류별 탐색
			coin = coins[i]; // i 번 동전 금액
			num = nums[i]; // i 번 동전 개수
			for (j = coin; j <= sum; j++) {
				if (dp[j - coin] < num && dp[j] == INF) { // 사용할 수 있는 동전이 남아있고, 아직 j 원이 만들어지지 않았으면
					dp[j] = dp[j - coin] + 1; // j 원을 만들기 위해 사용한 i 번 동전의 개수 = j - coin 원에 사용한 개수 + 1
					dp[j - coin] = 0; // 지나간 곳은 다음 동전 탐색할 때를 위해 0으로 초기화
					prev = j; // 탐색한 j를 prev에 저장
				}
			}
			if (dp[sum] != INF) { // 총 금액 합의 절반이 만들어짐
				return 1;
			}
			dp[prev] = 0; // 아직 초기화 되지 않은 prev 0으로 초기화
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int testCase;
		int[] coins, nums, dp;

		coins = new int[MAX_SIZE]; // 동전 금액
		nums = new int[MAX_SIZE]; // 동전 개수
		dp = new int[MAX_SUM]; // DP
		for (testCase = 0; testCase < T; testCase++) {
			sb.append(solution(br, st, coins, nums, dp)).append('\n');
		}
		System.out.print(sb);
	}
}
