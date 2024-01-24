import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ13549_숨바꼭질3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.valueOf(st.nextToken());
		int k = Integer.valueOf(st.nextToken());
		final int MAX_VALUE = Integer.MAX_VALUE;
		int tmp, result = 0;

		if (n >= k) {
			result = n - k;
		} else {
			boolean[] visited = new boolean[2 * k];
			int[] distance = new int[2 * k];
			Queue<Integer> q = new LinkedList<Integer>();

			Arrays.fill(distance, MAX_VALUE);

			visited[n] = true;
			distance[n] = 0;

			while (!q.isEmpty()) {
				tmp = q.poll();
				visited[tmp] = true;

				if (tmp == k) {
					result = distance[k];
					break;
				} else if (tmp * 2 < 2 * k && !visited[2 * tmp]) {
					tmp = 2 * k;
					q.add(tmp);
				} else if (tmp - 1 >= 0 && !visited[tmp - 1]) {
					tmp = tmp - 1;
					q.add(tmp);
				} else if (tmp + 1 < 2 * k && !visited[tmp + 1]){
					tmp = tmp + 1;
					q.add(tmp);
				}
			}
		}
		System.out.println(result);
	}
}