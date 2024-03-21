
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 위상 정렬
 * 744ms
 *
 * 우선순위큐를 사용하여 이전까지의 작업 시간 + 현재 작업 시간을 넣어주며 가장 짧게 걸린 경우를 앞으로 가져오게 만들어줌
 * 제일 앞으로 가져와진 시간이 각 정점까지의 최단 시간이므로 해당 시간을 result에 할당 해줌
 */
public class BOJ2056_작업 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        //차수 저장 배열
        int[] degree = new int[N + 1];
        //걸리는 시간 배열
        int[] times = new int[N + 1];
        //각 작업 다음으로 가능한 작업 리스트
        List<Integer>[] nodes = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            degree[i] = M;
            times[i] = time;

            for (int j = 0; j < M; j++) {
                nodes[Integer.parseInt(st.nextToken())].add(i);
            }
        }

        //int[] 배열의 0번은 현재 작업 1번은 해당 작업까지 오는데 걸린 시간
        //걸린 시간이 짧은것들을 앞으로 가져와서 result 값을 해당 시간으로 갱신해 나가며 최소시간으로 할당
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o1[1] - o2[1];
        });

        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                pq.add(new int[]{i, times[i]});
            }
        }

        int result = 0;

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            //해당 작업 시간을 result 값으로 해줌
            result = now[1];
            for (int i = 0; i < nodes[now[0]].size(); i++) {
                //해당 작업의 선행 작업이 이루어졌으므로 -1을 해줌
                degree[nodes[now[0]].get(i)] -= 1;
                if (degree[nodes[now[0]].get(i)] == 0) {
                    //0이라면 이전 작업들이 완료 되었으므로 pq에 add
                    pq.add(new int[]{ nodes[now[0]].get(i), now[1] + times[nodes[now[0]].get(i)]});
                }
            }
        }

        System.out.println(result);

    }
}
