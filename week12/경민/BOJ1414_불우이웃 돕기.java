import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static ArrayList<Node> edges = new ArrayList<>();
	static int[] parent;

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
		return parent[x] = find(parent[x]);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);

		parent[b] = parent[a];

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		parent = new int[n + 1];

		for (int i = 1; i <= n; i++)
			parent[i] = i;

		int total = 0;

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				char c = str.charAt(j);

				if (c >= 97 && c <= 122) {
					edges.add(new Node(i, j, c - 96));
					total += c - 96;
				} else if (c >= 65 && c <= 90) {
					edges.add(new Node(i, j, c - 38));
					total += c - 38;
				} else if (c == '0')
					continue;

			}
		}

		Collections.sort(edges);
		int count = 1;
		int use = 0;
		for (int i = 0; i < edges.size(); i++) {
			int v = edges.get(i).v;
			int w = edges.get(i).w;
			int cost = edges.get(i).cost;

			if (find(v) != find(w)) {
				union(v, w);
				use += cost;
				count++;
			}
		}


		System.out.println(count == n ? total - use : -1);
	}
}