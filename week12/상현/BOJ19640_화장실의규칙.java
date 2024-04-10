import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 우선순위 큐
 * pq 정렬 순서를 문제에서 나온 순위대로 정렬
 * 처음 시도는 while문 안에서 for문으로 맨 앞의 사람들을 계속 pq에 넣어준 후 clear 해주는 식으로 시도 -> 시간 초과
 * 생각해보니 처음 pq에 각 맨 앞의 3명을 넣어준 후 pq의 맨 앞을 빼오면서 다음번 해당 줄의 맨 앞사람을 넣어주면 됨
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayDeque<int[]>[] list = new ArrayDeque[M];
        for (int i = 0; i < M; i++) {
            list[i] = new ArrayDeque<>();
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            list[i % M].add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i, i % M});
        }

        int t = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            //근무 일수 같을 경우
            if (o1[0] == o2[0]) {
                //급함도 같을 경우
                if (o1[1] == o2[1]) {
                    //줄 번호가 작은 순
                    return o1[3] - o2[3];
                }
                //급한 순
                return o2[1] - o1[1];
            }
            //근무일수 순(불공평함)
            return o2[0] - o1[0];
        });
        for (int i = 0; i < M; i++) {
            if (!list[i].isEmpty()) {
                pq.add(list[i].poll());
            }
        }
        while (!pq.isEmpty()) {
            int[] p = pq.poll();

            if (p[2] == K) {
                break;
            }
            //빼온 사람 줄의 맨 앞사람을 넣어줌!!!!!!!!!
            if (!list[p[3]].isEmpty()) pq.add(list[p[3]].poll());
            t++;
        }

        System.out.println(t);
    }
}
