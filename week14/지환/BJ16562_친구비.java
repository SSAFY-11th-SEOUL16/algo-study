import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//Java8, 196ms
//한 정점으로부터 완전탐색 후 최솟값 구하기
//정점을 도며 위의 작업 반복
public class BJ16562_친구비 {
	static int sum = 0;
	static List<Integer>[] graph;
	static boolean[] visited;
	static int[] money;
	static int min;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		money = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			money[i] = Integer.parseInt(st.nextToken());
		}
		
		graph = new ArrayList[n+1];
		visited = new boolean[n+1];
		for(int i=1; i<=n; i++) {
			graph[i] = new ArrayList<>();
		}
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		for(int i=1; i<=n; i++) {
			min = Integer.MAX_VALUE;
			if(!visited[i]) {
				dfs(i);
				sum += min;
			}
		}
		
		if(sum <= k) {
			System.out.println(sum);
		} else {
			System.out.println("Oh no");
		}
	}
	
	public static void dfs(int now) {
		if(visited[now]) return;
		visited[now] = true;
		min = Math.min(money[now], min);
		
		for(int next: graph[now]) {
			dfs(next);
		}
	}
	
}
