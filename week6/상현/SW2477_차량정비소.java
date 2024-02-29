import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 구현
 * 1. 접수 창구에 배정된 고객들의 현재 time값을 먼저 확인한 다음 해당 접수 창구의 처리 시간과 동일하다면 정비 창구 대기 큐에 넣음
 * 2. 정비 창구에 배정된 고객들의 현재 time값을 확인한 다음 해당 정비 창구의 처리 시간과 동일하다면 빼버리고 대기큐 확인 후 배치
 * 3. 타임 테이블별로 현재의 접수 창구를 확인하고 할당 가능하다면 할당 그렇지 않다면 접수 대기큐에 넣어줌
 * 4. 창구에 배정시에 시간값을 1부터 세팅한 후 시작하며 다음 시간에 확인
 */
public class SW2477_차량정비소 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());   //접수 창구 개수
            int M = Integer.parseInt(st.nextToken());   //정비 창구 개수
            int K = Integer.parseInt(st.nextToken());   //고객 수
            int A = Integer.parseInt(st.nextToken());   //접수 창구 이용 번호
            int B = Integer.parseInt(st.nextToken());   //정비 창구 이용 번호

            int[] rece = new int[N];
            int[][] nowRece = new int[N][2];
            int[] maintan = new int[M];
            int[][] nowMaintan = new int[M][2];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                rece[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                maintan[i] = Integer.parseInt(st.nextToken());
            }

            //고객별 도착 정보
            Queue<int[]> timeT = new LinkedList<>();
            //고객별 접수 창구 및 정비 창구 이용 내역
            int[][] personInfo = new int[K + 1][2];
            st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= K; i++) {
                timeT.add(new int[]{i, Integer.parseInt(st.nextToken())});
            }

            int nowTime = 0;

            Queue<Integer> receWaitQ = new LinkedList<>();      //접수 대기 큐
            Queue<Integer> maintanWaitQ = new LinkedList<>();   //접수 대기 큐

            while (true) {
                //접수 창구
                for (int i = 0; i < N; i++) {
                    //i번째 접수 창구가 비어 있지 않다면
                    if (nowRece[i][0] != 0) {
                        //해당 시간 증가
                        if (nowRece[i][1] == rece[i]) {
                            personInfo[nowRece[i][0]][0] = i + 1;   //접수 창구 입력
                            maintanWaitQ.add(nowRece[i][0]);        //정비 창구 바로 가지 않고 임시 큐에 add

                            nowRece[i][0] = nowRece[i][1] = 0;      //초기화

                            if (!receWaitQ.isEmpty()) {             //접수 창구 기다리는 사람이 있을 경우
                                int p = receWaitQ.poll();
                                nowRece[i][0] = p;
                                nowRece[i][1] = 1;
                            }
                        } else {
                            //증가 후 i번째 접수 창구의 처리 시간 만족할 경우
                            nowRece[i][1]++;
                        }
                    }
                }

                //정비  창구
                for (int i = 0; i < M; i++) {
                    //해당 정비 창구가 비어있지 않다면
                    if (nowMaintan[i][0] != 0) {
                        if (nowMaintan[i][1] == maintan[i]) {
                            personInfo[nowMaintan[i][0]][1] = i + 1;
                            nowMaintan[i][0] = nowMaintan[i][1] = 0;

                            if (!maintanWaitQ.isEmpty()) {
                                int p = maintanWaitQ.poll();
                                nowMaintan[i][0] = p;
                                nowMaintan[i][1] = 1;
                            }
                        } else {
                            nowMaintan[i][1]++;
                        }
                    } else {
                        if (!maintanWaitQ.isEmpty()) {
                            int p = maintanWaitQ.poll();
                            nowMaintan[i][0] = p;
                            nowMaintan[i][1] = 1;
                        }
                    }
                }

                //타임테이블 별로 접수 창구에 할당
                while (!timeT.isEmpty()) {
                    if (timeT.peek()[1] == nowTime) {
                        int[] p = timeT.poll();
                        boolean pushFlag = false;
                        for (int i = 0; i < N; i++) {
                            if (nowRece[i][0] == 0) {
                                nowRece[i] = new int[]{p[0], 1};
                                pushFlag = true;
                                break;
                            }
                        }
                        //접수 창구에 할당 못한다면 접수 대기 큐에 add
                        if (!pushFlag) {
                            receWaitQ.add(p[0]);
                        }
                    } else {
                        break;
                    }
                }
                //종료 조건 = 모든 고객이 접수 창구와 정비 창구를 할당 받았다면
                boolean cFlag = false;
                for (int i = 1; i <= K; i++) {
                    if (personInfo[i][0] == 0 || personInfo[i][1] == 0) {
                        cFlag = true;
                        break;
                    }
                }

                if (cFlag) {
                    nowTime++;
                    continue;
                }
                break;
            }

            int result = 0;
            for (int i = 1; i <= K; i++) {
                if (personInfo[i][0] == A && personInfo[i][1] == B) {
                    result += i;
                }
            }
            sb.append("#").append(tc).append(" ").append(result == 0 ? -1 : result).append("\n");
        }
        System.out.println(sb);
    }
}
