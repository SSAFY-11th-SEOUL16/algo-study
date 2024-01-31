package algorithm.baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        int[][] wv = new int[N][2];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                wv[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        /*
            dp[k] : k무게로 i개의 물건을 가져갈 때 최대 가치

            1) 맨 처음 물건부터 k 무게로 살 수 있을때 최대 가치 저장
            2) 현재 물건의 무게가 w, 가치가 v라면
                - k를 [K,w]까지 아래 식 적용
                - dp[k] = Math.max(dp[k],v+dp[k-w]);
                    - k무게로 현재 물건을 샀을 때와 그러지 않았을 때를 비교
            3) dp 최댓값 dp[K] 반환
        */
        
        int[] dp = new int[K+1];

        for (int i = 0; i < N; i++) {
            // i개의 물건을 사는 경우
            int w = wv[i][0];
            int v = wv[i][1];
            for (int k = K; k >= w; k--) {
                dp[k] = Math.max(dp[k],v+dp[k-w]);
            }
        }
        System.out.println(dp[K]);
    }
}