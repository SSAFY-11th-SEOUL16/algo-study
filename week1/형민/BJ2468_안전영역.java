import java.io.*;
import java.util.*;

public class Main {
    static int n, depth;
    static int arr[][];
    static boolean[][] visited;
    static int result = Integer.MIN_VALUE;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i <= 100; i++) {
            depth = i;
            int cnt = 0; //안전영역 개수
            visited = new boolean[n][n];

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    //잠겼거나 이미 방문한 정점인 경우
                    if (depth >= arr[j][k] || visited[j][k])
                        continue;
                    cnt += 1;
                    bfs(j, k);
                }
            }
            result = Math.max(cnt, result);
        }

        System.out.println(result);
    }

    static int rx[] = { 0, 0, -1, 1 };
    static int ry[] = { -1, 1, 0, 0 };

    static void bfs(int x, int y) {
        Deque<int[]> q = new ArrayDeque();
        q.addLast(new int[] { x, y });

        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] now = q.pollFirst();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + rx[i];
                int ny = now[1] + ry[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (visited[nx][ny]) continue;
                if (depth >= arr[nx][ny]) continue;

                visited[nx][ny] = true;
                q.addLast(new int[] { nx, ny });
            }
        }
    }
}

