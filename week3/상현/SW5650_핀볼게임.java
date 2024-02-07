import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW5650_핀볼게임 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        //테스트 케이스 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= 1; test_case++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            int[][] map = new int[N + 2][N + 2];
            //웜홀 정보
            int[][][] wormhole = new int[5][2][2];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] >= 6 && map[i][j] <= 10) {
                        //웜홀 첫칸이 비어 있다면 첫번째 칸에 저장
                        if (wormhole[map[i][j] - 6][0][0] == 0 && wormhole[map[i][j] - 6][0][1] == 0) {
                            wormhole[map[i][j] - 6][0][0] = i;
                            wormhole[map[i][j] - 6][0][1] = j;
                        } else {
                            wormhole[map[i][j] - 6][1][0] = i;
                            wormhole[map[i][j] - 6][1][1] = j;
                        }
                    }
                }
            }

            int result = 0;

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (map[i][j] == 0) {
                        int count = simulation(i,j,map,N,wormhole);
                        result = Math.max(count, result);
                    }
                }
            }


            sb.append("#").append(test_case).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    /**
     * 시작 x y 값 위치 받음
     * 4방향 중 하나 선택해서 이동
     * 조건에 맞게 이동 처리
     */
    private static int simulation(int x, int y, int[][] map, int N, int[][][] wormhole) {
        //아래, 오른쪽, 위, 왼쪽
        int[] dx = new int[] {1,0,-1,0};
        int[] dy = new int[] {0,1,0,-1};

        int startX = x;
        int startY = y;
        int result = 0;

        int[][] changeDirection = new int[][] {{0},
            {1,2,-2,-1},   //1번이랑 충돌했을 경우 순서는 아래,오른쪽,위,왼쪽 순서
            {2,2,-1,-3},   //2번이랑
            {2, -1, 1, -2},//3번이랑
            {3,1,-2,-2},   //4번이랑
            {2,2,-2,-2}    //5번이랑
        };

        //종료 1.제자리로  2.웜홀에 빠질경우
        for (int startDir = 0; startDir < 4; startDir++) {
            int nowDir = startDir;
            int count = 0;
            x = startX;
            y = startY;
            while(true) {
                int nx = x + dx[nowDir];
                int ny = y + dy[nowDir];
                //종료 조건  웜홀에 빠짐 || 제자리
                if (map[nx][ny] == -1 || (nx == startX && ny == startY)) {
                    result = Math.max(result, count);
                    break;
                }

                //벽이랑 충돌할 경우 카운트 증가 후 방향 전환
                if (nx <= 0 || ny <= 0 || nx > N || ny > N) {
                    count += 1;
                    nowDir = (nowDir + 2) % 4;
                }

                //1,2,3,4,5번 구분
                if (map[nx][ny] >= 1 && map[nx][ny] <= 5) {
                    count += 1;
                    nowDir = nowDir + changeDirection[map[nx][ny]][nowDir];
                } else if (map[nx][ny] >= 6 && map[nx][ny] <= 10) {    //웜홀에 빠진 경우
                    if (wormhole[map[nx][ny] - 6][0][0] == nx && wormhole[map[nx][ny] - 6][0][1] == ny) {
                        x = wormhole[map[nx][ny] - 6][1][0];
                        y = wormhole[map[nx][ny] - 6][1][1];
                    } else {
                        x = wormhole[map[nx][ny] - 6][0][0];
                        y = wormhole[map[nx][ny] - 6][0][1];
                    }
                    continue;
                }
                x = nx;
                y = ny;
            }
        }

        return result;
    }
}
