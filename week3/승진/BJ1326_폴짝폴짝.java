import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static int bfs(int a, int b, int[] bridge, int N) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(a - 1);
        int[] check = new int[N];
        for (int i = 0; i < N; i++) {
            check[i] = -1;
        }
        check[a - 1] = 0;

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int n = node; n < N; n += bridge[node]) {
                if (check[n] == -1) {
                    q.offer(n);
                    check[n] = check[node] + 1;
                    if (n == b - 1) {
                        return check[n];
                    }
                }
            }

            for (int n = node; n >= 0; n -= bridge[node]) {
                if (check[n] == -1) {
                    q.offer(n);
                    check[n] = check[node] + 1;
                    if (n == b - 1) {
                        return check[n];
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[] bridge = new int[N];
        for (int i = 0; i < N; i++) {
            bridge[i] = scanner.nextInt();
        }

        int a = scanner.nextInt();
        int b = scanner.nextInt();

        System.out.println(bfs(a, b, bridge, N));
    }
}
