import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1326_폴짝폴짝 {
	private static final int INF = Integer.MAX_VALUE;
	
	private static int n;
	private static int[] arr, distance;
	private static boolean[] inQueue;
	
	private static int spfa(int a, int b) {
		int curr, next;
		Queue<Integer> q;
		
		Arrays.fill(distance, INF);
		distance[a] = 0;
		q = new ArrayDeque<>();
		q.add(a);
		inQueue[a] = true;
		while (!q.isEmpty()) {
			curr = q.poll();
			inQueue[curr] = false;
			for (next = curr - arr[curr]; next >= 0; next -= arr[curr]) {
				if (distance[curr] + 1 < distance[next]) {
					distance[next] = distance[curr] + 1;
					if (!inQueue[next]) {
						q.add(next);
						inQueue[next] = true;
					}
				}
			}
			for (next = curr + arr[curr]; next < n; next += arr[curr]) {
				if (distance[curr] + 1 < distance[next]) {
					distance[next] = distance[curr] + 1;
					if (!inQueue[next]) {
						q.add(next);
						inQueue[next] = true;
					}
				}
			}
		}
		return distance[b] == INF ? -1 : distance[b];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int a, b, i;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken()) - 1;
		b = Integer.parseInt(st.nextToken()) - 1;
		distance = new int[n];
		inQueue = new boolean[n];
		System.out.print(spfa(a, b));
	}
}
