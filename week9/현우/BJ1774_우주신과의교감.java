import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1774_우주신과의교감 {
	
	/**
	 * 212 ms
	 * 우주신들과 황선자씨 좌표 저장
	 * 이미 연결된 우주신들 Union
	 * MST: Kruskal
	 * 연결되어 있지 않은 우주신들 사이에
	 * 간선을 만들고 PQ에 push
	 * N - 1 개에서 이미 Union된 만큼
	 * 뺀 횟수까지 간선 선택
	 */
	
	private static class Edge implements Comparable<Edge> {
		int u, v;
		long weight;
		
		Edge(int u, int v, long weight) {
			this.u = u;
			this.v = v;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) { // 간선 가중치 비교
			return Long.compare(this.weight, o.weight);
		}
	}
	
	private static int[] root; // 부모 배열. 루트노드가 가리키는 0과 음수는 -Rank로 사용
	
	private static long dist(long[] u, long[] v) { // 간선 길이
		return (u[0] - v[0]) * (u[0] - v[0]) + (u[1] - v[1]) * (u[1] - v[1]);
	}
	
	private static boolean union(int u, int v) {
		int ru, rv;
		
		if ((ru = find(u)) == (rv = find(v))) {
			return false;
		}
		if (root[ru] > root[rv]) { // v 트리의 Rank가 더 크면 (음수가 더 작으면)
			root[ru] = rv; // u 트리를 v 트리의 루트에 붙임
		} else {
			if (root[ru] == root[rv]) { // Rank가 같으면
				root[ru]--; // Rank 1 증가 (음수값 1 감소)
			}
			root[rv] = ru; // v 트리를 u 트리의 루트에 붙임
		}
		return true;
	}
	
	private static int find(int u) {
		if (root[u] <= 0) {
			return u;
		}
		return root[u] = find(root[u]); // Path compression
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, u, v, size, i;
		double ans;
		long[][] gods;
		Edge edge;
		PriorityQueue<Edge> pq;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		gods = new long[n + 1][2];
		for (i = 1; i <= n; i++) { // 우주신과 황선자씨 좌표 입력
			st = new StringTokenizer(br.readLine());
			gods[i][0] = Long.parseLong(st.nextToken());
			gods[i][1] = Long.parseLong(st.nextToken());
		}
		size = n - 1;
		root = new int[n + 1];
		for (i = 0; i < m; i++) { // 이미 연결된 우주신들
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			if (union(u, v)) {
				size--; // 추후에 Union할 횟수 감소
			}
		}
		pq = new PriorityQueue<>(); // MST: Kruskal
		for (u = 1; u <= n; u++) {
			for (v = u + 1; v <= n; v++) { // u, v 두 우주신 선택
				if (find(u) != find(v)) { // 연결되어 있지 않으면
					pq.add(new Edge(u, v, dist(gods[u], gods[v]))); // 간선 추가
				}
			}
		}
		ans = 0;
		for (i = 0; i < size;) { // size = 선택해야 하는 간선 개수
			edge = pq.poll();
			if (union(edge.u, edge.v)) {
				ans += Math.sqrt(edge.weight); // MST 거리 계산
				i++;
			}
		}
        System.out.printf("%.2f", ans); // 소수점 둘째 자리까지 반올림
	}
}
