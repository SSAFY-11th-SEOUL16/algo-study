import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[] parent;
	static ArrayList<Node> edges = new ArrayList<>();

	static class Node implements Comparable<Node> {
		int v;
		int w;
		int cost;

		public Node(int v, int w, int cost) {
			this.v = v;
			this.w = w;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	static int find(int x) {
		if (x == parent[x])
			return x;
		else
			return parent[x] = find(parent[x]);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a < b)
			parent[b] = a;
		else
			parent[a] = b;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		parent = new int[n + 1];
		for (int i = 1; i <= n; i++)
			parent[i] = i;

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			edges.add(new Node(v, w, cost));
		}

		Collections.sort(edges);
		
		int max = 0;
		int sum = 0;
		for (int i = 0; i < edges.size(); i++) {
			int v = edges.get(i).v;
			int w = edges.get(i).w;
			int cost = edges.get(i).cost;
			
			if(find(v) != find(w)) {
				union(v, w);
				sum += cost;
				max = Math.max(cost, max); 
			}
		}
		
		System.out.println(sum - max);
	}
}
