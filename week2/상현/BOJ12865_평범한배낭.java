package baekjoon.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12865_평범한배낭 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //무게
        int[] w = new int[N + 1];
        //가치
        int[] v = new int[N + 1];

        //각각의 무게, 가치 입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        //dp 배열
        int[][] dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                //이전의 값 저장
                dp[i][j] = dp[i - 1][j];

                if (j - w[i] >= 0) { //남는 무게가 있다면
                    //현재 값 = 이전의 지금 j 무게까지 값, 이전의 물품 j -w[i] 즉 현재 물품의 무게를 뺐을 때의 값 + 자신의 값어치
                    //두개를 비교해서 큰 값을 대입  j -w[i]가 의미하는 것은 쉽게 말해 i번째의 물건을 넣기 전까지의 가장 큰 값어지

                    //예시로 3번째 물품을 넣는데 무게가 5인 상황일 때 i = 3 j = 5 라면 자신의 무게가 3일경우 즉 w[i] = 3일 경우
                    //2번째 물품을 넣는 경우에서 5-3의 무게 즉 2번째 물품에서 무게 2인 케이스의 값어치 값에 자신의 값어치 값을 더한다.
                    //위에서 나온 값과 이전에 무게 5까지의 값을 비교해서 큰 값을 넣어준다.
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - w[i]] + v[i]);
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
