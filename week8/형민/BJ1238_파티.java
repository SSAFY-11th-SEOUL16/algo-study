import java.io.*;
import java.util.*;


public class BJ1238_파티 {

	static int n, m, x;
	static int distance[];
	static ArrayList<ArrayList<Node>> graph1 = new ArrayList<>();
	static ArrayList<ArrayList<Node>> graph2 = new ArrayList<>();

	static class Node implements Comparable<Node> {
		int v, cost;

		public Node(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); //정점 수
		m = Integer.parseInt(st.nextToken()); //간선 수
		x = Integer.parseInt(st.nextToken()); //파티하는 마을

		distance = new int[n + 1]; //최단 경로 배열

		for (int i = 0; i <= n; i++) {
			graph1.add(new ArrayList<>());
			graph2.add(new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph1.get(a).add(new Node(b, cost));
			graph2.get(b).add(new Node(a, cost));
		}
		
		int people[] = new int[n+1]; //각 학생의 오고 가는 시간
		
	//x번에서 1~n번까지 가는 최단 경로
        boolean visited[] = new boolean[n + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[x]=0;
        pq.add(new Node(x, 0));
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            visited[cur.v] = true;

            for(Node node : graph1.get(cur.v)) {

                int v = node.v;
                int cost = node.cost;
                if(!visited[v] && distance[v] > distance[cur.v] + cost) {
                    distance[v] = distance[cur.v] + cost;

                    pq.add(new Node(v, distance[v]));
                }
            }
        }

        //오고 가는 시간 갱신
        for(int i=1;i<=n;i++) {
        	people[i] += distance[i];
        }

        
        //1~n번에서 x번까지 가는 최단 경로(역 그래프)
        visited = new boolean[n + 1];
        pq = new PriorityQueue<>();
        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[x]=0;
        pq.add(new Node(x, 0));
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            visited[cur.v] = true;

            for(Node node : graph2.get(cur.v)) {

                int v = node.v;
                int cost = node.cost;
                if(!visited[v] && distance[v] > distance[cur.v] + cost) {
                    distance[v] = distance[cur.v] + cost;

                    pq.add(new Node(v, distance[v]));
                }
            }
        }
        
	//오고 가는 시간 갱신
        for(int i=1;i<=n;i++) {
        	people[i] += distance[i];
        }
        
        
        int max = 0;
        for(int i=1;i<=n;i++) {
        	max = Integer.max(max,  people[i]);
        }
        System.out.println(max);
	}

}
