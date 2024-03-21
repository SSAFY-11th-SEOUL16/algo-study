import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] indegree;
	static Node[] graph;

	static class Node implements Comparable<Node> {
		int time;
		ArrayList<Integer> list;

		public Node(int time) {
			this.time = time;
			list = new ArrayList<>();
		}

		@Override
		public int compareTo(Node o) {
			return this.time - o.time;
		}

	}

	public static int topologySort() {
		int time = 0;
		PriorityQueue<Node> pq = new PriorityQueue<Node>();

		for (int i = 1; i <= n; i++) {
			if (indegree[i] == 0)
				pq.offer(graph[i]);
		}

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			time = now.time;

			for (int i = 0; i < now.list.size(); i++) {
				// 진입차수-1
				indegree[now.list.get(i)]--;

				if (indegree[now.list.get(i)] == 0) {
					graph[now.list.get(i)].time += now.time;
					pq.offer(graph[now.list.get(i)]);
				}
			}
		}
		return time;

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		indegree = new int[n + 1];
		graph = new Node[n + 1];

		// 작업에 걸리는 시간과 선행 관계
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());

			graph[i] = new Node(Integer.parseInt(st.nextToken()));

			int tmp = Integer.parseInt(st.nextToken());
			for (int j = 0; j < tmp; j++) {
				graph[Integer.parseInt(st.nextToken())].list.add(i);
				indegree[i]++; // 진입차수+1
			}
		}

		int result = topologySort();
		System.out.println(result);
	}
}
