package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class Main_1774_우주신{
	static class Coord{
		int x,y;

		public Coord(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static class Edge implements Comparable<Edge>{
		int from, to;
		double dist;
		
		public Edge(int from, int to, double dist) {
			super();
			this.from = from;
			this.to = to;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.dist, o.dist);
		}
	}
	
	static int[] parents;
	
	public static int find(int x) {
		int v = x;
		while(parents[v] != -1) {
			v = parents[v];
		}
		
		if(v != x) {
			parents[x] = v;
		}
		
		return v;
	}
		
	public static void union(int i, int j) {
		parents[i] = j;
	}
	
	public static double getDist(Coord c1, Coord c2) {
		long diffX = c1.x-c2.x;
		long diffY = c1.y-c2.y;
		return Math.sqrt(diffX*diffX + diffY*diffY);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens;
		
		tokens = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(tokens.nextToken());
		int M = Integer.parseInt(tokens.nextToken());
		
		parents = new int[N+1];
		Arrays.fill(parents, -1);
		Coord[] coords = new Coord[N+1];
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 1; i <= N; i++) {
			tokens = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(tokens.nextToken());
			int y = Integer.parseInt(tokens.nextToken());
			coords[i] = new Coord(x,y);
			
			for (int j = 1; j < i; j++) {
				pq.add(new Edge(i,j,getDist(coords[i],coords[j])));
			}
		}

		int numComplete = 0;
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());

			int pf = find(from);
			int pt = find(to);
			
			if(pf == pt) continue;

			union(pf, pt);
			numComplete++;
		}

		double totalDist = 0;
		while(!pq.isEmpty() && numComplete < N-1) {
			Edge a = pq.poll();
			
			int pf = find(a.from);
			int pt = find(a.to);
			
			if(pf == pt) continue;
			
			union(pf, pt);
			numComplete++;
			
			totalDist += a.dist;
		}
		
		System.out.println(String.format("%.2f", totalDist));
	}
}