import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        //[x][y] => x번 과목까지 고려했을때 y시간 동안의 최대 점수
        int dp[][] = new int[n + 1][t + 1];
        int chap[][] = new int[n + 1][2];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            chap[i][0] = Integer.parseInt(st.nextToken()); //예상 공부 시간
            chap[i][1] = Integer.parseInt(st.nextToken()); //배점
        }

        for (int i = 1; i <= n; i++) {
            int curTime = chap[i][0];
            int curScore = chap[i][1];
            for (int j = 1; j <= t; j++) {
                if(curTime > j) dp[i][j] = dp[i - 1][j];
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - curTime] + curScore);
                }
            }
        }

        System.out.println(dp[n][t]);
    }
}
