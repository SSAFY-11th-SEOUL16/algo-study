import java.io.*;
import java.util.*;

public class Main {
	// 다익스트라
	static int n, m, x, result = 0;
	static List<ArrayList<Node>> graphIn = new ArrayList<ArrayList<Node>>();
	static List<ArrayList<Node>> graphOut = new ArrayList<ArrayList<Node>>();
	static int[] go, back;

	// go : 1번 ~ N번 노드가 X번 마을로 가는 데 최소 비용
	// back : X번 마을에서 1~N번 마을로 가는 데 최소 비용

	static class Node implements Comparable<Node> {
		int v;
		int dist;

		public Node(int v, int cost) {
			this.v = v;
			this.dist = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.dist - o.dist;
		}
	}

	public static void dijkstra(int[] d, List<ArrayList<Node>> graph) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(x, 0));
		d[x] = 0;
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int dist = node.dist;
			int now = node.v;

			if (d[now] < dist)
				continue;

			for (int i = 0; i < graph.get(now).size(); i++) {
				int cost = d[now] + graph.get(now).get(i).dist;

				if (cost < d[graph.get(now).get(i).v]) {
					d[graph.get(now).get(i).v] = cost;
					pq.offer(graph.get(now).get(i));
				}

			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());

		back = new int[n + 1];
		go = new int[n + 1];

		for (int i = 0; i <= n; i++)
			graphIn.add(new ArrayList<>());

		for (int i = 0; i <= n; i++)
			graphOut.add(new ArrayList<>());

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graphIn.get(to).add(new Node(from, c));
			graphOut.get(from).add(new Node(to, c));
		}

		Arrays.fill(go, Integer.MAX_VALUE);
		Arrays.fill(back, Integer.MAX_VALUE);

		dijkstra(go, graphOut);
		dijkstra(back, graphIn);

		for (int i = 1; i <= n; i++)
			result = Math.max(result, go[i] + back[i]);

		System.out.println(result);
	}

}
