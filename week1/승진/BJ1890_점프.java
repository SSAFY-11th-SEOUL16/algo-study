import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] maps = new int[N][N];
        long[][] dp = new long[N][N];  // long 자료형으로 변경

        for (int i = 0; i < N; i++) {
            String[] mapRow = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(mapRow[j]);
            }
        }

        dp[0][0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == N - 1 && j == N - 1) {
                    System.out.println(dp[i][j]);
                    return;  // 루프 종료 후 바로 리턴
                }

                if (j + maps[i][j] < N) {
                    dp[i][j + maps[i][j]] += dp[i][j];
                }

                if (i + maps[i][j] < N) {
                    dp[i + maps[i][j]][j] += dp[i][j];
                }
            }
        }
    }
}
