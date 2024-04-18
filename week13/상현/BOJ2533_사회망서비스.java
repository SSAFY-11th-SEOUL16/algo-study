import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * dp, 트리
 * 
 */
public class Main {
    private static List<Integer>[] trees;
    private static boolean[] visit;
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();


        int n = Integer.parseInt(st.nextToken());

        trees = new ArrayList[n + 1];
        visit = new boolean[n + 1];
        dp = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            trees[i] = new ArrayList<>();
            dp[i][1] = 1;
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            trees[a].add(b);
            trees[b].add(a);
        }

        func(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void func(int node) {
        visit[node] = true;

        for (Integer child : trees[node]) {
            if (!visit[child]) {
                func(child);
                //자신이 얼리어댑터가 아닐경우 자식 모두가 얼리어야 됨
                dp[node][0] += dp[child][1];
                //자신이 얼리어댑터라면 자식이 얼리어댑터 or 아닐 수도 있음
                dp[node][1] += Math.min(dp[child][0], dp[child][1]);
            }
        }
    }

}
