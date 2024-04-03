import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * DP
 * i번째에서 시작하여 1번까지 역으로 내려가면서 i번을 그룹핑 했을 때의 값과 j - 1번째의 최대값을 더했을 때
 * 최대값이 되는 경우를 갱신해가면서 1차원 dp 테이블을 채워줌
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];
        int[] dp = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 2; i <= N; i++) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for (int j = i; j >= 1; j--) {
                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j]);
                dp[i] = Math.max(dp[i], max - min + dp[j - 1]);
            }
        }
        System.out.println(dp[N]);
    }
}
