import java.util.Scanner;

public class Main {
    static int N;
    static int[] S;
    static int[] W;
    static int max_cnt;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        S = new int[N];
        W = new int[N];
        max_cnt = 0;

        for (int i = 0; i < N; i++) {
            S[i] = scanner.nextInt();
            W[i] = scanner.nextInt();
        }

        backtrack(0, 0);
        System.out.println(max_cnt);
    }

    public static void backtrack(int cur, int cnt) {
        if (cur == N) {
            if (cnt > max_cnt) max_cnt = cnt;
            return;
        }

        if (S[cur] <= 0 || cnt == N - 1) {
            backtrack(cur + 1, cnt);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (i == cur || S[i] <= 0) continue;

            // update S, W
            S[cur] -= W[i];
            S[i] -= W[cur];
            if (S[cur] <= 0) cnt++;
            if (S[i] <= 0) cnt++;

            backtrack(cur + 1, cnt);

            // restore S, W
            if (S[cur] <= 0) cnt--;
            if (S[i] <= 0) cnt--;
            S[cur] += W[i];
            S[i] += W[cur];
        }
    }
}
