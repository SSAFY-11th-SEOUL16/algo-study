import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * DP
 * 104ms
 * 입력을 받으면서 해당 블록의 높이부터 H까지 이전사람의 특정 높이 - 현재 블록 높이를 빼준값을 현재 사람의 DP 테이블에 채워줌
 * 해당 사람의 입력을 다 받은 후 이전 사람의 높이 + 입력을 받으면서 얻은 경우의 수를 더해줌
 */
public class BOJ18427_함께블록쌓기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][H + 1];

        dp[0][0] = 1;
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            dp[i][0] = 1;
            while(st.hasMoreTokens()){
                int nowCoin = Integer.parseInt(st.nextToken());

                //j = 높이
                for (int j = nowCoin; j <= H; j++) {
                    //현재 사람이 J 높이의 블록을 쌓는데 나오는 경우의 수 = 이전 사람까지의 J - 입력 받은 높이
                    //해당 테케를 예시로 첫번째 사람은 2를 들고 있었으므로 높이가 3인 블록을 두번째 사람이 가질 경우 J = 5일때 이전 사람까지의 5 - 현재 블럭값은 1이므로 5에 1이 입력됨
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - nowCoin]) % 10007;
                }

            }
            //입력을 받으면서 현재 사람의 블록과 이전 사람까지의 블록을 합쳐줌
            for (int j = 1; j <= H; j++) {
                dp[i][j] = (dp[i][j] + dp[i - 1][j]) % 10007 ;
            }
        }
        System.out.println(dp[N][H]);
    }

}
