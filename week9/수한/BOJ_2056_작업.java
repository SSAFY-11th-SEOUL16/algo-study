package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class Main_2056_작업{
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens;
		
		/*
		 * 작업마다 걸리는 시간 주어짐
		 */
		
		int N = Integer.parseInt(br.readLine());
		
		int[] degree = new int[N+1];
		int[] times = new int[N+1];
		int[] maxTimes = new int[N+1];
		List<Integer>[] childs = new List[N+1];

		for (int i = 1; i <= N; i++) {
			childs[i] = new ArrayList<>();
			
			tokens = new StringTokenizer(br.readLine());
			times[i] = Integer.parseInt(tokens.nextToken());
			
			int M = Integer.parseInt(tokens.nextToken());
			degree[i] += M;
			for (int j = 0; j < M; j++) {
				int from = Integer.parseInt(tokens.nextToken());
				childs[from].add(i);
			}
		}
		
		Queue<Integer> works = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if(degree[i] == 0) {
				works.add(i);
			}
		}
		
		int answer = 0;
		
		while(!works.isEmpty()) {
			int work = works.poll();
			times[work] += maxTimes[work];
			answer = Math.max(times[work], answer);
			for(int to : childs[work]) {
				degree[to]--;
				maxTimes[to] = Math.max(maxTimes[to],times[work]);
				if(degree[to] == 0) {
					works.add(to);
				}
			}
		}

		System.out.println(answer);
	}
}
