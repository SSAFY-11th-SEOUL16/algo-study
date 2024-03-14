package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class Main_2141_우체국{
	
	static class Town{
		long X, A;

		public Town(long x, long a) {
			X = x;
			A = a;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens;
		
		/*
		 * 수직선에 N개 마을 위치 <= 100,000
		 * 
		 * X[i] 마을에 A[i]명 살음
		 * 
		 * 각 사람들까지 거리 합 최소가 되는 위치에 우체국 설치 위치 구하기
		 */
		
		int N = Integer.parseInt(br.readLine());
		
		Town[] towns = new Town[N];
		
		long total = 0;
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(tokens.nextToken());
			int A = Integer.parseInt(tokens.nextToken());
			
			towns[i] = new Town(X,A);
			total += A;
		}
		
		Arrays.sort(towns,(a,b)->((int)(a.X-b.X)));

		long acc = 0;
		for (int i = 0; i < N-1; i++) {
			acc += towns[i].A;
			long diff = acc + acc - total; // acc - (total - acc);

			if(diff >= 0) {
				System.out.println(towns[i].X);
				return;
			}
		}
		System.out.println(towns[N-1].X);
	}

}
