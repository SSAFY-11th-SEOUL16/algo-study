package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_7579_앱 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        /*
            N개 앱 활성화
            m : m바이트만큼 사용
            c : 비활성화 후 다시 실행 시간

            새로운 앱 B 실행
            M바이트 메모리 필요

            k개 앱 비활성화 -> M바이트 이상 메모리 확보
            c 최소

            dp[i] : 실행 시간이 i인 경우 중 최대 메모리
            현재 메모리가 m, 실행 시간이 c라면

            dp[i] = max(dp[i],dp[i-c] + m);
         */

        tokens = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokens.nextToken());
        int M = Integer.parseInt(tokens.nextToken());

        int[] ms = new int[N];
        int[] cs = new int[N];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ms[i] = Integer.parseInt(tokens.nextToken());
        }

        tokens = new StringTokenizer(br.readLine());
        int maxC = 0;
        for (int i = 0; i < N; i++) {
            cs[i] = Integer.parseInt(tokens.nextToken());
            maxC += cs[i];
        }

        int[] dp = new int[maxC+1];

        for (int i = 0; i < N; i++) {
            int m = ms[i];
            int c = cs[i];
            for (int j = maxC; j >= c; j--) {
                dp[j] = Math.max(dp[j],dp[j-c] + m);
            }
        }

        for (int i = 0; i <= maxC; i++) {
            if(dp[i] >= M){
                System.out.println(i);
                break;
            }
        }
    }
}