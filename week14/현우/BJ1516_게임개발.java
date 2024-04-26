import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 176 ms
 * 위상 정렬
 * degree 0인 노드부터 탐색
 * 자식 노드의 시간을 부모 노드의 시간 중 최대 값으로 갱신
 * degree가 0이 되면 해당 건물을 짓는데 걸리는 시간 추가
 * */
public class BJ1516_게임개발 {
	private static final int END = -1;
	
	private static final class Node {
		int idx;
		Node next;
		
		Node(int idx, Node next) {
			this.idx = idx;
			this.next = next;
		}
	}
	
	private static int n;
	private static int[] ans; // 각 건물이 완성되는 시각
	private static int[] time; // 각 건물을 짓는데 걸리는 시간
	private static int[] degree;
	private static Node[] adj;
	private static BufferedReader br;
	
	private static final void inputAdj() throws IOException {
		int i;
		int parent;
		StringTokenizer st;
		
		adj = new Node[n + 1];
		degree = new int[n + 1];
		time = new int[n + 1];
		for (i = 1; i <= n; i++) { // 간선 입력
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			while ((parent = Integer.parseInt(st.nextToken())) != END) {
				adj[parent] = new Node(i, adj[parent]);
				degree[i]++; // 자식 노드 degree 증가
			}
		}
	}
	
	private static final void dfs(int curr) {
		Node next;
		
		for (next = adj[curr]; next != null; next = next.next) { // 자식 노드 순회
			ans[next.idx] = Math.max(ans[next.idx], ans[curr]); // 부모 노드의 시간 중 최대 값으로 갱신
			if (--degree[next.idx] == 0) { // degree 0 도달
				ans[next.idx] += time[next.idx]; // 건물 짓는데 걸리는 시간 추가
				dfs(next.idx); // 자식 노드 탐색
			}
		}
	}
	
	private static final void topoSort() { // 위상 정렬
		int i;
		ArrayDeque<Integer> q;
		
		ans = new int[n + 1];
		q = new ArrayDeque<>();
		for (i = 1; i <= n; i++) {
			if (degree[i] == 0) { // dgree 0인 노드
				ans[i] = time[i]; // 건물 짓는 시간 = 완성 시각
				q.addLast(i); // 큐에 추가
			}
		}
		while (!q.isEmpty()) {
			dfs(q.pollFirst()); // 큐에 있는 노드들부터 탐색
		}
	}
	
	private static final void output() { // 출력
		int i;
		StringBuilder sb;
		
		sb = new StringBuilder();
		for (i = 1; i <= n; i++) {
			sb.append(ans[i]).append('\n');
		}
		System.out.print(sb);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		inputAdj(); // 간선 입력
		topoSort(); // 위상 정렬
		output(); // 출력
	}
}
