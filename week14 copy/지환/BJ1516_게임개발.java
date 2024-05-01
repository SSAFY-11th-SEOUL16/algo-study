import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//Java8, 280ms
//위상정렬과 dp를 활용
public class BJ1516_게임개발 {
	static List<Integer>[] graph;
	static int[] indegree;
	static int[] time;
	static int n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		graph = new ArrayList[n+1];
		
		for(int i=1; i<=n; i++) {
			graph[i] = new ArrayList<>();
		}
		StringTokenizer st;
		indegree = new int[n+1];
		time = new int[n+1];
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			int input;
			while((input=Integer.parseInt(st.nextToken()))!=-1) {
				graph[input].add(i);
				indegree[i]++;
			}
		}
		int[] dp = new int[n+1];
		Arrays.fill(dp, 0);
		tropical(dp);
		for(int i=1; i<=n; i++) {
			System.out.println(dp[i]);
		}
	}
	
	static void tropical(int[] dp) {
		
		Queue<Integer> q = new LinkedList<>();
		for(int i=1; i<=n; i++) {
			if(indegree[i]==0) {
				q.add(i);
				dp[i] = time[i];
			}
		}
		
		while(!q.isEmpty()) {
			int now = q.remove();
			
			for(int next: graph[now]) {
				indegree[next]--;
				if(indegree[next]==0) {
					q.add(next);
				}
				dp[next] = Math.max(dp[now] + time[next], dp[next]);
			}
		}
	}
}
