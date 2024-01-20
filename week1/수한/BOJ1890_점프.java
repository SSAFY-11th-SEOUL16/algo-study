package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1890 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        /*
            1. x,y에서 출발 했을 때 도달하는 경우의 수를 저장하는 int 배열 생성
            2. 맨 오른쪽 아래의 왼쪽 (N-1,M-2)부터 왼쪽으로 가며 loop
            3. 해당 칸이 i,j이고 이 칸에 적힌 숫자가 num이라면
                a. i+num, j와 i, j+num에서 출발했을 때 도달 가능한 경우의 수를 더해 int 배열의 i,j에 저장
        */

        long[][] dp = new long[N+9][N+9];
        dp[N-1][N-1] = 1;
        for (int i = N-1; i >= 0; i--) {
            for (int j = N-1; j >= 0; j--) {
                if(i == N-1 && j == N-1) continue;
                int num = map[i][j];
                dp[i][j] = dp[i+num][j] + dp[i][j+num];
            }
        }

        System.out.println(dp[0][0]);
    }
}