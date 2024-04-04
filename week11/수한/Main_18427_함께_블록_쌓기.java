package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_18427_함께_블록_쌓기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        /*
            2 3 5
            3 5
            1 2 3

            0 1 2 3 4 5
            1 0 1 1 0 1
            1 0 1 2 0 3
            1 1 1 3 2 3

            i번 학생의 j번째 블록, h의 길이인 경우,
            0부터 H-h까지 검사
            dp[i][k] += dp[i-1][k-h];

         */
        tokens = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokens.nextToken());
        int M = Integer.parseInt(tokens.nextToken());
        int H = Integer.parseInt(tokens.nextToken());

        int MOD = 10007;

        int[][] dp = new int[N+1][H+1];
        dp[0][0] = 1;
        for (int i = 1; i <= N; i++) {
            tokens = new StringTokenizer(br.readLine());
            dp[i] = dp[i-1].clone();
            while(tokens.hasMoreTokens()){
                int h = Integer.parseInt(tokens.nextToken());
                for (int j = 0; j <= H-h; j++) {
                    int nh = j+h;
                    dp[i][nh] = (dp[i][nh] + dp[i-1][j]) % MOD;
                }
            }
        }

        System.out.println(dp[N][H]);

    }
}