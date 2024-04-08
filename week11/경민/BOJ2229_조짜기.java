import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n + 1];
		int[] dp = new int[n + 1];
		// dp[i]: i번째 학생까지 포함했을 때, 조가 잘 짜여진 최댓값
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 2; i < arr.length; i++) {
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;

			for (int j = i; j >= 1; j--) {
				max = Math.max(max, arr[j]);
				min = Math.min(min, arr[j]);
				// j학생부터 i번째 학생이 한 조가 된다면 j-1번째 학생까지 최대값은 dp[j-1]이 된다.
				dp[i] = Math.max(dp[i], max - min + dp[j - 1]);
			}
		}
		System.out.println(dp[n]);
	}
}