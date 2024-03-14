import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static int[][] coins;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		for (int t = 0; t < 3; t++) {
			n = Integer.parseInt(br.readLine());
			coins = new int[n][2];
			int sum = 0;

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				coins[i][0] = Integer.parseInt(st.nextToken());
				coins[i][1] = Integer.parseInt(st.nextToken());

				sum += coins[i][0] * coins[i][1];
			}

			if (sum % 2 == 1) {
				sb.append(0).append('\n');
				continue;
			}

			// dp
			int mid = sum / 2;
			dp = new int[n + 1][mid + 1]; // i번째 동전으로 0부터 ~ mid 까지 만들 수 있는지

			for (int[] cols : dp)
				Arrays.fill(cols, Integer.MAX_VALUE);

			dp[0][0] = 0;

			for (int i = 0; i < n; i++) {
				int[] coin = coins[i];
				for (int j = 0; j <= mid; j++) {
					if (dp[i][j] == Integer.MAX_VALUE)
						continue;
					if (j + coin[0] <= mid && dp[i][j] < coin[1])
						dp[i][j + coin[0]] = Math.min(dp[i][j + coin[0]], dp[i][j] + 1);
					dp[i + 1][j] = 0;
				}
			}
			if (dp[n][mid] == 0)
				sb.append(1);
			else
				sb.append(0);
			sb.append('\n');
		}
		System.out.println(sb);
	}

}
