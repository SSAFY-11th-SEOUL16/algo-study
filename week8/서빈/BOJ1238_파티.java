import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 180 ms
 * 가장 오래 걸리는 학생의 소요시간 계산함
 * 
 * 다익스트라
 * 최단 경로
 * 
 * 파티 장소로 가는 최단 거리와 파티 장소에서 돌아오는 최단 거리를 구하기 위하여 
 * 정방향 그래프와 역방향 그래프를 사용
 * 최단 경로를 찾기 위해 다익스트라 알고리즘을 사용
 * 갔다왔다의 합 중 최댓값을 구함
 */
public class BOJ1238_파티 {
	static class Node implements Comparable<Node> {
		int end;
		int weight;

		public Node(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {	
			return Integer.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N, M, X, dist[], reverse_dist[];
		ArrayList<Node> adj[], reverse_adj[];
		
		N = Integer.parseInt(st.nextToken()); // 마을의 수 N
		M = Integer.parseInt(st.nextToken()); // 단방향 도로의 수 M
		X = Integer.parseInt(st.nextToken()); // 파티 장소 X

		adj = new ArrayList[N + 1];	
		reverse_adj = new ArrayList[N + 1];	// 방향 반대

		dist = new int[N + 1];	// 파티 장소로 가는 최단 거리 저장
		reverse_dist = new int[N + 1];	// 파티 장소에서 돌아오는 최단 거리 저장

		Arrays.fill(dist, Integer.MAX_VALUE);	// 최솟값을 구하기 위함
		Arrays.fill(reverse_dist, Integer.MAX_VALUE);

		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
			reverse_adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adj[start].add(new Node(end, weight));
			reverse_adj[end].add(new Node(start, weight));
		}

		dijkstra(adj, dist, X);	// 파티 장소(X)로 가는 최단 경로 계산
		dijkstra(reverse_adj, reverse_dist, X);	// 파티 장소에서 돌아오는 최단 경로 계산

		int max = 0;
		for (int i = 1; i <= N; i++) {	// 가장 오래 걸리는 학생의 소요시간(최댓값) 계산
			max = Math.max(max, dist[i] + reverse_dist[i]);
		}

		System.out.println(max);
	}

	public static void dijkstra(ArrayList<Node> adj[], int[] dist, int start) {	// 각 마을로 돌아오는 최단 경로를 찾기 위함	
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		dist[start] = 0;

		while (!pq.isEmpty()) {
			Node current = pq.poll();
			for (Node next : adj[current.end]) {	// 인접한 노드들을 탐색
				if (dist[next.end] > dist[current.end] + next.weight) {	// 현재 노드를 거쳐서 가는 것이 더 짧은 경우
					dist[next.end] = dist[current.end] + next.weight;	// 거리를 업데이트!
					pq.add(new Node(next.end, dist[next.end]));	// 우선순위 큐에 추가
				}
			}
		}
	}
}