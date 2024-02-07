package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class Main_1326{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());

		/*
		 * 징검다리 숫자 배수만큼 갈 수 있음
		 * a -> b 징검다리로 가려고 함
		 * 최소 점프 횟수
		 */
		
		int[] nums = new int[N+1];
		StringTokenizer tokens = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(tokens.nextToken());
		}

		tokens = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(tokens.nextToken());
		int b = Integer.parseInt(tokens.nextToken());
		
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[]{a,0});
		
		int answer = -1;
		boolean[] visited = new boolean[N+1];
		visited[a] = true;
		while(!q.isEmpty()) {
			int[] info = q.poll();
			int idx = info[0];
			int cnt = info[1];
			
			if(idx == b) {
				answer = cnt;
				break;
			}
			
			int num = nums[idx];
			for(int i=idx+num; i <= N; i+=num) {
				if(!visited[i]) {
					visited[i] = true;
					q.add(new int[]{i,cnt+1});
				}
			}			
			for(int i=idx-num; i >= 1; i-=num) {
				if(!visited[i]) {
					visited[i] = true;
					q.add(new int[]{i,cnt+1});
				}
			}	
		}
		
		System.out.println(answer);
	}

}
