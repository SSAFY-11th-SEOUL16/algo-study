import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ2533_사회망서비스 {
	static List<Integer>[] graph;
	static boolean[] visited;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		graph = new ArrayList[n+1];
		visited = new boolean[n+1];
		dp = new int[n+1][2];
		StringTokenizer st;
		for(int i=1; i<=n; i++) {
			graph[i] = new ArrayList<>();
		}
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		dfs(1);
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}
	
	public static void dfs(int now) {
		visited[now] = true;
		dp[now][0] = 1;
		for(int next: graph[now]) {
			if(visited[next]) continue;
			dfs(next);
			dp[now][0] += Math.min(dp[next][0], dp[next][1]);
			dp[now][1] += dp[next][0];
		}
	}
}
