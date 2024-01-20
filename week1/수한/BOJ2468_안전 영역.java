import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] heights;
    static int N;
    static int answer;
    static int h;
    static boolean[][] visited;
    static int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    public static void dfs(int x, int y){
        visited[x][y] = true;

        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            if(visited[nx][ny] || heights[nx][ny] <= h) continue;

            dfs(nx, ny);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokenizer.nextToken());

        heights = new int[N+2][N+2];

        int maxHeight = 0;
        for (int i = 1; i <= N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                heights[i][j] = Integer.parseInt(tokenizer.nextToken());
                maxHeight = Math.max(maxHeight, heights[i][j]);
            }
        }

        answer = 1;
        for (h = 1; h < maxHeight; h++) {
            int numSafe = 0;
            visited = new boolean[N+2][N+2];
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    if(!visited[j][k] && heights[j][k] > h){
                        numSafe++;
                        dfs(j, k);
                    }
                }
            }
            answer = Math.max(answer,numSafe);
        }

        System.out.println(answer);
    }
}