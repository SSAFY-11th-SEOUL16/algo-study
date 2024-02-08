package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class Main_1497{
	static int answer = Integer.MAX_VALUE;
	static int N;
	static int M;
	static int maxSongs = 0;
	static long[] ableList;
	static long[] checkList;
	
	public static void subset(int idx, long num, int count) {
		if(idx == N) {
			int numSongs = 0;
			for (int i = 0; i < M; i++) {
				if((num & checkList[i]) == checkList[i]) numSongs++;
			}
			
			if (maxSongs < numSongs) {
				maxSongs = numSongs;
				answer = count;
			}else if(maxSongs == numSongs && maxSongs != 0) {
				answer = Math.min(answer, count);
			}
			
			return;
		}
				
		// 해당 악기 사용X
		subset(idx+1,num, count);
		// 해당 악기 사용O
		subset(idx+1,num | ableList[idx], count+1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens;
		tokens = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		/*
		 * 기타마다 연주할 수 있는 곡이 있음
		 * 최소 개수로 모든 곡 연주할 때의 기타 개수 구하기
		 * 
		 * M이 최대 50이니 비트마스킹 사용
		 * 부분집합 완전탐색
		 */
		
		ableList = new long[N]; // 가능한 곡을 비트로 표현 YYYNN -> 11100
		checkList = new long[M]; // 2^0~2^(M-1)까지의 값을 미리 저장하는 배열
		
		for (int i = 0; i < M; i++) {
			checkList[i] = 1L << i;
		}
		
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			tokens.nextToken();
			String ables = tokens.nextToken();
			for (int j = 0; j < M; j++) {
				// 만약 j번째 곡을 칠 수 있다면 해당 비트 켜기
				if(ables.charAt(j) == 'Y') ableList[i] |= checkList[j];
			}
		}
		
		subset(0,0,0);
		
		System.out.println(maxSongs == 0 ? "-1" : answer);		
	}

}
