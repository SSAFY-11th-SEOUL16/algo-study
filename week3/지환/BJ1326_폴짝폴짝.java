package week3.지환;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1326_폴짝폴짝 {
	static int n;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n+1];
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		System.out.println(bfs(a, b));
	}

	public static int bfs(int a, int b) {
		Queue<Integer> q = new LinkedList<>();
		q.add(a);
		boolean[] visited = new boolean[n+1];
		int cnt = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int k=0; k<size; k++) {
				int tmp = q.remove();
				if(visited[tmp]) continue;
				if(tmp == b) {
					return cnt;
				}
				visited[tmp] = true;
				for(int i=tmp; i<=n; i+=arr[tmp]) {
					q.add(i);
				}
				for(int i=tmp; i>=1; i-=arr[tmp]) {
					q.add(i);
				}
			}
			cnt++;
		}
		return -1;
	}
}
