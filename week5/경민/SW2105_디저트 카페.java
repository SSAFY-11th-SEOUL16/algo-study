import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int[] dx = { 1, 1, -1, -1 };
    static int[] dy = { 1, -1, -1, 1 };
    static int n, max = -1, startX, startY;
    static int[][] board;
    static boolean[][] visited;
    static boolean[] desserts = new boolean[101];

    public static boolean isValid(int x, int y) {
        if (x >= 0 && x < n && y >= 0 && y < n)
            return true;
        return false;
    }

    public static void dfs(int x, int y, int count, int d) {
        // ↘︎↙︎↖︎↗︎ 순서로 탐색할 수 있도록 방향 d 설정
        for (int i = d; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isValid(nx, ny)) {
                if (nx == startX && ny == startY && count > 2) {
                    max = Math.max(max, count);
                    return;
                }
                if (!visited[nx][ny] && !desserts[board[nx][ny]]) {
                    visited[nx][ny] = true;
                    desserts[board[nx][ny]] = true;
                    dfs(nx, ny, count + 1, i);
                    visited[nx][ny] = false;
                    desserts[board[nx][ny]] = false;

                }
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new FileInputStream("sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            max = -1;
            n = Integer.parseInt(br.readLine());
            board = new int[n][n];
            visited = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 밑에 2칸과 양옆 1칸엔 사각형을 만들 수 없음
            for (int i = 0; i < n - 2; i++) {
                for (int j = 1; j < n - 1; j++) {
                    for(boolean[] col : visited)
                        Arrays.fill(col, false);
                    Arrays.fill(desserts, false);
                    visited[i][j] = true;
                    desserts[board[i][j]] = true;
                    startX = i;
                    startY = j;
                    dfs(i, j, 1, 0);
                }
            }

            sb.append('#').append(t).append(' ').append(max).append('\n');
        }
        System.out.println(sb);
    }
}