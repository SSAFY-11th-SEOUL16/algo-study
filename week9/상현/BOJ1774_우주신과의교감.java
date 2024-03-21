import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * MST, 크루스칼, 유니온-파인드
 * 연결되지 않은 우주신들을 연결했을 때 최소가 되는 트리를 완성해야하는 문제
 * 입력을 다 받은 후 각 우주신들을 연결했을 때 거리는 좌표간 거리 공식을 이용해서 구할 수 있음
 * 기존의 연결된 우주신들 정보를 입력 받으면서 union-find를 진행
 * 간선 리스트에서 하나씩 확인하며 간선의 개수가 우주신 수 - 1일 때 멈춰줌
 */
public class BOJ1774_우주신과의교감 {
    private static int[] parent;

    private static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }

    }

    private static class Edge {
        int to;
        int from;
        double w;

        public Edge(int to, int from, double w) {
            this.to = to;
            this.from = from;
            this.w = w;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        Node[] nodes = new Node[N + 1];
        //Makeset과 동시에 Node 생성
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            parent[i] = i;
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(x, y);
        }
        //이어진 간선의 개수
        int eCnt = 0;
        //우선순위 큐를 이용해서 간선의 길이가 가장 짧은 순으로 정렬 유지
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>((o1, o2) -> {
            return Double.compare(o1.w, o2.w);
        });
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                double sum = Math.pow(nodes[j].x - nodes[i].x, 2) + Math.pow(nodes[j].y - nodes[i].y, 2);
                pq.add(new Edge(i, j, Math.sqrt(sum)));
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            //서로 다른 집합일 경우 union 진행
            if (find(a) != find(b)) {
                union(a, b);
                eCnt++;
            }
        }

        double result = 0;

        while(!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (find(edge.to) != find(edge.from)) {
                union(edge.to, edge.from);
                result += edge.w;
            }

            if (eCnt == N - 1) {
                break;
            }
        }

        for (Edge edge : pq) {
            if (find(edge.to) != find(edge.from)) {
                union(edge.to, edge.from);
                result += edge.w;
            }
        }

        System.out.printf("%.2f\n", result);
    }

    private static int find(int n) {
        if (parent[n] == n) {
            return n;
        }
        return parent[n] = find(parent[n]);
    }

    private static void union(int a, int b) {
        int aP = find(a);
        int bP = find(b);

        if (a < b)
            parent[bP] = aP;
        else
            parent[aP] = bP;
    }

}
