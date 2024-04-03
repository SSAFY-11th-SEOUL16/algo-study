import java.io.*;
import java.util.*;

public class BJ1647_도시분할계획 {
	static int n,m; //집의 수, 길의 수
	static int parent[];
	static PriorityQueue<Edge> pq;
	
	static class Edge implements Comparable<Edge>{
		int v, e, cost;
		
		public Edge(int v, int e, int cost) {
			this.v= v;
			this.e= e;
			this.cost= cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		pq = new PriorityQueue<>();
		parent = new int[n+1];
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			pq.add(new Edge(v,e,cost));
		}
		
		for(int i=1;i<=n;i++) {
			parent[i] = i;
		}
		
		int maxLineLen = 0; //가장 큰 유지비
		int costSum = 0; //유지비 합계
		int lineCnt = 0; //간선 갯수
		while(true) {
			Edge cur = pq.poll();
			int v = cur.v;
			int e = cur.e;
			
			if(find(v) == find(e)) continue;
			
			union(v,e);
			
			maxLineLen = Integer.max(maxLineLen, cur.cost);
			lineCnt++;
			costSum += cur.cost;
			
			if(lineCnt == n - 1) break;
		}
		
		System.out.println(costSum - maxLineLen);
	}

	static int find(int x) {
		if(x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		
		parent[px] = py;
	}
}
