import java.io.*;
import java.util.*;

public class BJ1774_우주신과의교감 {

	static int n,m;
	static int arr[][];
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static int parent[];
	
	static class Edge implements Comparable<Edge>{
		int u,v;
		double cost;

		public Edge(int u, int v, double cost) {
			super();
			this.u = u;
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.cost, o.cost);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n + 1][2];
		parent = new int[n + 1];
		
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken()); //x좌표
			arr[i][1] = Integer.parseInt(st.nextToken()); //y좌표
			parent[i] = i; //parent 배열 초기화
		}
		
		int edgeCnt = 0; //간선 개수
		
		//이미 연결된 우주신 설정
		for(int i=1;i<=m;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if(find(a) == find(b)) continue;
			
			edgeCnt++;
			union(a, b);
		}
		
		//모든 간선 우선순위큐에 저장
		for(int i=1;i<=n;i++) {
			for(int j=i+1;j<=n;j++) {
				double distance = Math.sqrt(Math.pow(arr[i][0]-arr[j][0], 2) + Math.pow(arr[i][1]-arr[j][1], 2));
				pq.add(new Edge(i,j,distance));
			}
		}
	
		double result = 0;
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			//이미 연결된 경우
			if(find(cur.u) == find(cur.v)) continue;
			
			//합치기
			union(find(cur.u), find(cur.v));
			result += cur.cost; //거리 갱신
			edgeCnt++; //간선 개수 갱신
			
			//간선이 정점-1개가 되는 경우 모든 정점이 연결되어 있는 것!
			if(edgeCnt == n-1) break;
		}
		
		System.out.printf("%.2f", Math.round(result*100) / 100.);
	}
	
	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		parent[pb] = pa;
	}

	static int find(int a) {
		if(a == parent[a]) return a;
		return parent[a] = find(parent[a]);
	}
}

