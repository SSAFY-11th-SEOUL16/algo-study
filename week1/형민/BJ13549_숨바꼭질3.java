import java.util.*;
import java.io.*;

class Main {
    static int n, k;
    static int arr[];
    static boolean visited[];

    static class Node implements Comparable<Node> {
        int x;
        int value;

        public Node(int x, int value) {
            this.x = x;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[100001];
        visited = new boolean[100001];

        System.out.println(bfs(n));
    }

    static int bfs(int x) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(x, 0));

        if(x == k) return 0;
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int curX = node.x;
            visited[curX] = true;
            if(curX == k) return node.value;

            if(curX * 2 < 100001 && !visited[curX * 2]) pq.add(new Node(curX * 2, node.value));
            if(curX + 1 < 100001 && !visited[curX + 1]) pq.add(new Node(curX + 1, node.value + 1));
            if(curX - 1 >= 0 && !visited[curX - 1]) pq.add(new Node(curX - 1, node.value + 1));
        }

        return -1;
    }
}
