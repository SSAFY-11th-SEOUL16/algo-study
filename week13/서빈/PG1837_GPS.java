import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PG1837_GPS {
	static int N, M, K, dp[][];
	static int[] route;
	static List<Integer>[] graph;

	public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
		int answer = 0;
		N = n;
		M = m;
		K = k;
		route = new int[N + 1];
		dp = new int[N + 1][K + 1];
		route = gps_log;

		graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        dp = new int[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }
        
		for (int[] edge : edge_list) {
            int from = edge[0];
            int to = edge[1];
            graph[from].add(to);
            graph[to].add(from); 
        }
		
		dp[1][0] = 0;
		
		answer = solve(route[0], 0);
		return answer == Integer.MAX_VALUE ? -1 : answer;
	}

	public int solve(int index, int time) {
		if (time == K - 1) {
			if (index == route[time]) {
				return 0;
			} else {
				return Integer.MAX_VALUE;
			}
		}

		if (dp[index][time] != -1)
			return dp[index][time];

		int result = Integer.MAX_VALUE;

		for (int i = 0; i < graph[index].size(); i++) {
			int next = graph[index].get(i);
			if (route[time + 1] == next) {
				result = Math.min(result, solve(next, time + 1));
			} else {
				result = Math.min(result, solve(next, time + 1) + 1);
			}
		}
		return dp[index][time] = result;
	}
}