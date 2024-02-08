package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class Main_16987{
	
	static int N;
	static int[] S, W; 
	static int answer = 0;
	
	public static void solve(int idx, int count) {
		if(idx == N) {
			answer = Math.max(answer, count);
			return;
		}

		if(S[idx] > 0) {
			boolean allBroken = true;
			int ncount;
			for (int i = 0; i < N; i++) {
				if(S[i] <= 0 || i == idx) continue;
				allBroken = false;
				ncount = count;
				S[idx] -= W[i];
				S[i] -= W[idx];
				if(S[idx] <= 0) ncount++;
				if(S[i] <= 0) ncount++;
				solve(idx+1, ncount);
				S[idx] += W[i];
				S[i] += W[idx];
			}
			if(allBroken) solve(idx+1, count);
		} else {
			solve(idx+1, count);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		S = new int[N]; // 내구도
		W = new int[N]; // 무게

		StringTokenizer tokenizer;
		
		for (int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(br.readLine());
			S[i] = Integer.parseInt(tokenizer.nextToken());
			W[i] = Integer.parseInt(tokenizer.nextToken());
		}
		
		
		/*
		 * 계란 내구도는 상대 계란의 무게만큼 깎임
		 * 
		 * 1. 가장 왼쪽 계란 들기
		 * 2. 다른 계란 중 하나 침
		 * 	  - 손에 든 계란이 깨졌거나 깨지지 않은 계란 없으면 넘어감
		 *    - 손에 든 계란 제자리에
		 * 3. 가장 최근에 든 계란의 한 칸 오른쪽 계란을 들고 2번 진행
		 *    - 가장 오른쪽 계란이라면 종료
		 */
		
		solve(0,0);
		
		System.out.println(answer);
	}

}
