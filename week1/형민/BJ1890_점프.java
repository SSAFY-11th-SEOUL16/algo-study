import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int arr[][] = new int[n+1][n+1];
        long dp[][] = new long[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[1][1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(arr[i][j] == 0) break;
                if(dp[i][j] == 0) continue;

                int nx = i + arr[i][j];
                int ny = j + arr[i][j];

                if(nx <= n) dp[nx][j] += dp[i][j];
                if(ny <= n) dp[i][ny] += dp[i][j];
            }
        }

        System.out.println(dp[n][n]);
    }
}
