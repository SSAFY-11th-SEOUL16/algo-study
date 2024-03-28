package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class Main_1647_도시_분할_계획{

	static class Edge implements Comparable<Edge>{
		int a,b;
		int cost;

		public Edge(int a, int b, int cost) {
			this.a = a;
			this.b = b;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Edge [a=");
			builder.append(a);
			builder.append(", b=");
			builder.append(b);
			builder.append(", cost=");
			builder.append(cost);
			builder.append("]");
			return builder.toString();
		}
		
		
	}
	
	static int find(int x, int[] parent) {
		int v = x;
		while(parent[v]!=v) {
			v = parent[v];
		}
		return parent[x] = v;
	}
	
	static void union(int a, int b, int[] parent) {
		parent[b] = a;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens;
		
		/*
		 * N개의 집, M개의 길, 길마다 유지비
		 * 
		 * 임의 두 집 사이 경로 항상 존재
		 * 
		 * 마을을 두 개의 분리된 마을로 분할
		 * 
		 * 유지비 합 최소
		 * 
		 * 크루스칼 -> 최대 유지비 빽기 (정점이 N개라면 Edge가 N-2가 될때까지)
		 */
		
		tokens = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(tokens.nextToken());
		int M = Integer.parseInt(tokens.nextToken());
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			int cost = Integer.parseInt(tokens.nextToken());
			
			pq.add(new Edge(a,b,cost));
		}
		
		int[] parent = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		int totalCost = 0;
		int numEdge = 0;
		
		while(!pq.isEmpty() && numEdge < N-2) {
			Edge edge = pq.poll();
			
			int pa = find(edge.a, parent);
			int pb = find(edge.b, parent);

			if(pa == pb) continue;
			
			union(pa,pb,parent);
			numEdge++;
			totalCost += edge.cost;
		}
		
		System.out.println(totalCost);
	}

}
