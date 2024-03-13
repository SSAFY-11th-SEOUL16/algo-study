package week8.상현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1943_동전분배 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());

			int[][] coins = new int[N][2];
			//dp 배열의 각 인덱스 = 금액 true라면 가능한 금액들을 의미
			boolean[] dp = new boolean[100001];

			int total = 0;
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				int price = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());

				coins[j][0] = price;
				coins[j][1] = v;

				total += (price * v);

				//각 금액 * 개수 만큼 가능하므로 true 체크
				for (int k = 1; k <= v; k++) {
					dp[price * k] = true;
				}
			}

			//홀수면 절대 불가능
			if (total % 2 == 1) {
				sb.append(0).append("\n");
				continue;
			}

			//금액의 절반만이여야 하기 때문에 나누기 2
			total = total / 2;
			
			dp[0] = true;

			//동전 종류만큼 반복
			for (int j = 0; j < N; j++) {
				int price = coins[j][0];
				int v = coins[j][1];

				//총 금액에서 내려가면서 각 금액 - 동전 금액이 가능하다면 해당 금액에서 각 동전 * 개수 금액 만큼 더한 경우를 true 처리
				for (int k = total; k >= 0; k--) {
					if (k - price > 0 && dp[k - price]) {
						for (int l = 1; l <= v; l++) {
							if (k + price * l > total) {
								break;
							} else {
								dp[k + price * l] = true;
							}
						}
					}
				}
			}
			
			sb.append(dp[total] ? 1 : 0).append("\n");
		}

		System.out.println(sb);
	}
}