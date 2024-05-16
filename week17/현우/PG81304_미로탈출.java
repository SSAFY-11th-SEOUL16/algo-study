import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 16.84 ms
 * 비트필드 + 다익스트라
 * 트랩 밟은 곳 toggle 값에 비트로 표시
 * distance[방 번호][toggle] 기준으로 다익스트라
 * 간선은 정방향, 역방향 모두 입력 받고
 * from, to의 toggle 여부와 역방향 여부에 따라 활성/비활성
 * */
public class PG81304_미로탈출 {
	private static final int INF = Integer.MAX_VALUE >> 1;
	
	private static final class Edge {
		int to;
		int from;
		int weight;
		boolean backward;
		Edge next;
		
		Edge(int from, int to, int weight, Edge next, boolean backward) {
			this.from = from;
			this.to = to;
			this.weight = weight;
			this.next = next;
			this.backward = backward;
		}
		
		final boolean off(int toggle) {
			boolean to;
			boolean from;
			
			from = isTrap[this.from] ? (((toggle >> trapIdx[this.from]) & 1) == 0) : true; // 이전 방 토글 여부
			to = isTrap[this.to] ? (((toggle >> trapIdx[this.to]) & 1) == 0) : true; // 다음 방 토글 여부
			return from ^ to ^ backward; // from, to의 toggle 여부와 역방향 여부에 따라 활성/비활성
		}
	}
	
	private static final class Node implements Comparable<Node> {
		int idx;
		int toggle;
		
		Node(int idx, int toggle) {
			this.idx = idx;
			this.toggle = toggle;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(distance[this.idx][this.toggle], distance[o.idx][o.toggle]);
		}
	}
	
	private static int[] trapIdx;
	private static int[][] distance;
	private static boolean[] isTrap;
	private static Edge[] adj;
	
	private static final int dijkstra(int start, int end) {
		int curr;
		int next;
		int weight;
		int toggle;
		int nextToggle;
		Node node;
		Edge edge;
		PriorityQueue<Node> pq;
		
		distance[start][0] = 0; // 시작 지점 거리
		pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0)); // pq에 시작 시점 삽입
		while (!pq.isEmpty()) {
			node = pq.poll();
			curr = node.idx; // 현재 방 번호
			toggle = node.toggle; // toggle 정보
			if (curr == end) { // 도착 방 도달
				return distance[curr][toggle]; // 저장된 거리 반환
			}
			for (edge = adj[curr]; edge != null; edge = edge.next) {
				if (edge.off(toggle)) { // 간선이 비활성화 된 경우
					continue; // 다음 간선 탐색
				}
				next = edge.to; // 다음 방 번호
				weight = edge.weight; // 간선 길이
				if (isTrap[next]) { // 다음 방이 트랩인 경우
					nextToggle = toggle ^ (1 << trapIdx[next]); // toggle의 다음 방 비트 XOR
					if (distance[curr][toggle] + weight < distance[next][nextToggle]) { // 새로 계산된 거리가 짧으면
						distance[next][nextToggle] = distance[curr][toggle] + weight; // 거리 업데이트
						pq.offer(new Node(next, nextToggle)); // pq에 다음 방 삽입
					}
					continue;
				} // 다음 방이 트랩이 아닌 경우
				if (distance[curr][toggle] + weight < distance[next][toggle]) { // 새로 계산된 거리가 짧으면
					distance[next][toggle] = distance[curr][toggle] + weight; // 거리 업데이트
					pq.offer(new Node(next, toggle)); // pq에 다음 방 삽입
				}
			}
		}
		return 0;
	}
	
	public int solution(int n, int start, int end, int[][] roads, int[] traps) {
		int i;
		int idx;

		idx = 0;
		trapIdx = new int[n + 1];
		isTrap = new boolean[n + 1];
		for (int trap : traps) {
			trapIdx[trap] = idx++; // 트랩들은 따로 인덱스 부여
			isTrap[trap] = true; // 트랩 여부
		}
		adj = new Edge[n + 1];
		for (int[] edge : roads) {
			adj[edge[0]] = new Edge(edge[0], edge[1], edge[2], adj[edge[0]], false); // 정방향 간선
			adj[edge[1]] = new Edge(edge[1], edge[0], edge[2], adj[edge[1]], true); // 역방향 간선
		}
		distance = new int[n + 1][1 << traps.length];
		for (i = 1; i <= n; i++) {
			Arrays.fill(distance[i], INF); // 거리 초기화
		}
		return dijkstra(start, end); // 다익스트라
	}
}
