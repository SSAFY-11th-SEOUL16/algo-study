import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int dp[][] = new int[n + 1][k + 1];
		int w[] = new int[n + 1]; //무게
		int v[] = new int[n + 1]; //가치

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			w[i] = Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= n; i++) { // 1~n번 아이템
			for (int j = 1; j <= k; j++) { // 1~k 무게
				dp[i][j] = dp[i - 1][j];
				if (j - w[i] >= 0) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
				}
			}
		}

		System.out.println(dp[n][k]);
	}

}
