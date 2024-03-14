package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class Main_1238_파티{
	static int N,M,X;
	
	static class Edge implements Comparable<Edge>{
		int to;
		int time;
		
		public Edge(int to, int time) {
			this.to = to;
			this.time = time;
		}

		@Override
		public int compareTo(Edge o) {
			return this.time - o.time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens;
		
		/*
		 * N개 마을, 각 마을당 한 명의 학생
		 * 
		 * X번 마을에서 파티
		 * 
		 * 마을 사이 M개 단방향 도로, i번째 도로는 T[i] 시간 소비
		 * 
		 * 파티하고 돌아와야 함
		 * 
		 * 가장 많은 시간 소비 학생 구하기
		 * 
		 * 모든 노드 다익스트라
		 * 
		 */
		
		tokens = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		X = Integer.parseInt(tokens.nextToken());
		
		List<Edge>[] edges = new List[N+1];
		
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			int time = Integer.parseInt(tokens.nextToken());
			
			edges[from].add(new Edge(to,time));
		}

		boolean[] visited = new boolean[N+1];
		
		int[][] times = new int[N+1][N+1];

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 1; i <= N; i++) {
			Arrays.fill(visited, false);
			Arrays.fill(times[i], Integer.MAX_VALUE);

			visited[i] = true;
			for (Edge e : edges[i]) {
				times[i][e.to] = e.time;
				pq.add(e);
			}
			times[i][i] = 0;
			
			while(!pq.isEmpty()) {
				Edge edge = pq.poll();
				
				visited[edge.to] = true;
				
				for(Edge e : edges[edge.to]) {
					int time = edge.time + e.time;
					if(time < times[i][e.to]) {
						times[i][e.to] = time;
						if(!visited[e.to])
							pq.add(new Edge(e.to,time));
					}
				}
			}
		}
		
		int maxTime = 0;
		
		for (int i = 1; i <= N; i++) {
			int totalTime = times[i][X] + times[X][i];
			if(totalTime > maxTime) {
				maxTime = totalTime;
			}
		}
		
		System.out.println(maxTime);
	}

}
