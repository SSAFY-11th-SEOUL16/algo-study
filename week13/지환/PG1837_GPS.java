import java.util.*;

class PG1837_GPS {
    List<Integer>[] graph;
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int[] edge: edge_list) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        
        int[][] dp = new int[k][n+1];
        for(int i=0; i<k; i++) {
            for(int j=1; j<=n; j++) {
                dp[i][j] = k+1;
            }
        }
        
        dp[0][gps_log[0]] = 0;
        for(int t=1; t<k; t++) {
            for(int prev=1; prev<=n; prev++) {
                int tmp = gps_log[t];
                for(int next: graph[prev]) {
                    if(next == tmp) {
                        dp[t][next] = Math.min(dp[t][next], dp[t-1][prev]);
                    } else {
                        dp[t][next] = Math.min(dp[t][next], dp[t-1][prev] + 1);
                    }
                }
            }
        }

        int answer = dp[k-1][gps_log[k-1]] == k+1 ? -1 : dp[k-1][gps_log[k-1]];
        return answer;
    }
    
}
