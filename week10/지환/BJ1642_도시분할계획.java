import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

//Java8(2036ms)
//가장 최소의 비용으로 두개로 분할
//최소의 비용으로 연결할 수 있는 것은 크루스칼 알고리즘 사용
//가장 큰 비용의 개수를 빼면 된다.
public class BJ1642_도시분할계획 {
	static int[] parents;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		parents = new int[n+1];
		for(int i=1; i<=n; i++) {
			parents[i]= i;
		}
		List<Edge> edges = new ArrayList<>();
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edges.add(new Edge(start, end, cost));
		}

		Collections.sort(edges, (Comparator.comparingInt(o -> o.cost)));
		int maxCost = 0;
		int res = 0;
		for(Edge edge: edges) {
			if(findParent(edge.start) == findParent(edge.end)) continue;
			union(edge.start, edge.end);
			res += edge.cost;
			maxCost = Math.max(maxCost, edge.cost);
		}
		System.out.println(res - maxCost);
	}

	public static int findParent(int n) {
		if(parents[n]==n) return n;
		return findParent(parents[n]);
	}

	public static void union(int a, int b) {
		int p1 = findParent(a);
		int p2 = findParent(b);
		if(p1 < p2) {
			parents[p2] = p1;
		} else {
			parents[p1] = p2;
		}
	}

	static class Edge {
		int start;
		int end;
		int cost;

		Edge(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
	}
}
