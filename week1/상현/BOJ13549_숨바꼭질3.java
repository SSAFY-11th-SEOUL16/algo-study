import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13549_숨바꼭질3 {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        System.out.println(bfs2(N, K));
    }

    //처음 시도
    private static int bfs(int start, int target) {
        int[] d = {-1, 1, 2};

        int[] count = new int[100001];
        boolean[] visit = new boolean[100001];
        int result = Integer.MAX_VALUE;

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()) {
            int now = q.poll();
            int time = count[now];
            visit[now] = true;

            if (now == target) {
                result = Math.min(result, time);
            }

            for (int i = 0; i < 3; i++) {
                int next = i == 2 ? now * d[i] : now + d[i];

                if (next >= 0 && next <= 100000 && !visit[next]) {
                    q.add(next);
                    if (i == 2) {
                        count[next] = time;
                    } else {
                        count[next] = time + 1;
                    }
                }
            }
        }
        return result;
    }


    private static int bfs2(int start, int target) {
        boolean[] visit = new boolean[100001];
        int result = Integer.MAX_VALUE;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start, 0});
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int node = now[0];
            int time = now[1];
            visit[node] = true;

            if (node == target) {
                result = Math.min(result, time);
            }

            if (node * 2 <= 100000 && !visit[node * 2]) {
                q.add(new int[]{node * 2, time});
            }

            if (node + 1 <= 100000 && !visit[node + 1]) {
                q.add(new int[]{node + 1, time + 1});
            }

            if (node - 1 >= 0 && !visit[node - 1]) {
                q.add(new int[]{node - 1, time + 1});
            }
        }
        return result;
    }
}
