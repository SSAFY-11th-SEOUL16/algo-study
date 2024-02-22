import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int n, k, result = 0;
    static boolean flag;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    public static void dfs(int x, int y, int count) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny]) {
                if (board[nx][ny] >= board[x][y] && !flag) {
                    // 봉우리가 이전보다 높지만 깎을 기회 1번이 남아있는 경우
                    for (int j = 1; j <= k; j++) {
                        // 1~k까지 board[x][y]보다 작아질 때까지 깎기
                        board[nx][ny] -= j;
                        if (board[nx][ny] < board[x][y]) {
                            visited[nx][ny] = true;
                            flag = true;
                            dfs(nx, ny, count + 1);
                            visited[nx][ny] = false;
                            flag = false;
                            board[nx][ny] += j;
                            continue;
                        }
                        board[nx][ny] += j;
                    }
                } else if (board[nx][ny] < board[x][y]) {
                    visited[nx][ny] = true;
                    dfs(nx, ny, count + 1);
                    visited[nx][ny] = false;
                }
            } else {
                result = Math.max(result, count);
            }
        }

    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        // System.setIn(new FileInputStream("sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            board = new int[n][n];
            visited = new boolean[n][n];
            result = 0;
            int max = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    max = Math.max(max, board[i][j]);
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == max) {
                        // 가장 높은 봉우리를 기준으로 상하좌우 탐색
                        visited[i][j] = true;
                        dfs(i, j, 1);
                        visited[i][j] = false;
                    }
                }
            }

            sb.append('#').append(t).append(' ').append(result).append('\n');
        }
        System.out.println(sb);
    }
}