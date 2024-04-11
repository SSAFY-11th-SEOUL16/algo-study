package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1414_불우이웃돕기 {

    static class Edge implements Comparable<Edge>{
        int a,b,dist;
        Edge(int a, int b, int dist){
            this.a = a;
            this.b = b;
            this.dist = dist;
        }


        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }

    public static int find(int x, int[] parents){
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x], parents);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /*
            크루스칼
         */

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int totalDist = 0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = str.charAt(j);
                if(c == '0') continue;
                int dist = 0;
                if(c >= 'a' && c <= 'z'){
                    dist = c-'a'+1;
                }else if(c >= 'A' && c <= 'Z'){
                    dist = c-'A'+27;
                }
                if(i != j) pq.add(new Edge(i,j,dist));
                totalDist += dist;
            }
        }

        int[] parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }

        int connectedDist = 0;
        int numConnected = 0;
        int limit = N-1;
        while(!pq.isEmpty() && numConnected < limit){
            Edge edge = pq.poll();

            int pa = find(edge.a, parents);
            int pb = find(edge.b, parents);

            if(pa == pb) continue;

            parents[pa] = pb;
            connectedDist += edge.dist;
            numConnected++;
        }

        System.out.println(numConnected != limit ? "-1" : totalDist - connectedDist);
    }
}