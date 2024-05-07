import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * DP
 * i - 각 단원, j - 시간 dp[i][j] = 해당 단원을 j시간을 했을 때 가질 수 있는 최대 점수
 */
public class Main {
    private static class Danwon {
        int k;
        int s;

        public Danwon(int k, int s) {
            this.k = k;
            this.s = s;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n + 1][t + 1];

        Danwon[] danwons = new Danwon[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            danwons[i] = new Danwon(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 1; i <= n; i++) {
            Danwon d = danwons[i - 1];
            for (int j = 0; j <= t; j++) {

                if (j >= d.k) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j-d.k] + d.s);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[n][t]);
    }


}
