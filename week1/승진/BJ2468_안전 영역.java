import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] maps = new int[N][N];
        int maxH = 0;

        int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        for (int i = 0; i < N; i++) {
            String[] tmpStr = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(tmpStr[j]);
                maxH = Math.max(maxH, maps[i][j]);
            }
        }

        int maxArea = 0;

        for (int h = 0; h < maxH; h++) {
            maxArea = Math.max(maxArea, dfs(maps, h, N, moves));
        }

        System.out.println(maxArea);
    }

    private static int dfs(int[][] maps, int h, int N, int[][] moves) {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new ArrayDeque<>();
        int result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (maps[i][j] <= h || visited[i][j]) {
                    continue;
                }
                q.add(new int[]{i, j});

                while (!q.isEmpty()) {
                    int[] current = q.poll();
                    int x = current[0];
                    int y = current[1];

                    for (int[] move : moves) {
                        int nx = x + move[0];
                        int ny = y + move[1];

                        if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] || maps[nx][ny] <= h) {
                            continue;
                        }
                        q.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
                result++;
            }
        }
        return result;
    }
}
