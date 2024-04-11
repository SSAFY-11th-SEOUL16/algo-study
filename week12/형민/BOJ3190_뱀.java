import java.io.*;
import java.util.*;

public class Main {
    static int n, k, l;
    static boolean[][] snakes; //맵에 있는 뱀
    static boolean[][] apples; //맵에 있는 사과
    static HashMap<Integer, String> diMap; //방향 변환 정보
    static LinkedList<int []> snake;
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        apples = new boolean[n][n];
        snakes = new boolean[n][n];
        k = Integer.parseInt(br.readLine());
        diMap = new HashMap<>();
        snake = new LinkedList<>();

        //사과의 위치 저장
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            apples[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
        }

        //방향 전환 정보 저장
        l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            diMap.put(Integer.parseInt(st.nextToken()), st.nextToken());
        }

        //뱀 시작 위치
        snake.addFirst(new int[]{0, 0});
        snakes[0][0] = true;

        int time = 0;
        int di = 1;
        while(true) {
            int head[] = snake.get(0); //머리 위치

            //방향 바뀌는지 확인
            if(diMap.containsKey(time)) {
                String d = diMap.get(time);
                diMap.remove(time);

                //방향 전환(상 우 하 좌)
                if(d.equals("L")) {
                    di--;
                    if(di < 0) di = 3;
                } else if(d.equals("D")) {
                    di++;
                    if(di >= 4) di = 0;
                }
            }

            //다음 위치
            int nx = head[0] + dx[di];
            int ny = head[1] + dy[di];

            //다음 위치가 벽인 경우
            if (nx < 0 || ny < 0 || nx >= n || ny >= n) break;
            //다음 위치가 자기 자신인 경우
            if(snakes[nx][ny]) break;

            //다음 위치에 사과 없을 때
            if(!apples[nx][ny]) {
                int tail[] = snake.getLast(); //꼬리 위치
                snakes[tail[0]][tail[1]] = false;
                snake.removeLast(); //꼬리 자르기
            } else apples[nx][ny] = false;

            snake.addFirst(new int[]{nx, ny});
            snakes[nx][ny] = true;

            time++;
        }

        System.out.println(time + 1);
    }
}