

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 완전 탐색
 *
 * 1. 벽에 붙어 있는 코어는 제외 제외시켜 버린다.
 * 2. 각 코어와 연결할 방향으로 전선 또는 코어가 있는지 확인하고 없다면 map에 2로 표시 
 * 3. 2번 방식으로 계속 진행하며 기저 조건 도달 시 코어의 개수를 비교하고 코어 개수가 많다면 길이를 업데이트 하고 만약 코어 개수가 같은데 길이가 더 짧은경에도 값 업데이트
 * 4. isFill = 전선을 설치할 방향에 장애물이 있는지 없는지 확인 / fill = 해당 방향으로 전선을 설치 2 = 전선 0 = 원복을 위한 값이다.
 *
 * #######combi 부분 의사코드 버전#######
 * #1
 * 종료 조건
 *      연결한 코어수 > 현재 코어최대값
 *          전선 최소 길이 = 현재 전선 길이
 *      연결한 코어수 = 현재 코어최대값
 *          전선 최소 길이 > 현재 전선 길이
 *              전선 최소 길이 = 현재 전선 길이
 *  
 *  #2
 *  4방향 탐색 
 *      #3
 *      현재 코어에서 현재 방향으로 연결할 수 있는지 확인
 *          #4
 *          다음 코어 선택 전 현재 맵에 전선 표시
 *          다음 코어 진행
 *          탐색 완료 후 맵 원복
 *  
 *  #5
 *  현재 코어 선택 안할 경우
 */
public class SW1767_프로세서연결하기 {
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int result = Integer.MAX_VALUE;
    private static int coreMaxCnt = Integer.MIN_VALUE;

    private static int[][] map;
    private static class Core {
        int x;
        int y;

        public Core(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            result = Integer.MAX_VALUE;
            coreMaxCnt = Integer.MIN_VALUE;
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());

            map = new int[N][N];

            int coreCnt = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        if (i != 0 && j != 0 && i != N - 1 && j != N - 1) {
                            coreCnt++;
                        }
                    }
                }
            }

            Core[] cores = new Core[coreCnt];
            int idx = 0;
            for (int i = 1; i < N - 1; i++) {
                for (int j = 1; j < N - 1; j++) {
                    if (map[i][j] == 1) {
                        cores[idx++] = new Core(i, j);
                    }
                }
            }

            combi(0, cores, 0, 0);
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);

    }

    private static void combi(int L, Core[] cores, int len, int coreCnt) {
        //#1
        if (L == cores.length) {
            if (coreCnt > coreMaxCnt) {
                coreMaxCnt = coreCnt;
                result = len;
            } else if (coreCnt == coreMaxCnt) {
                if (result > len) {
                    result = len;
                }
            }
            return;
        }
        //#2
        for (int i = 0; i < 4; i++) {
            //#3
            if (isFill(cores[L], i)) {
                //#4
                int cnt = fill(cores[L], 2, i);
                combi(L + 1, cores, len + cnt, coreCnt + 1);
                fill(cores[L], 0, i);
            }
        }
        //#5
        combi(L + 1, cores, len, coreCnt);
    }

    /**
     *  맵에 전선을 채우는 함수
     */
    private static int fill(Core c, int v, int dir) {
        int cnt = 0;

        int x = c.x;
        int y = c.y;

        while (true) {
            x = x + dx[dir];
            y = y + dy[dir];
            if (x >= 0 && x < map.length && y >= 0 && y < map[0].length) {
                map[x][y] = v;
                cnt++;
                continue;
            }
            break;
        }
        return cnt;
    }

    /**
     * 현재 방향이 전선을 이을 수 있는지 확인
     */
    private static boolean isFill(Core c, int dir) {
        int x = c.x;
        int y = c.y;

        while (true) {
            x = x + dx[dir];
            y = y + dy[dir];
            if (x >= 0 && x < map.length && y >= 0 && y < map[0].length) {
                if (map[x][y] != 0) return false;
                continue;
            }
            break;
        }
        return true;
    }
}
