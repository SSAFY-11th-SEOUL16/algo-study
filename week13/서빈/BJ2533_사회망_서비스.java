import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
- 2132 ms

- DFS, DP

- 각 노드마다 DFS를 통해 해당 노드가 얼리 어댑터일 때와 아닐 때의 최소 얼리 어댑터 수를 계산
    - 현재 노드가 얼리 어댑터가 아닌 경우, 해당 자식 노드는 얼리 어댑터여야 함
    - 현재 노드가 얼리 어댑터인 경우, 해당 자식 노드는 얼리 어댑터일 수도 아닐 수도 있음
- 부모 노드에서 자식 노드로의 전파를 고려하여 계산
 */

public class BJ2533_사회망_서비스 {
	static int N, dp[][];
	static boolean[] visited;
	static List<Integer>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1][2];
		visited = new boolean[N + 1];
		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		StringTokenizer st = null;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}

		dfs(1);
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}

	public static void dfs(int index) {
		visited[index] = true;
		dp[index][0] = 0; // 얼리 아답터가 아닌 경우
		dp[index][1] = 1; // 얼리 아답터인 경우

		for (int child : graph[index]) {
			if (!visited[child]) {
				dfs(child);
				dp[index][0] += dp[child][1]; // 현재 노드가 얼리 어댑터가 아닌 경우, 해당 자식 노드는 얼리 어댑터여야 함
				dp[index][1] += Math.min(dp[child][0], dp[child][1]); // 현재 노드가 얼리 어댑터인 경우, 해당 자식 노드는 얼리 어댑터일 수도 아닐 수도 있음												// 있음
			}
		}
	}
}