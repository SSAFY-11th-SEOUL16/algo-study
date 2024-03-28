import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1647_도시분할계획 {
	
	/**
	 * 1132 ms
	 * Kruskal MST
	 * MST에서 한 간선을 끊으면 두 집단으로 분리
	 * Kruskal에서 N - 2 개의 간선 선택
	 */
	
	private static class Edge implements Comparable<Edge> {
		int u, v, weight;
		
		Edge(int u, int v, int weight) {
			this.u = u;
			this.v = v;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) { // 길이 오름차순 정렬
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	private static int[] root; // Parent + Rank
	
	private static boolean union(int u, int v) {
		int ru, rv;
		
		if ((ru = find(u)) == (rv = find(v))) { // 조상이 같음
			return false;
		}
		if (root[ru] > root[rv]) { // Union by Rank
			root[ru] = rv;
		} else {
			if (root[ru] == root[rv]) { // Rank 동일
				root[ru]--; // Rank 1 증가
			}
			root[rv] = ru;
		}
		return true;
	}
	
	private static int find(int u) { 
		if (root[u] <= 0) { // 루트 노드
			return u;
		}
		return root[u] = find(root[u]); // Path Compression
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, ans, i;
		Edge edge;
		PriorityQueue<Edge> pq;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		pq = new PriorityQueue<>();
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			pq.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		root = new int[n + 1];
		ans = 0;
		for (i = 0; i < n - 2;) { // Kruskal 간선 N - 2 개만 선택
			edge = pq.poll(); // PQ에서 정렬된 간선
			if (union(edge.u, edge.v)) { // Union 성공
				ans += edge.weight; // 간선 선택
				i++; // 간선 카운트
			}
		}
		System.out.print(ans);
	}
}
