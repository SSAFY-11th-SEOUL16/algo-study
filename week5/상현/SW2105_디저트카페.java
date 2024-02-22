import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * DFS
 * 1. i 0 j 1번째 가게부터 시작하면서 각 지점으로 부터 만들 수 있는 사각형을 전부 그려가며 탐색 진행
 * 2. 이전 방향으로는 갈 수 없기 때문에 이전 방향의 값을 가지고 가면서 해당 방향부터 움직일 수 있는 방향 전부를 탐색
 * 3. nx ny 값이 처음 위치와 같다면 count값과 result 값을 비교한다.
 */
public class SW2105_디저트카페 {
    private static int[] dx = {1, 1, -1, -1};
    private static int[] dy = {1, -1, -1, 1};
    private static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            result = -1;
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int[][] map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < map.length; j++) {
                    int v = Integer.parseInt(st.nextToken());
                    map[i][j] = v;
                }
            }

            //시작 가게로 부터 사각형을 만들 수 있는 좌표 영역은 i 0번째 줄의 j 1부터가 가능
            for (int i = 0; i < N - 2; i++) {
                for (int j = 1; j < N - 1 ; j++) {
                    //시작 가게 방문 체크 및 카운트 1
                    boolean[][] visit = new boolean[N][N];
                    boolean[] desert = new boolean[101];
                    visit[i][j] = true;
                    desert[map[i][j]] = true;
                    dfs(i,j, visit, i, j, 1, N, 0,desert, map);
                }
            }

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);

    }

    /**
     *
     * @param x         - 현재 x
     * @param y         - 현재 y
     * @param visit     - 방문 확인 배열
     * @param startX    - 시작 위치 x
     * @param startY    - 시작 위치 y
     * @param count     - 현재 디저트 가게 개수
     * @param N         - N
     * @param dir       - 현재 방향
     * @param check     - 방문한 디저트 체크
     * @param map       - 맵
     */
    private static void dfs(int x, int y, boolean[][] visit, int startX, int startY, int count, int N, int dir, boolean[] check, int[][] map) {
        //현재 방향부터 시작 -> 이전 방향을 갈 수 없기 때문에 dir부터 for문을 돈다. 방향은 우하 - 좌하 - 좌상 - 우상 순서
        for (int i = dir; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if ((nx >= 0 && nx < N) && (ny >= 0 && ny < N)) {
                //한바퀴를 돌아 제자리로 왔다면 최소한 4개 이상을 가지고 있는 상태만 가능하기 때문에 3개 이상만을 조건으로 적용
                if ((nx == startX && ny == startY) && count > 3) {
                    result = Math.max(count, result);
                    return;
                }
                if (!visit[nx][ny] && !check[map[nx][ny]]) {
                    visit[x][y] = true;
                    check[map[nx][ny]] = true;
                    dfs(nx, ny, visit, startX, startY, count + 1, N, i, check, map);
                    visit[x][y] = false;
                    check[map[nx][ny]] = false;

                }
            }
        }
    }
}
