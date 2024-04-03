import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 크루스칼
 * 문제에서 마을에는 집이 한개 이상 있으면 되므로 최소 신장 트리를 만들어줌
 * 만들다가 마지막 정점을 잇는 경우만 빼주면 최소 비용이 나옴
 */

public class Main {
    private static class Edge implements Comparable<Edge> {
        int to;
        int from;
        int w;

        public Edge(int to, int from, int w) {
            this.to = to;
            this.from = from;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] parent = new int[N + 1];

        //make-set
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        PriorityQueue<Edge> edges = new PriorityQueue<>();

        int vCnt = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges.add(new Edge(A, B, w));
        }

        int totalW = 0;

        while (true) {
            Edge e = edges.poll();

            if (find(e.to, parent) != find(e.from, parent)) {
                union(e.to, e.from, parent);
                vCnt++;
                totalW += e.w;
            }

            if (vCnt == N - 1) {
                totalW -= e.w;
                break;
            }
        }

        System.out.println(totalW);
    }

    private static int find(int n, int[] parent) {
        if (parent[n] == n) {
            return n;
        }
        return parent[n] = find(parent[n], parent);
    }

    private static void union(int a, int b, int[] parent) {
        int aP = find(a, parent);
        int bP = find(b, parent);

        if (aP == bP) return;

        if (aP < bP) {
            parent[bP] = aP;
        } else {
            parent[aP] = bP;
        }
    }

}
