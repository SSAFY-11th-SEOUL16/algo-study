import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * MST
 * 모든 정점을 최소로 이었을 때를 전체 값에서 빼면 됨
 */

public class Main {
    private static int[] parent;

    static class Node implements Comparable<Node> {
        int to;
        int from;
        int v;

        public Node(int to, int from, int v) {
            this.to = to;
            this.from = from;
            this.v = v;
        }

        @Override
        public int compareTo(Node o) {
            return this.v - o.v;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];

        char[][] map = new char[N + 1][N + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map[i] = st.nextToken().toCharArray();
        }

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        int total = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ('a' <= map[i][j] && map[i][j] <= 'z') {
                    total += map[i][j] - 96;
                    pq.add(new Node(i + 1, j + 1, map[i][j] - 96));
                } else if ('A' <= map[i][j] && map[i][j] <= 'Z') {
                    total += map[i][j] - 38;
                    pq.add(new Node(i + 1, j + 1, map[i][j] - 38));
                }
            }
        }

        int eCnt = 0;
        int result = 0;
        while(!pq.isEmpty()) {
            Node n = pq.poll();

            if (find(n.to) != find(n.from)) {
                union(n.to, n.from);
                eCnt++;
                result += n.v;
            }

            if (eCnt == N - 1) {
                break;
            }
        }

        if (eCnt != N - 1) {
            System.out.println(-1);
        } else {
            System.out.println(total - result);
        }

    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b) {
        int x = find(a);
        int y = find(b);

        if (x > y) {
            parent[x] = y;
        } else {
            parent[y] = x;
        }
    }
}
