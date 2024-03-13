import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1238_파티 {
	
	/**
	 * 152 ms
	 * 각 마을에서 X 마을로 가는 거리
	 * = X 마을에서 각 마을까지의 역방향 그래프 거리
	 * 간선을 입력 받을때 역방향 그래프도 추가로 생성
	 * X 마을에서부터 각 마을까지의
	 * 역방향 그래프 거리와 정방향 그래프 거리의 합 중에서
	 * 가장 큰 거리값 출력
	 */
	
	private static final int INF = Integer.MAX_VALUE;
	
	private static final class Edge { // 간선
		int to, weight;
		
		Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
	
	private static void spfa(int n, int x, int[] distance, boolean[] inQueue, Queue<Integer> q, ArrayList<ArrayList<Edge>> adj) {
		int curr, next, dist, i;
		
		for (i = 1; i <= n; i++) {
			distance[i] = INF;
		}
		distance[x] = 0;
		q.add(x);
		while (!q.isEmpty()) { // SPFA
			curr = q.poll();
			inQueue[curr] = false;
			for (Edge edge : adj.get(curr)) {
				next = edge.to;
				if ((dist = distance[curr] + edge.weight) < distance[next]) {
					distance[next] = dist;
					if (!inQueue[next]) {
						q.add(next);
						inQueue[next] = true;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, x, a, b, weight, ans, i;
		int[] distanceIn, distanceOut;
		boolean[] inQueue;
		Queue<Integer> q;
		ArrayList<ArrayList<Edge>> adjIn, adjOut;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		adjIn = new ArrayList<>(n + 1);
		adjOut = new ArrayList<>(n + 1);
		for (i = 0; i <= n; i++) {
			adjIn.add(new ArrayList<>());
			adjOut.add(new ArrayList<>());
		}
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			adjIn.get(b).add(new Edge(a, weight)); // 역방향 간선
			adjOut.get(a).add(new Edge(b, weight)); // 정방향 간선
		}
		distanceIn = new int[n + 1]; // 역방향 거리
		distanceOut = new int[n + 1]; // 정방향 거리
		inQueue = new boolean[n + 1];
		q = new ArrayDeque<>();
		spfa(n, x, distanceIn, inQueue, q, adjIn); // 역방향 SPFA
		spfa(n, x, distanceOut, inQueue, q, adjOut); // 정방향 SPFA
		ans = 0;
		for (i = 1; i <= n; i++) {
			if (distanceIn[i] + distanceOut[i] > ans) {
				ans = distanceIn[i] + distanceOut[i]; // 거리합 최대값 출력
			}
		}
		System.out.print(ans);
	}
}
