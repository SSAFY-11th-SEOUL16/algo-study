import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static double result;
	static int[] parent;
	static PriorityQueue<Node> pq = new PriorityQueue<>();

	static class Node implements Comparable<Node> {
		int v;
		int w;
		double dist;

		public Node(int v, int w, double dist) {
			this.v = v;
			this.w = w;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			if(o.dist > this.dist)
				return -1;
			return 1;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + ", w=" + w + ", dist=" + dist + "]";
		}

	}

	static int find(int x) {
		if (x == parent[x])
			return x;
		return parent[x] = find(parent[x]);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (b > a)
			parent[b] = parent[a];
		else
			parent[a] = parent[b];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		visited = new boolean[n + 1];
		parent = new int[n + 1];

		for (int i = 1; i <= n; i++) 
			parent[i] = i;
		

		List<int[]> list = new ArrayList<>();
		list.add(new int[] { 0, 0 });
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) });
		}

		for (int i = 1; i < n; i++) {
			for (int j = i + 1; j <= n; j++) {
				double dist = Math.sqrt(Math.pow(list.get(i)[0] - list.get(j)[0],2) + Math.pow(list.get(i)[1] - list.get(j)[1], 2));
				pq.add(new Node(i, j, dist));
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			if(find(node.v) != find(node.w)) {
				union(node.v, node.w);
				result += node.dist;
			}
		}

		System.out.printf("%.2f",result);
	}
}