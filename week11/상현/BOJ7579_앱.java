import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * dp
 * i는 앱을 의미 j는 비용
 *      0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
 *   1  0 0 0 30 30 30 30 30 30 30 30 30 30 30
 *   2  10 10 10 40 40 40 --
 *   3  10 10 10 40 40 40 60 60 60 --
 *   4  10 10 10 40 40 45 60 60 --
 *   5
 * i번 앱을 비활성화 했을 때의 비용과 확보 메모리와 i - 1번째의 각 비용들을 비교하여 더 큰 값을 채움
 * 적은 비용으로 많은 메모리를 확보해야 하기 때문에
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] memory = new int[N + 1];
        int[] costs = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }
        int costTotal = 0;
        int min = Integer.MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
            costTotal += costs[i];
            if (memory[i] >= M) {
                min = Math.min(min, costs[i]);
            }
        }

        int[][] dp = new int[N + 1][costTotal + 1];

        for (int i = 1; i <= N; i++) {
            int mem = memory[i];
            int cost = costs[i];
            for (int j = 0; j <= costTotal; j++) {
                if (j >= cost) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost] + mem);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

                if (dp[i][j] >= M) {
                    min = Math.min(min, j);
                }
            }
        }
        System.out.println(min);
    }
}
