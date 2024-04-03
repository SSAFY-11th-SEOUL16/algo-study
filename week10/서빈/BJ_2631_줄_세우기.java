import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * java
 * 76 ms
 * LIS
 * 
 * LIS의 길이가 최대일 때, 옮겨야 하는 숫자의 개수는 최소
 */

public class BJ_2631_줄_세우기 {
	static int N;
	static int[] child;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		child = new int[N];
		dp = new int[N];
		Arrays.fill(dp, 1); // 모든 위치를 1로 초기화
		int max = 0;

		for (int i = 0; i < N; i++) { // 아이들의 번호를 입력 받음
			child[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (child[i] > child[j] && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1; // 새로운 LIS를 발견했을 때, LIS 길이 갱신
				}
			}
			max = Math.max(max, dp[i]); // dp 배열 최댓값을 갱신 => 전체 LIS의 최대 길이를 찾음
		}
		System.out.println(N - max); // 전체 - LIS 길이의 최댓값 => 옮겨야 하는 아이들의 최소 수
	}
}