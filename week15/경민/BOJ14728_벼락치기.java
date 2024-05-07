import java.util.*;
import java.io.*;

public class Main {

	static int n, t;
	static int[][] input, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		input = new int[n + 1][t + 1];
		dp = new int[n + 1][t + 1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			input[i][0] = Integer.parseInt(st.nextToken());
			input[i][1] = Integer.parseInt(st.nextToken());
		}

		// 최대 점수 식을 통한 정보 저장
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= t; j++) {
				// i번째 단원의 모든 내용을 학습할 수 있을 때
				if (j >= input[i][0]) {
					// 1, 2번 경우에서 최대 값 저장
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-input[i][0]] + input[i][1]);
					
				} else {
					// i번째 단원의 모든 내용을 학습할 수 없을 때
					// 2번 경우만 진행하기에 그대로 값 저장
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		System.out.println(dp[n][t]);
	}
}
