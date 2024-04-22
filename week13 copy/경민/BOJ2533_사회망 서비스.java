import java.util.*;
import java.io.*;

public class Main {

    /*
       각 노드 별로 얼리어답터거나 일반인이거나 두 가지 state 밖에 없다.
       두 state는 완전히 독립적으로 dp 적용이 가능

       dp[node][0] : 해당 노드가 얼리어답터인 경우, 본인 노드 + 모든 자식노드 중 문제 조건을 만족하는 최소 얼리어답터 수
       dp[node][1] : 해당 노드가 일반인일 경우, 본인 노드 + 모든 자식노드 중 문제 조건을 만족하는 최소 얼리어답터 수

       부모 노드가 얼리어답터인 경우, 자식노드는 얼리어답터일 경우 or 일반인일 경우 중 더 작은 값을 골라 더해줘야 함
       부모 노드가 일반인인 경우, 자식노드가 전부 얼리어답터여야 하므로 자식노드가 얼리어답터일 경우를 더해줘야 함
     */

    static int n, dp[][];
    static boolean[] visited;
    static List<Integer>[] edges;

    static void dfs(int node) {
        visited[node] = true;
        dp[node][0] = 1;

        for (int child : edges[node]) {
            if (!visited[child]) {
                dfs(child);
                dp[node][1] += dp[child][0];
                dp[node][0] += Math.min(dp[child][0], dp[child][1]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1][2];
        edges = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            edges[u].add(v);
            edges[v].add(u);
        }

        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));

    }
}
