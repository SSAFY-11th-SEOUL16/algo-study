import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputLine = br.readLine().split(" ");
        int N = Integer.parseInt(inputLine[0]);
        int M = Integer.parseInt(inputLine[1]);

        int[][] maps = new int[N][N];
        int[][] dp = new int[N + 1][N + 1];

        for (int i = 0; i < N; i++) {
            String[] mapLine = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(mapLine[j]);
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1] + maps[i - 1][j - 1];
            }
        }

        for (int i = 0; i < M; i++) {
            String[] queryLine = br.readLine().split(" ");
            int x1 = Integer.parseInt(queryLine[0]);
            int y1 = Integer.parseInt(queryLine[1]);
            int x2 = Integer.parseInt(queryLine[2]);
            int y2 = Integer.parseInt(queryLine[3]);

            int result = dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1];
            System.out.println(result);
        }
    }
}
