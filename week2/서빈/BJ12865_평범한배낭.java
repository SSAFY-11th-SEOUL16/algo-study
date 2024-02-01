import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ12865_평범한배낭 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] input = new int[n + 1][2];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            input[i][0] = Integer.parseInt(st.nextToken());
            input[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - input[i][0] >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - input[i][0]] + input[i][1]);
                }
            }
        }
        System.out.println(dp[n][k]);
    }
}