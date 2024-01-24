package baekjoon.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2468_안전영역 {
    private static int N;
    private static int[][] map;
    private static boolean[][] visit;
    private static int[] dx = {0,0,-1,1};
    private static int[] dy = {-1,1,0,0};
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }
        int result = 0;
        for (int i = 0; i < max; i++) {
            visit = new boolean[N][N];
            int count = 0;
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (map[j][k] > i && !visit[j][k]) {
                        dfs(j,k,i);
                        count++;
                    }
                }
            }
            result = Math.max(result, count);
        }
        System.out.println(result);
    }

    private static void dfs(int x, int y, int heigth) {
        visit[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if ((nx >= 0 && nx < N) && (ny >= 0 && ny < N) && !visit[nx][ny] && map[nx][ny] > heigth) {
                dfs(nx, ny, heigth);
            }
        }
    }
}
