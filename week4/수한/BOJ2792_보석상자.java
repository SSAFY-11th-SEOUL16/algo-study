package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class Main_2792{
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens;
		
		tokens = new StringTokenizer(br.readLine());
		
		/*
		 * 각 보석은 M가지 색 중 하나
		 * 보석을 N명의 학생에게 나눠줌
		 * 
		 * 최소 질투심과 최대 질투심을 두어 최적의 질투심을 이분탐색
		 * 
		 * 최소 질투심이 j라 할 때 가능여부 판단
		 * 
		 * 만약 가능하다면? j를 더 줄임
		 * 불가능하다면? j를 키움
		 * 
		 * i번째 보석 개수가 V일때 최대 질투심이 j가 되게 하려면?
		 * V / n_i = j여야 함
		 * 여기서 n_i는 i번째 보석을 가진 아이들의 수
		 * n_i = V / j !!!!
		 */
				
		int N = Integer.parseInt(tokens.nextToken());
		int M = Integer.parseInt(tokens.nextToken());
		
		int maxCount=0;
		int[] counts = new int[M];
		for (int i = 0; i < M; i++) {
			counts[i] = Integer.parseInt(br.readLine());
			maxCount = Math.max(maxCount, counts[i]);
		}

		int minJealousy = 1;
		int maxJealousy = maxCount;

		while(minJealousy <= maxJealousy) {
			int midJealousy = (minJealousy + maxJealousy) / 2;
			
			int n = N;
			for (int i = 0; i < M && n >= 0; i++) {
				n -= counts[i] % midJealousy == 0 ? counts[i] / midJealousy : counts[i] / midJealousy + 1;
			}
			
			if(n < 0) {
				// 질투심의 최소값이 midJealousy가 불가능한 경우
				minJealousy = midJealousy+1;
			} else {
				// 질투심의 최소값이 midJealousy가 가능한 경우
				maxJealousy = midJealousy-1;
			}
		}
		
		System.out.println(maxJealousy+1);
	}

}
