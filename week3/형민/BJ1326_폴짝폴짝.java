import java.util.*;
import java.io.*;

public class Main {
    static int n, a, b, min = Integer.MAX_VALUE;
    static int arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        visited = new boolean[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        bfs();

        if(min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }

    static boolean visited[];
    static void bfs() {
        Deque<int[]> q = new ArrayDeque<>();
        q.addLast(new int[]{a,0});

        while(!q.isEmpty()) {
            int cur[] = q.pollFirst();

            if(cur[0] == b) {
                min = Math.min(min, cur[1]);
                return;
            }

            int stack;
            int i = 1;
            while(true) {
                stack = 0;
                int nx1 = cur[0] + arr[cur[0]] * i;
                int nx2 = cur[0] - arr[cur[0]] * i;

                if(nx1 <= n) {
                    visited[nx1] = true;
                    q.addLast(new int[]{nx1, cur[1] + 1});
                    visited[nx1] = false;
                } else stack++;
                if(nx2 > 0) {
                    visited[nx2] = true;
                    q.addLast(new int[]{nx2, cur[1] + 1});
                    visited[nx2] = false;
                } else stack++;

                if(stack == 2) break;
                i++;
            }
        }
    }
}

