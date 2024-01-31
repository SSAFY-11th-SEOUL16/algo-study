import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int houseCount = Integer.parseInt(br.readLine());

        int[][] dp = new int[houseCount][3];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < 3; j++) {
            dp[0][j] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < houseCount; i++) {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            dp[i][0] = Math.min(dp[i - 1][1] + R, dp[i - 1][2] + R);
            dp[i][1] = Math.min(dp[i - 1][0] + G, dp[i - 1][2] + G);
            dp[i][2] = Math.min(dp[i - 1][0] + B, dp[i - 1][1] + B);
        }

        int result = Math.min(dp[houseCount - 1][0], Math.min(dp[houseCount - 1][1], dp[houseCount - 1][2]));
        System.out.println(result);
    }
}
