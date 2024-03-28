package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2631_줄세우기{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		/*
		 * 1~N번 아이
		 * 
		 * 2 <= N <= 200
		 * 
		 * 목적지까지 번호순서대로
		 * 
		 * 이동 도중 번호순서가 바뀜
		 * 
		 * 옮기는 아이들 수 최소
		 * 
		 * 최대 부분 정렬 된 아이들 수 구하기
		 */
		
		int N = Integer.parseInt(br.readLine());

		int[] nums = new int[N];
		int[] dp = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
			dp[i] = 1;
		}

		int max = 0;
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if(nums[j] < nums[i] && dp[i] <= dp[j]) {
					dp[i] = dp[j]+1;
				}
			}
			max = max < dp[i] ? dp[i] : max;
		}
		
		System.out.println(N-max);
	}
}
