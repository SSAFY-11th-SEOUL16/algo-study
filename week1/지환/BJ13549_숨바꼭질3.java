package week1.지환;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ13549_숨바꼭질3 {
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		System.out.println(bfs(n, k));
	}

	public static int bfs(int n, int k) {
		int[] visited = new int[100001];
		Arrays.fill(visited, -1);
		Queue<Integer> q = new LinkedList<>();
		q.add(n);
		visited[n] = 0;
		while(!q.isEmpty()) {
			int tmp = q.poll();
			if(tmp == k) {
				return visited[k];
			}

			if(tmp*2 <= 100000 && visited[tmp*2]==-1) {
				visited[tmp*2] = visited[tmp];
				q.add(tmp*2);
			}

			if(tmp-1 >= 0 && visited[tmp-1]==-1) {
				q.add(tmp-1);
				visited[tmp-1] = visited[tmp] + 1;
			}

			if(tmp+1 <= 100000 && visited[tmp+1]==-1) {
				visited[tmp+1] = visited[tmp] + 1;
				q.add(tmp+1);
			}
		}
		return 0;
	}
}
