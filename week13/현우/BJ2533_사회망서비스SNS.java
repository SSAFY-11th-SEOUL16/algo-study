import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2533_사회망서비스SNS {
	
	/**
	 * 740 ms
	 * MVC (Minimum Vertex Cover)
	 * 트리에서 MVC
	 * 1. 리프 노드는 커버에 포함 시키지 않는 것이 항상 이득
	 * 2. 자식 노드가 모두 커버에 포함된 경우
	 *    해당 노드 포함 시키지 않음
	 * 3. 그 외 노드는 커버에 포함시킴
	 */
	
	private static final class Node {
		int idx;
		Node next;
		
		Node(int idx, Node next) {
			this.idx = idx;
			this.next = next;
		}
	}
	
	private static int cnt;
	private static Node[] adj;
	
	private static boolean isCovered(int node, int parent) {
		boolean cover;
		Node child;
		
		cover = false;
		for (child = adj[node]; child != null; child = child.next) {
			if (child.idx != parent && !isCovered(child.idx, node)) {
				cover = true; // 자식 노드중 커버에 포함되지 않은 자식 존재 -> 현재 노드를 커버에 포함시킴
			}
		}
		if (cover) {
			cnt++; // MVC 카운트
		}
		return cover;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, u, v, i;
		
		n = Integer.parseInt(br.readLine());
		adj = new Node[n + 1];
		for (i = 1; i < n; i++) { // 간선 입력
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			adj[u] = new Node(v, adj[u]); // LinkedList
			adj[v] = new Node(u, adj[v]);
		}
		cnt = 0;
		isCovered(1, 0); // DFS
		System.out.print(cnt); // MVC 출력
	}
}
