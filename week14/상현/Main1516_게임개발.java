import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 위상 정렬
 * 차수 0인 정점부터 탐색 진행
 * 우선순위큐를 활용하여 가장 시간이 적게드는 순서로 진행
 * 
 */
public class Main {
    private static class Build implements Comparable<Build>{
        int num;
        int time;

        public Build(int num, int time) {
            this.num = num;
            this.time = time;
        }

        @Override
        public int compareTo(Build o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());

        int[] times = new int[n + 1];
        int[] minTime = new int[n + 1];
        int[] degrees = new int[n + 1];
        List<Integer>[] adj = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
            minTime[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            while (true) {
                int num = Integer.parseInt(st.nextToken());
                if (num == -1) break;
                adj[num].add(i);
                degrees[i]++;
            }
        }

        PriorityQueue<Build> pq = new PriorityQueue<>();

        for (int i = 1; i <= n; i++) {
            if (degrees[i] == 0) {
                minTime[i] = times[i];
                pq.add(new Build(i, times[i]));
            }
        }

        while (!pq.isEmpty()) {
            Build now = pq.poll();

            for (Integer i : adj[now.num]) {
                degrees[i]--;
                if (degrees[i] == 0) {
                    minTime[i] = now.time + times[i];
                    pq.add(new Build(i, minTime[i]));
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            sb.append(minTime[i]).append("\n");
        }
        System.out.println(sb);
    }
}
