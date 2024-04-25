package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2342_DanceDanceRevolution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /*
            dp[i][l][r] : i번째에 왼발이 l칸, 오른발이 r칸일 때 힘 최솟값

            다음 칸이 n이어야 한다면
            왼발을 n으로 바꾸거나 오른발을 n으로 바꾸는 경우 존재

            현재 가능한 위치 조합에서
            왼발을 n으로 바꾸었을 때와
            오른발을 n으로 바꾸었을 때 중에
            힘의 최솟값을 저장

            dp[i][l][r] = min(dp[i+1][n][r] + powerL, dp[i+1][l][n] + powerR);
         */

        String input = br.readLine();

        int[] nums = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();

        int N = nums.length;

        int[][] power = new int[5][5]; // power[a][b] a - > b로 바꿀 때 드는 힘

        for (int l = 0; l < 5; l++) {
            for (int r = 0; r < 5; r++) {
                if(l == r) power[l][r] = 1;
                else if(l == 0 || r == 0) power[l][r] = 2;
                else if(Math.abs(l-r) == 2) power[l][r] = 4;
                else power[l][r] = 3;
            }
        }

        int MAX = 1_000_000;
        int[][][] dp = new int[N][5][5];

        for (int i = N-3; i >= 0; i--) {
            int curN = nums[i];
            int nextN = nums[i+1];
            for (int l = 0; l < 5; l++) {
                for (int r = 0; r < 5; r++) {
                    if(l == r || (r != curN && l != curN)) {
                        // 양 발이 같은 위치에 있거나 현재 칸에 없는 경우는 불가능한 경우
                        dp[i][l][r] = MAX;
                    }else{
                        // 왼발을 바꾸었을 때와
                        // 오른발을 바꾸었을 때 중 최솟값 저장
                        dp[i][l][r] = Math.min(dp[i+1][nextN][r] + power[l][nextN], dp[i+1][l][nextN] + power[r][nextN]);
                    }
                }
            }
        }

        System.out.println(dp[0][0][nums[0]]+2);
    }
}