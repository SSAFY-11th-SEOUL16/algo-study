package week8.지환;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//Java8(332ms)
public class BJ1238_파티 {

	static int n;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());

		List<Node>[] graph = new ArrayList[n+1];
		List<Node>[] reverseGraph = new ArrayList[n+1];
		for(int i=1; i<=n; i++) {
			graph[i] = new ArrayList<>();
			reverseGraph[i] = new ArrayList<>();
		}
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			graph[start].add(new Node(end, time));
			reverseGraph[end].add(new Node(start, time));
		}
		int[] go = calculateDistance(x, graph);
		int[] back = calculateDistance(x, reverseGraph);
		int max = 0;
		for(int i=1; i<=n; i++) {
			if(back[i]==Integer.MAX_VALUE || go[i]==Integer.MAX_VALUE) continue;
			max = Math.max(go[i] + back[i], max);
		}
		System.out.println(max);
	}
	public static int[] calculateDistance(int start, List<Node>[] graph) {
		int[] dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		pq.add(new int[] {start, 0});
		dist[start] = 0;
		while(!pq.isEmpty()) {
			int[] now = pq.remove();
			for(Node next : graph[now[0]]) {
				if(dist[now[0]] + next.time < dist[next.end]) {
					dist[next.end] = dist[now[0]] + next.time;
					pq.add(new int[] {next.end, dist[next.end]});
				}
			}
		}
		return dist;
	}

	static class Node {
		int end;
		int time;

		Node(int end, int time) {
			this.end = end;
			this.time = time;
		}
	}
}
