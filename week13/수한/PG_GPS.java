package algorithm.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution_Kakao_2017_카카오코드_본선_GPS {
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        /*
            n : 거점 개수 <= 200
            m : 도로 개수 <= 10,000
            edge_list : 연결된 도로 정보
            k : 거점 정보 총 개수 <= 100
            gps_log : 거점 정보
            
            dp[i][j] : i초에 j지점인 경우 변경 최솟값
            
            dp[i][j] = Math.min(dp[i][j],dp[i+1][c]);
            if(gps_log[i] != j) dp[i][j]++;
        */
        List<Integer>[] adjList = new ArrayList[n+1];

        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int[] edge = edge_list[i];
            int a = edge[0];
            int b = edge[1];
            adjList[a].add(b);
            adjList[b].add(a);
        }

        int MAX = 1_000_000_000;
        int[][] dp = new int[k][n+1];
        
        int ed = k-1;
        Arrays.fill(dp[ed],MAX);
        dp[ed][gps_log[ed]] = 0;
        
        for(int i = ed-1; i >= 0; i--){
            for(int j = 1; j <= n; j++){
                dp[i][j] = MAX;
                for(int c : adjList[j]){
                    dp[i][j] = Math.min(dp[i][j],dp[i+1][c]);
                }
                if(gps_log[i] != j) dp[i][j]++;
            }
        }

        return dp[0][gps_log[0]] >= MAX ? -1 : dp[0][gps_log[0]];
    }
}