import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 구현 및 순열
 * 각 사람들이 0번 또는 1번으로 갔을 때의 경우별로 순열을 구함
 * 각 사람들이 나갈 계단 값 세팅 후 거리를 구해준 다음 시뮬레이션 진행
 *
 * 1.각각의 계단에서 내려가고 있는 사람이 있다면 시간값을 증가시켜 주고 끝까지 내려갔다면 대기열에서 확인 후 바로 내려가도록 함
 * 2.아직 도착하지 못한 사람들의 거리값을 1씩 줄여주면서 만약 계단에 도착했다면 계단큐에 넣어줌 계단큐가 다 차있다면 대기열로 이동
 */
public class SW2383_점심식사시간 {
    private static int result;

    private static class Person  {
        int x;
        int y;
        int exit;
        int dis;

        public Person(int x, int y, int exit) {
            this.x = x;
            this.y = y;
            this.exit = exit;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            result = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());

            int[][] arr = new int[N][N];
            LinkedList<Person> people = new LinkedList<>();

            //계단 정보
            int[][] out = new int[2][3];
            out[0][0] = out[1][0] = -1;

            //값 입력 및 계단 또는 사람 정보 저장
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if (arr[i][j] == 1)
                        people.add(new Person(i, j, 0));
                    if (arr[i][j] > 1) {
                        if (out[0][0] == -1) {
                            out[0][0] = i;
                            out[0][1] = j;
                            out[0][2] = arr[i][j];
                        } else {
                            out[1][0] = i;
                            out[1][1] = j;
                            out[1][2] = arr[i][j];
                        }
                    }
                }
            }

            pm(0, out, people);

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);

    }

    private static void pm(int L, int[][] out, LinkedList<Person> people) {
        if (L == people.size()) {
            for (Person p : people) {
                p.dis = Math.abs(out[p.exit][0] - p.x) + Math.abs(out[p.exit][1] - p.y);
            }
            result = Math.min(result, simul(out, people));
            return;
        }
        //1번 계단으로 갔을 때
        people.get(L).exit = 1;
        pm(L + 1, out, people);
        //0번 계단으로 갔을 때
        people.get(L).exit = 0;
        pm(L + 1, out, people);
    }

    private static int simul(int[][] out, LinkedList<Person> people) {
        int time = 0;
        Queue<Person> q = new ArrayDeque<>();
        //큐에 현재 사람들 넣어주기
        for (Person person : people) {
            q.add(person);
        }
        int pNum = 1;
        int outCnt = 0;
        //계단 대기열 큐
        Queue<Person>[] wait = new ArrayDeque[2];
        //내려가고 있는 사람 큐
        Queue<int[]>[] now = new ArrayDeque[2];
        for (int i = 0; i < 2; i++) {
            now[i] = new ArrayDeque<>();
            wait[i] = new ArrayDeque<>();
        }
        //모든 사람이 빠져나왔다면 종료
        while (outCnt != people.size()) {
            //계단 2개 각각의 큐에 현재 내려가고 있는 사람 있는지 확인 후 시간 증가
            for (int i = 0; i < 2; i++) {
                if (!now[i].isEmpty()) {
                    int turn = now[i].size();
                    int nowTurn = 0;
                    while (nowTurn != turn) {
                        int[] downPerson = now[i].poll();
                        //해당 계단의 시간만큼 내려갔다면 대기큐에 있는 사람을 넣어줌
                        if (downPerson[1] == out[i][2]) {
                            while (now[i].size() != 3 && !wait[i].isEmpty()) {
                                wait[i].poll();
                                now[i].add(new int[] {pNum++, 1});
                            }
                            outCnt++;
                        } else {
                            downPerson[1]++;
                            now[i].add(downPerson);
                        }
                        nowTurn++;
                    }
                }
            }
            int originSize = q.size();
            int nowTrun = 0;
            //
            while(nowTrun != originSize) {
                Person p = q.poll();
                if (p.dis == 0) {
                    if (now[p.exit].size() == 3) {
                        wait[p.exit].add(p);
                    } else {
                        now[p.exit].add(new int[] { pNum++, 1});
                    }
                } else {
                    p.dis -= 1;
                    q.add(p);
                }
                nowTrun++;
            }
            time++;
        }
        return time;
    }
}