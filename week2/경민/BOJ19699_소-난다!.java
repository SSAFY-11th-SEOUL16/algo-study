import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int[] cows;
    static boolean[] checked;
    static int N, M;
    static TreeSet<Integer> result = new TreeSet<>();

    public boolean isPrime(int num) {
        if (num == 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++)
            if (num % i == 0)
                return false;
        return true;
    }

    public void DFS(int count, int sum, int index) {
        if (count > M) return;
        if (count == M) {
            // 합이 소수인가 체크
            if (isPrime(sum))
                result.add(sum);
        } else {
            for (int i = index; i < N; i++) {
                if (!checked[i]) {
                    checked[i] = true;
                    DFS(count + 1, sum + cows[i], index + 1);
                    checked[i] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cows = new int[N];
        checked = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cows[i] = Integer.parseInt(st.nextToken());
        }

        T.DFS(0, 0, 0);

        if (result.isEmpty()) {
            System.out.println(-1);
        } else {
            for(int i : result)
                System.out.print(i + " ");
        }
    }
}