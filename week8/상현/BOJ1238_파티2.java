package week8.상현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 처음 코드
 * 각각의 노드에서 X로 가는 최소 거리를 구하고 다시 X에서부터 각 노드로 돌아오는 최소 거리를 구해줌
 */
public class BOJ1238_파티2 {
	private static class Node {
		List<Edge> edges;

		public Node() {
			this.edges = new ArrayList<>();
		}

	}

	private static class Edge implements Comparable<Edge>{
		int to;
		int t;

		public Edge(int to, int t) {
			this.to = to;
			this.t = t;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.t - o.t;
		}


	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		Node[] nodes = new Node[N + 1];

		for (int i = 1; i <= N; i++) {
			nodes[i] = new Node();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());

			nodes[from].edges.add(new Edge(to, t));
		}

		int[] distance = new int[N + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[X] = 0;
		for (int i = 1; i <= N; i++) {
			if (i == X) {
				continue;
			}
			int[] goDis = new int[N + 1];
			int[] backDis = new int[N + 1];
			Arrays.fill(goDis, Integer.MAX_VALUE);
			Arrays.fill(backDis, Integer.MAX_VALUE);
			PriorityQueue<Edge> pq = new PriorityQueue<>();

			goDis[i] = 0;
			backDis[X] = 0;
			pq.add(new Edge(i, 0));
			while (!pq.isEmpty()) {
				Edge now = pq.poll();

				for (Edge e : nodes[now.to].edges) {
					if (goDis[e.to] > now.t + e.t) {
						goDis[e.to] = now.t + e.t;
						pq.add(new Edge(e.to, now.t + e.t));
					}
				}

			}

			pq.add(new Edge(X, 0));
			while (!pq.isEmpty()) {
				Edge now = pq.poll();

				for (Edge e : nodes[now.to].edges) {
					if (backDis[e.to] > now.t + e.t) {
						backDis[e.to] = now.t + e.t;
						pq.add(new Edge(e.to, now.t + e.t));
					}
				}
			}

			distance[i] = goDis[X] + backDis[i];
		}

		int result = Integer.MIN_VALUE;

		for (int i = 1; i <= N; i++) {
			result = Math.max(result,  distance[i]);
		}
		System.out.println(result);
	}
}