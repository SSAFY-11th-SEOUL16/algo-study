import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 풀이 참고!!!! DP
 * i = DDR 입력받은 순서
 * J = 왼쪽발 K = 오른쪽 발
 * 왼발 이동시 [L][다음번 밟을 발판] 오른발 이동시 [다음번 밟을 발판][R] (?)
 */
public class Main {
    private static int[][][] dp;
    private static List<Integer> arr = new ArrayList<>();
    static int[][] power = {
            {1, 2, 2, 2, 2},
            {0, 1, 3, 4, 3},
            {0, 3, 1, 3, 4},
            {0, 4, 3, 1, 3},
            {0, 3, 4, 3, 1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        while (true) {
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) break;
            arr.add(n);
        }

        dp = new int[arr.size()][5][5];

        int result = func(0,0,0);

        System.out.println(result);
    }

    private static int func(int idx, int l, int r) {
        if (idx == arr.size()) {
            return 0;
        }

        if (dp[idx][l][r] != 0) return dp[idx][l][r];

        int next = arr.get(idx);

        return dp[idx][l][r] = Math.min(func(idx + 1, next,r) + power[l][next], func(idx + 1, l,next) + power[r][next]);
    }
}
