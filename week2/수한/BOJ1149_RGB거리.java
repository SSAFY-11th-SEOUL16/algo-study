package algorithm.baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1149 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] costs = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                costs[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        /*
            dp[i][c] : i번째 집을 c로 칠해을 때의 최솟값

            dp[i][c] = costs[i][c] + min(dp[i+1][(c-1)%3],dp[i+1][(c+1)%3]);

            dp[N-1][c] = costs[N-1][c];
         */

        int[][] dp = new int[N+1][3];
        int[][] nc = {{1,2},{0,2},{0,1}};

        for (int i = N-1; i >= 0 ; i--) {
            for (int c = 0; c < 3; c++) {
                dp[i][c] = costs[i][c] + Math.min(dp[i+1][nc[c][0]],dp[i+1][nc[c][1]]);
            }
        }

        System.out.println(Arrays.stream(dp[0]).min().getAsInt());
    }
}