package baekjoon.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ21608_상어초등학교 {

    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static boolean[] studentCheck; //배치 체크 배열
    static int[][] setInfo; //학생 배치 정보
    static int[][] map; //배치도
    static int[][] studentInfo; //학생별 좋아하는 사람 정보
    static int[] order; //학생 순서
    static int TOTAL_PERSON;

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        TOTAL_PERSON = N * N;

        map = new int[N + 1][N + 1];
        studentInfo = new int[TOTAL_PERSON + 1][4];
        order = new int[TOTAL_PERSON + 1];
        studentCheck = new boolean[TOTAL_PERSON + 1];
        setInfo = new int[TOTAL_PERSON + 1][2];

        for (int i = 1; i <= TOTAL_PERSON; i++) {
            st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());

            order[i] = student;
            for (int j = 0; j < 4; j++) {
                studentInfo[student][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= TOTAL_PERSON; i++) {
            setSeat(order[i]);
        }

        System.out.println(score());
    }

    private static int score() {
        int result = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int nowStudent = map[i][j];
                int count = 0;
                //현재 위치 기준 4방 탐색
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if ((nx>=1 && nx <= N) && (ny >= 1 && ny <= N)) {
                        //해당 학생이 좋아하는 사람이 존재한다면
                        for (int l = 0; l < 4; l++) {
                            if (studentInfo[nowStudent][l] == map[nx][ny]) {
                                count += 1;
                            }
                        }
                    }
                }
                result += covertScore(count);
            }
        }
        return result;
    }

    private static int covertScore(int count) {
        if (count == 1) {
            return 1;
        } else if (count == 2) {
            return 10;
        } else if (count == 3) {
            return 100;
        } else if (count == 4) {
            return 1000;
        }
        return 0;
    }

    //자리 배치
    private static void setSeat(int student) {

        //좋아하는 사람 배치 확인
        Queue<Integer> likeQ = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            int like = studentInfo[student][i];
            if (studentCheck[like]) {
                likeQ.add(like);
            }
        }

        //있을 경우
        if (!likeQ.isEmpty()) {
            if (existFriend(student, likeQ)) {
                noFriend(student);
            }
        } else {
            noFriend(student);
        }
    }

    //좋아하는 사람 없을 경우
    private static void noFriend(int student) {
        int x = 0, y = 0;
        int emptyCount = Integer.MIN_VALUE;
        //탐색
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] != 0) {
                    continue;
                }

                int empty = countEmpty(i, j);
                //빈칸일 경우 4방 탐색

                if (empty > emptyCount) {
                    emptyCount = empty;
                    x = i;
                    y = j;
                } else if (empty == emptyCount) { //빈칸 같을 경우
                    if (i < x) {
                        x = i;
                        y = j;
                    } else if (i == x && j < y) {
                        x = i;
                        y = j;
                    }

                }

            }
        }
        map[x][y] = student;
        setInfo[student][0] = x;
        setInfo[student][1] = y;
        studentCheck[student] = true;
    }

    private static boolean existFriend(int student, Queue<Integer> likeQ) {
        int[][] tmpMap = new int[N + 1][N + 1];

        int result = 0;
        int x = 0;
        int y = 0;
        int empty = Integer.MIN_VALUE;

        while (!likeQ.isEmpty()) {
            int like = likeQ.poll();

            //친구 위치 정보
            int fX = setInfo[like][0];
            int fY = setInfo[like][1];

            //친구 위치 기준 4방 탐색
            for (int i = 0; i < 4; i++) {
                int nx = fX + dx[i];
                int ny = fY + dy[i];
                if ((nx >= 1 && nx <= N) && (ny >= 1 && ny <= N) && map[nx][ny] == 0) {
                    tmpMap[nx][ny] += 1;
                    if (tmpMap[nx][ny] > result) {
                        result = tmpMap[nx][ny];
                        empty = countEmpty(nx, ny);
                        x = nx;
                        y = ny;
                    } else if (tmpMap[nx][ny] == result) {
                        int emptyCount = countEmpty(nx, ny);
                        if (emptyCount > empty) {
                            empty = emptyCount;
                            x = nx;
                            y = ny;
                        } else if (emptyCount == empty) {
                            if (nx < x) {
                                x = nx;
                                y = ny;
                            }
                            if (nx == x && ny < y) {
                                x = nx;
                                y = ny;
                            }
                        }
                    }
                }
            }
        }

        if (x == 0 && y == 0) {
            return true;
        } else {
            map[x][y] = student;
            setInfo[student][0] = x;
            setInfo[student][1] = y;
            studentCheck[student] = true;
            return false;
        }
    }

    private static int countEmpty(int x, int y) {
        int emptyCount = 0;
        for (int j = 0; j < 4; j++) {
            int tmpNx = x + dx[j];
            int tmpNy = y + dy[j];
            if ((tmpNx >= 1 && tmpNx <= N) && (tmpNy >= 1 && tmpNy <= N)
                && map[tmpNx][tmpNy] == 0) {
                emptyCount += 1;
            }
        }

        return emptyCount;
    }
}
