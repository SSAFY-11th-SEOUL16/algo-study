import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW2115_벌꿀채취 {
	
	/**
	 * 120 ms
	 * 각각의 지점부터 오른쪽으로 M 칸 꿀 채취
	 * M 개의 칸에서 최대 수익을 얻는 경우
	 * M 가지 짐, 용량 C, 가치 weight 제곱
	 * knapsack
	 */
	
	private static final int MAX_N = 10;
	
	private static int n, m, c, max1, max2;
	private static int[][] beehives;
	
	private static int knapsack(int start, int[] beehive) { // 최대 수익 knapsack
		int weight, value, i, j;
		int[] dp;
		
		dp = new int[c + 1];
		for (i = 1; i <= m; i++) {
			weight = beehive[i - 1 + start];
			value = weight * weight;
			for (j = c; j > 0; j--) {
				if (j >= weight) {
					dp[j] = Math.max(dp[j], dp[j - weight] + value);
				}
			}
		}
		return dp[c];
	}
	
	private static void getResults() {
		int result, i, j;
		
		for (i = 0; i < n; i++) {
			result = 0;
			for (j = 0; j < n - m + 1; j++) {
				result = Math.max(result, knapsack(j, beehives[i])); // 최대 수익 계산
			}
			if (result > max1) { // 최대 수익 업데이트
				max2 = max1;
				max1 = result;
			} else if (result > max2) { // 두 번째 최대 수익 업데이트
				max2 = result;
			}
		}
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int i, j;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		for (i = 0; i < n; i++) { // 벌통 정보 입력
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < n; j++) {
				beehives[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		max1 = 0;
		max2 = 0;
		getResults();
		return max1 + max2;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		beehives = new int[MAX_N][MAX_N];
		t = Integer.parseInt(br.readLine());
		for (testCase = 1; testCase <= t; testCase++) {
			sb.append('#').append(testCase).append(' ').append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}
