import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int arr[][], parent[];

    static class Edge implements Comparable<Edge>{
        int u, e,cost;

        public Edge(int u, int e, int cost) {
            this.u = u;
            this.e = e;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int totalEdgesum = 0; //모든 간선의 길이의 합

        n = Integer.parseInt(br.readLine()); //컴퓨터의 개수
        arr = new int[n][n];
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            char[] charArray = br.readLine().toCharArray();
            parent[i] = i;
            for (int j = 0; j < n; j++) {
                if(charArray[j] >= 'a') arr[i][j] = charArray[j] - 'a' + 1;
                else if(charArray[j] == '0') arr[i][j] = 0;
                else arr[i][j] = charArray[j] - 'A' + 27;

                totalEdgesum += arr[i][j];
            }
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //자기 자신의 랜선이거나 랜선이 없을 때
                if(i == j || arr[i][j] == 0) continue;
                pq.add(new Edge(i, j, arr[i][j]));
            }
        }

        int lineCnt = 0; //간선 개수
        int edgeSum = 0; //간선의 합
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            
            //이미 연결되어 있는 경우
            if(find(cur.u) == find(cur.e)) continue;

            union(cur.u, cur.e);
            lineCnt++;
            edgeSum += cur.cost;

            if(lineCnt == n-1) break;
        }

        if(lineCnt != n-1) System.out.println(-1);
        else System.out.println(totalEdgesum - edgeSum);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        parent[pb] = pa;
    }

    static int find(int a) {
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
}
