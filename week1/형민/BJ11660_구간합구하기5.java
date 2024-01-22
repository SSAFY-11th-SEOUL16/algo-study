import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int arr[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long sum = 0;
        long dp[][] = new long[n + 1][n + 1]; //누적 합
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += arr[i][j];
                dp[i + 1][j + 1] = sum;
            }
        }

        //각 행의 인덱스 0번째 열은 전 행까지의 누적 합을 나타냄
        dp[1][0] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i][0] = dp[i - 1][n];
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            long result = 0;
            for (int j = x1; j <= x2; j++) {
                result += (dp[j][y2] - dp[j][y1 - 1]);
            }
            sb.append(result + "\n");
        }

        System.out.println(sb);
    }
}
