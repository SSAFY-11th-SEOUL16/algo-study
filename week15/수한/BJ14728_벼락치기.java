package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class Main_14728_벼락치기{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens;
		
		/*
		 * 여러 단원 융햡 문제 출제 X
		 * 한 단원에 한 문제
		 * 
		 * 단원 별 배점
		 * 
		 * 단원 별 예상 공부 시간 이상 해야 맞출 수 있음
		 */
		
		tokens = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(tokens.nextToken());
		int T = Integer.parseInt(tokens.nextToken());
		
		int[] dp = new int[T+1];
		
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			
			int K = Integer.parseInt(tokens.nextToken()); // 예상 공부 시간
			int S = Integer.parseInt(tokens.nextToken()); // 배점
			
			for (int j = T; j >= K; j--) {
				dp[j] = Math.max(dp[j], dp[j-K] + S);
			}
		}
		
		System.out.println(dp[T]);
		
	}
}
