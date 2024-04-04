package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2229_조짜기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        /*
            실력차이 많이 나도록
         */

        int N = Integer.parseInt(br.readLine());
        tokens = new StringTokenizer(br.readLine());

        int[] scores = new int[N];
        for (int i = 0; i < N; i++) {
            scores[i] = Integer.parseInt(tokens.nextToken());
        }

        int[] dp = new int[N+1];
        int minScore, maxScore;
        for (int i = N-2; i >= 0; i--) {
            minScore = maxScore = scores[i];
            dp[i] = dp[i+1]; // 현재 학생 혼자조일 때
            for (int j = i+1; j < N; j++) {
                // j번째 학생까지 조일 때
                int score = scores[j];
                if(score < minScore){
                    minScore = score;
                }else if(score > maxScore){
                    maxScore = score;
                }else{
                    break;
                }
                int diff = maxScore - minScore;
                dp[i] = Math.max(dp[i], diff + dp[j+1]);
            }
        }

        System.out.println(dp[0]);
    }
}