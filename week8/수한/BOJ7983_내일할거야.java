package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class Main_7983_내일할거야{
	
	static class Work implements Comparable<Work>{
		int day;
		int endTime;
		
		public Work(int day, int endTime) {
			this.day = day;
			this.endTime = endTime;
		}

		@Override
		public int compareTo(Work o) {
			return o.endTime - this.endTime;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens;
		
		/*
		 * n개 과제 목록 <= 1,000,000
		 * 
		 * 과제 i는 d[i]일이 걸림, t[i]일 안에 끝내야함
		 * 
		 * 최소 시작 일 : t[i] - d[i] + 1;
		 * 
		 * 과제는 쉬지 않고 계속 해야함
		 * 
		 * 내일부터 연속으로 최대 며칠동안 놀 수 있는지 궁금
		 */
		
		int N = Integer.parseInt(br.readLine());
		
		Work[] works = new Work[N];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			
			int d = Integer.parseInt(tokens.nextToken());
			int t = Integer.parseInt(tokens.nextToken());
			
			works[i] = new Work(d,t);
		}
		
		Arrays.sort(works);
		
		int curStartTime = works[0].endTime;
		
		for (int i = 0; i < N; i++) {
			Work work = works[i];
			
			if(curStartTime < work.endTime) {
				curStartTime -= work.day;  
			}else {
				curStartTime = work.endTime-work.day;  
			}
		}
		
		System.out.println(curStartTime);
	}
}
