import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * 완전 탐색으로 접근
 * 1. 최대 봉우리 값을 가진 위치들을 확인하고 해당 좌표에서 탐색을 진행
 * 2. 탐색을 진행하다가 다음으로 갈 좌표의 봉우리 값이 클 경우 깎을 수 있는 기회가 남아있다면 1부터 깎아 나가면서 각각의 봉우리 값 별로 탐색을 진행
 * 
 * #######의사코드 버전#######
 * 방문 체크
 * #1
 * 4방 탐색을 진행
 * 		#2
 * 		다음 X좌표, 다음 Y좌표
 * 		#3
 * 		좌표가 범위를 벗어나는지와 방문했었는지 확인
 * 				#4
 * 				최대 등산로 길이 비교
 * 				#5
 * 				다음 등산로가 현재 봉우리 보다 크고 & 깎을 수 있을 경우
 * 						다음 좌표의 봉우리 값을 1 ~ K 만큼 깎음
 * 								다음 좌표의 봉우리 값이 현재보다 작을 경우 탐색 진행
 * 						깎은 거 원복
 * 				현재 봉우리가 다음 봉우리보다 클 경우
 * 						그대로 탐색 진행
 *
 *
 * 방문 체크 풀어주기
 */
public class SW1949_등산로조성 {
    
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};
    private static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        
        for (int tc = 1; tc <= T; tc++) {
            result = 0;
            st = new StringTokenizer(br.readLine());
            
            int N = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            
            int[][] map = new int[N][N];
            int max = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] > max) {
                        max = map[i][j]; 
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == max) {
                        boolean[][] visit = new boolean[N][N];
                        DFS(i,j,true,L,N,visit, map, 1);
                    }
                }
            }
            
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);

    }

    /**
     * @param x         - x좌표
     * @param y         - y좌표
     * @param isCut     - 깎을 수 있는지 여부
     * @param L         - 깎을 수 있는 최대값
     * @param N         - N
     * @param visit     - 방문 여부 배열
     * @param map       - 높이 정보 맵
     * @param count     - 등산로 개수
     */
    private static void DFS(int x, int y, boolean isCut, int L, int N, boolean[][] visit, int[][] map, int count) {
        visit[x][y] = true;
        //#1
        for (int i = 0; i < 4; i++) {
            //#2
            int nx = x + dx[i];
            int ny = y + dy[i];

            //#3
            if ((nx >= 0 && nx < N) && (ny >= 0 && ny < N) && !visit[nx][ny] ) {
                //#4
                result = Math.max(result,  count);
                //#5
                if (map[nx][ny] >= map[x][y] && isCut) {
                    isCut = false;
                    //#6
                    for (int j = 1; j <= L; j++) {
                        map[nx][ny] -= j;
                        if (map[nx][ny] < map[x][y]) {
                            DFS(nx, ny, isCut, L, N, visit, map, count + 1);                            
                        }
                        map[nx][ny] += j;
                    }
                    isCut = true;
                }
                //#6
                else if(map[nx][ny] < map[x][y]) {
                    DFS(nx, ny, isCut, L, N, visit, map, count + 1);    
                }
            }
        }
        visit[x][y] = false;
    }
    
}