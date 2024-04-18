import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 실패...
 * dp + dfs로 시도
 */
class Solution {
    private static List<Integer>[] trees;
    private static int[][] dp;
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        int answer = 0;

        trees = new ArrayList[n + 1];
        dp = new int[n + 1][k];
        for (int i = 1; i <= n; i++) {
            trees[i] = new ArrayList<>();
            Arrays.fill(dp[i], Integer.MAX_VALUE - k);
        }

        for (int i = 0; i < m; i++) {
            trees[edge_list[i][0]].add(edge_list[i][1]);
            trees[edge_list[i][1]].add(edge_list[i][0]);
        }

        answer = func(gps_log[0], 0,gps_log, k);

        if (answer == Integer.MAX_VALUE - k) answer = -1;
        System.out.println(answer);
        return answer;
    }

    private static int func(int node, int time, int[] gpsLog, int k) {
        if (time == k - 1) {
            if (gpsLog[time] == node) return 0;
            return dp[node][time];
        }

        if (dp[node][time] != Integer.MAX_VALUE-k) return dp[node][time];

        int diff = 1;
        int result = dp[node][time];
        if (gpsLog[time] == node) diff = 0;

        for (Integer next : trees[node]) {
            result = Math.min(result, func(next, time + 1, gpsLog, k) + diff);
        }

        dp[node][time] = result;
        return dp[node][time];
    }




    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        StringBuilder sb = new StringBuilder();
//        int N = Integer.parseInt(st.nextToken());

        Solution s = new Solution();

        s.solution(7,10, new int[][] {{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}},6, new int[]{1,2,3,3,6,7});
    }
}