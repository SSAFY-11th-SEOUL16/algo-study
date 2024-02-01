import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ01149_RGB거리 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] array = new int[n][3];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n][3];
        dp[0][0] = array[0][0];
        dp[0][1] = array[0][1];
        dp[0][2] = array[0][2];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + array[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + array[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + array[i][2];
        }
        System.out.println(Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2])));
    }
}