
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BFS
 * 1. 탈주범의 시작 위치부터 뻗어나가며 최대 L 길이 만큼 탐색 진행
 * 2. 파이프 끼리 방향별로 갈 수 있는 파이프를 체크해줘야 함
 *    EX) 현재 1번 파이프이며 다음 탐색 방향이 오른쪽일 경우 4번 파이프나 5번 파이프가 온다면 갈 수 없음
 * 3. 탐색을 돌다가 현재 위치의 상태값이 L일 경우 더 이상 탐색을 하면 안되므로 continue 처리
 */
public class SW1953_탈주범검거 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        //테스트 케이스 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= t; test_case++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());     //N,M
            int R = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());     //시작 위치
            int L = Integer.parseInt(st.nextToken());                                           //소요된 시간

            int[][] MAP = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    MAP[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int count = BFS(R, C, MAP, L, N, M);

            sb.append("#").append(test_case).append(" ").append(count).append("\n");
        }
        System.out.println(sb);
    }

    private static int BFS(int x, int y, int[][] MAP, int L, int N, int M) {
        int[][] move = {
                {-1, 0},
                {0, 1},
                {1, 0},
                {0, -1}};
        int[][] pipeDir = {
                {},             //0
                {0, 1, 2, 3},   //1
                {0, 2},         //2
                {1, 3},         //3
                {0, 1},         //4
                {1, 2},         //5
                {2, 3},         //6
                {0, 3}          //7
        };

        boolean[][] visit = new boolean[N][M];
        visit[x][y] = true;
        
        Queue<int[]> Q = new LinkedList<>();
        Q.add(new int[]{x, y, 1});

        int result = 1;         //범인이 최소 한개는 갈 수 있으므로 1부터 시작
        while (!Q.isEmpty()) {
            int[] now = Q.poll();
            //#3
            if (now[2] == L) {
                continue;
            }

            for (int i = 0; i < pipeDir[MAP[now[0]][now[1]]].length; i++) {

                int nx = now[0] + move[pipeDir[MAP[now[0]][now[1]]][i]][0];
                int ny = now[1] + move[pipeDir[MAP[now[0]][now[1]]][i]][1];

                //#2
                if ((nx >= 0 && nx < N) && (ny >= 0 && ny < M) && MAP[nx][ny] != 0 && !visit[nx][ny]) {
                    if (checkMove(MAP[now[0]][now[1]], MAP[nx][ny], pipeDir[MAP[now[0]][now[1]]][i])) {
                        result += 1;
                        Q.add(new int[]{nx, ny, now[2] + 1});
                        visit[nx][ny] = true;
                    }
                }

            }
        }

        return result;
    }

    /**
     * 이게 뭐람?...
     * 이거 아름답게 하는 법 알려주세요 선생님들
     */
    private static boolean checkMove(int nowPipe, int goPipe, int direction) {
        if (nowPipe == 1) {
            if (direction == 0 && (goPipe == 2 || goPipe == 5 || goPipe == 6 || goPipe == 1)) return true;
            if (direction == 1 && (goPipe == 3 || goPipe == 6 || goPipe == 7 || goPipe == 1)) return true;
            if (direction == 2 && (goPipe == 2 || goPipe == 4 || goPipe == 7 || goPipe == 1)) return true;
            if (direction == 3 && (goPipe == 3 || goPipe == 4 || goPipe == 5 || goPipe == 1)) return true;
            return false;
        } else if (nowPipe == 2) {
            if (direction == 0 && (goPipe == 1 || goPipe == 5 || goPipe == 6 || goPipe == 2)) return true;
            if (direction == 2 && (goPipe == 1 || goPipe == 4 || goPipe == 7 || goPipe == 2)) return true;
            return false;
        } else if (nowPipe == 3) {
            if (direction == 1 && (goPipe == 1 || goPipe == 7 || goPipe == 6 || goPipe == 3)) return true;
            if (direction == 3 && (goPipe == 1 || goPipe == 4 || goPipe == 5 || goPipe == 3)) return true;
            return false;
        } else if (nowPipe == 4) {
            if (direction == 0 && (goPipe == 1 || goPipe == 2 || goPipe == 5 || goPipe == 6)) return true;
            if (direction == 1 && (goPipe == 1 || goPipe == 3 || goPipe == 6 || goPipe == 7)) return true;
            return false;
        } else if (nowPipe == 5) {
            if (direction == 2 && (goPipe == 1 || goPipe == 2 || goPipe == 7 || goPipe == 4)) return true;
            if (direction == 1 && (goPipe == 1 || goPipe == 3 || goPipe == 6 || goPipe == 7)) return true;
            return false;
        } else if (nowPipe == 6) {
            if (direction == 2 && (goPipe == 1 || goPipe == 2 || goPipe == 7 || goPipe == 4)) return true;
            if (direction == 3 && (goPipe == 1 || goPipe == 3 || goPipe == 4 || goPipe == 5)) return true;
            return false;
        } else if (nowPipe == 7) {
            if (direction == 0 && (goPipe == 1 || goPipe == 2 || goPipe == 5 || goPipe == 6)) return true;
            if (direction == 3 && (goPipe == 1 || goPipe == 3 || goPipe == 4 || goPipe == 5)) return true;
            return false;
        }

        return false;
    }
}
