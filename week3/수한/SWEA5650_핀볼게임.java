package SW.problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_5650_핀볼게임
{
    static int N;

    public static boolean isWall(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(br.readLine());

        /*
         * == 블록 ==
         * - 블록은 각 번호마다 어느 방향에서 왔을 때 어느 방향으로 반환하는지 저장하는 배열 생성
         * int[][] blockDir;
         *
         * blockDir[i][k] : i번째 블록에 k 방향으로 도달했을 때 변환된 방향 값
         *
         *  		 	 0 1 2 3
         * int[] dx, dy :하 우 상 좌 순서
         *
         * {1,0,-1,0}
         * {0,1,0,-1}
         *
         * 블럭 1 : 진행 방향 : 하 -> 우(1), 우 -> 좌(3) , 상 -> 하(0), 좌 -> 상(2)
         * 블럭 2 : 진행 방향 : 하 -> 상(2), 우 -> 좌(3) , 상 -> 우(1), 좌 -> 하(0)
         * 블럭 3 : 진행 방향 : 하 -> 상(2), 우 -> 하(0) , 상 -> 좌(3), 좌 -> 우(1)
         * 블럭 4 : 진행 방향 : 하 -> 좌(3), 우 -> 상(2) , 상 -> 하(0), 좌 -> 우(1)
         * 블럭 5 : 진행 방향 : 하 -> 상(2), 우 -> 좌(3) , 상 -> 하(0), 좌 -> 우(1)
         *
         */

        int[][] blockDir = {
                {},
                {1,3,0,2},
                {2,3,1,0},
                {2,0,3,1},
                {3,2,0,1},
                {2,3,0,1},
        };

        StringTokenizer tokens;
        StringBuilder sb = new StringBuilder();

        int[] dx = {1,0,-1,0};
        int[] dy = {0,1,0,-1};
        int[][] map = new int[101][101];
        int[][][] hole = new int[5][2][2];

        int[] holeCnts = new int[5];

        int answer;
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine().trim());

            answer = 0;

            // 맵 저장
            for (int i = 0; i < N; i++) {
                tokens = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(tokens.nextToken());
                    if(map[i][j] >= 6 && map[i][j] <= 10) {
                        int holeIdx = map[i][j] - 6;
                        hole[holeIdx][holeCnts[holeIdx]][0] = i;
                        hole[holeIdx][holeCnts[holeIdx]++][1] = j;
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int d = 0; d < 4; d++) {
                        if(map[i][j] == 0) {
                            int x = i;
                            int y = j;
                            int k = d;

                            int count=0;

                            while(true) {
                                x += dx[k];
                                y += dy[k];
                                int nk = k;
                                if(isWall(x,y)) {
                                    // 벽에 부딪혔을 때
                                    count = count*2+1;
                                    break;
                                }else {
                                    int num = map[x][y];
                                    if(num == -1 || (x == i && y == j)) {
                                        // 블랙홀이나 시작점이라면 종료
                                        break;
                                    }else if(num==0){
                                        // 빈 공간이라면 지나감
                                        continue;
                                    }else if(num >= 1 && num < 6) {
                                        // 블럭에 부딪혔을 때
                                        nk = blockDir[num][k];
                                        if(nk != (k+2) % 4) count++;
                                        else {
                                            count = count*2+1;
                                            break;
                                        }
                                    }else if(num >= 6){
                                        // 웜홀에 닿았을 때
                                        for (int[] xy : hole[num-6]) {
                                            if(xy[0] != x || xy[1] != y) {
                                                x = xy[0];
                                                y = xy[1];
                                                break;
                                            }
                                        }
                                    }
                                }

                                k = nk;
                            }

                            answer = Math.max(answer, count);
                        }
                    }
                }
            }

            Arrays.fill(holeCnts,0);

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
