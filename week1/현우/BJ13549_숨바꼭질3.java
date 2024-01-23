import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ13549_숨바꼭질3 {
	private static final int INF = Integer.MAX_VALUE;
	
	private static int[] distance;
	private static boolean[] inQueue;
	private static Queue<Integer> q;
	
	private static void update(int curr, int next, int time) {
		if (distance[curr] + time < distance[next]) {
			distance[next] = distance[curr] + time;
			if (!inQueue[next]) {
				q.add(next);
				inQueue[next] = true;
			}
		}
	}
	
	private static int dist(int n, int k) {
		int curr;
		
		q = new LinkedList<>();
		inQueue = new boolean[2 * k];
		distance = new int[2 * k];
		Arrays.fill(distance, INF);
		distance[n] = 0;
		q.add(n);
		inQueue[n] = true;
		while (!q.isEmpty()) {
			curr = q.poll();
			inQueue[curr] = false;
			if (curr - 1 >= 0) {
				update(curr, curr - 1, 1);
			}
			if (curr + 1 < 2 * k) {
				update(curr, curr + 1, 1);
			}
			if (curr * 2 < 2 * k) {
				update(curr, curr * 2, 0);
			}
		}
		return distance[k];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, k;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		if (k <= n) {
			System.out.println(n - k);
			return;
		}
		System.out.println(dist(n, k));
	}
}