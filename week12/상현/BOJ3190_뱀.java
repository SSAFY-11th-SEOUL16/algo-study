import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 구현, LinkedList
 * 각 코드 주석 참고
 */
public class Main {
    //time 시간에 direction 방향으로 전환하는 정보를 가지는 클래스
    private static class Turn {
        int time;
        String direction;

        public Turn(int time, String direction) {
            this.time = time;
            this.direction = direction;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int N = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];

        st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = -1;
        }

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        Queue<Turn> turnQ = new ArrayDeque<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            turnQ.add(new Turn(Integer.parseInt(st.nextToken()), st.nextToken()));
        }
        //초기 방향 오른쪽으로 세팅
        int x = 0, y = 0, dir = 1;
        int time = 0;
        ArrayDeque<int[]> snake = new ArrayDeque<>();
        snake.add(new int[] {0,0});

        while(true) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            //종료 조건
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                time++;
                break;
            }
            //-1 = 사과를 의미하며 사과를 먹으면 몸 길이가 늘어나므로 해당 nx,ny값을 LinkedList 맨 앞에 넣어주며 머리로 지정
            if (map[nx][ny] == -1) {
                map[nx][ny] = 0;
                snake.addFirst(new int[] {nx, ny});
            } else if (map[nx][ny] == 0) {
                //다음번 칸이 몸일 경우 멈추게 하는 flag
                boolean isContinue = true;
                //다음에 가고자 하는 칸이 LinkedList 안에 존재한다면 몸에 머리를 박는 경우 이므로 있는지 없는지 확인
                for (int[] body : snake) {
                    if (nx == body[0] && ny == body[1]) {
                        isContinue = false;
                        break;
                    }
                }
                if (!isContinue) {
                    time++;
                    break;
                }
                //꼬리 제거
                snake.removeLast();
                //머리 이동
                snake.addFirst(new int[] {nx, ny});
            }
            x = nx;
            y = ny;
            time++;

            //해당 시간에 방향 전환을 해야 하는지 확인
            if (!turnQ.isEmpty()) {
                if (time == turnQ.peek().time) {
                    Turn turn = turnQ.poll();
                    if (turn.direction.equals("L")) {
                        if (dir == 0) dir = 3;
                        else if (dir == 1) dir = 0;
                        else if (dir == 2) dir = 1;
                        else if (dir == 3) dir = 2;
                    } else if (turn.direction.equals("D")) {
                        if (dir == 0) dir = 1;
                        else if (dir == 1) dir = 2;
                        else if (dir == 2) dir = 3;
                        else if (dir == 3) dir = 0;
                    }
                }
            }
        }
        System.out.println(time);
    }
}
