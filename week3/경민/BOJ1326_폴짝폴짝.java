import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, a, b;
    static int[] arr, count;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        count = new int[N + 1];
        visited = new boolean[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        bfs();

        if (count[b] == 0) System.out.println(-1);
        else System.out.println(count[b]);
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(a);
        visited[a] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur == b) return;

            for (int i = cur + arr[cur]; i <= N; i += arr[cur]) {
                if (visited[i]) continue;
                visited[i] = true;
                count[i] = count[cur] + 1;
                q.offer(i);
            }

            for (int i = cur - arr[cur]; i >= 1; i -= arr[cur]) {
                if (visited[i]) continue;
                visited[i] = true;
                count[i] = count[cur] + 1;
                q.offer(i);
            }
        }
    }
}
