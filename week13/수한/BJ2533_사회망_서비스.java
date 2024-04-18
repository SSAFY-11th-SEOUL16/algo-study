package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class Main_2533_사회망_서비스{

	static int N;
	static List<Integer>[] friends;
	static int[][] dp;
	static boolean[] visited;

	static void travesal(int i){
		dp[i][0] = 0;
		dp[i][1] = 1;

		visited[i] = true;

		for (int friend : friends[i]){
			if(visited[friend]) continue;
			if(dp[friend][0] == -1) travesal(friend);
			dp[i][0] += dp[friend][1];
			dp[i][1] += Math.min(dp[friend][0],dp[friend][1]);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens;
		
		/*
		 * dp[i] : i가 아이디어 받아들일 때 최소 얼리 어답터 수
		 *
		 * dp[i] : i가 얼리 어답터 일 때, 아닐때
		 *
		 * 1) i가 얼리 어답터가 아닐 때
		 *    - i와 연결된 애들이 전부 얼리 어답터여야함
		 * 2) i가 얼리 어답터일 때
		 *    - i와 연결된 애들은 얼리 어답터 여부 상관 없음
		 *
		 * dp[i][0] : i가 얼리 어답터가 아닌 경우 최솟 값
		 * dp[i][1] : i가 얼리 어답터인 경우 최솟 값
		 *
		 * dp[i][0] += dp[k][1];
		 * dp[i][1] = 1;
		 * dp[i][1] += min(dp[k][1],dp[k][0]);
		 */

		N = Integer.parseInt(br.readLine());

		friends = new ArrayList[N+1];

		for (int i = 1; i <= N; i++) {
			friends[i] = new ArrayList<>();
		}

		for (int i = 1; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(tokens.nextToken());
			int v = Integer.parseInt(tokens.nextToken());

			friends[u].add(v);
			friends[v].add(u);
		}

		visited = new boolean[N+1];
		dp = new int[N+1][2];

		for (int i = 1; i <= N; i++) {
			dp[i][0] = dp[i][1] = -1;
		}

		travesal(1);

		System.out.println(Math.min(dp[1][0],dp[1][1]));
	}
}
