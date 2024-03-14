package week8.지환;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//Java(132ms)
//100원 짜리가 3개 있다고한다면 배열에서 200은 100에서 100원을 더한 것이다. 즉 기존값의 개수를 메모제이션 하는 방식
//이러면 동전의 개수만큼 반복문을 돌 필요가 없다.
public class BJ1943_동전분배_2 {
	static int n;
	static int[][] coins;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=3; t++){
			n = Integer.parseInt(br.readLine());
			int sum = 0;
			coins = new int[n][2];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int coin = coins[i][0] = Integer.parseInt(st.nextToken());
				int cnt = coins[i][1] = Integer.parseInt(st.nextToken());
				sum += coin * cnt;
			}
			if (sum % 2 != 0) sb.append(0);
			else sb.append(solve(sum/2));
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static int solve(int mid){
		int[] dp = new int[mid + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for(int i = 0; i < n; i ++){
			int coin = coins[i][0];
			int cnt = coins[i][1];
			for(int j = 0; j<=mid; j++){
				if(dp[j] == Integer.MAX_VALUE) continue;
				if(j + coin <= mid && dp[j] < cnt) {
					dp[j + coin] = Math.min(dp[j + coin], dp[j] + 1);
				}
				dp[j] = 0;
			}
		}
		if(dp[mid] != Integer.MAX_VALUE) return 1;
		else return 0;
	}
}
