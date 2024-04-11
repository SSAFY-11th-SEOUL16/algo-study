import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BJ1414_불우이웃돕기 {
	static int N, total = 0;
	static int[] parent;
	static int[][] map;
	static PriorityQueue<Node> pq = new PriorityQueue<>();

	static class Node implements Comparable<Node> {
		int to, from, value;

		public Node(int to, int from, int value) {
			this.to = to;
			this.from = from;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.value, o.value);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			char[] charArr = br.readLine().toCharArray();
			for (int j = 1; j <= N; j++) {
				if (charArr[j - 1] >= 'a' && charArr[j - 1] <= 'z') {
					map[i][j] = charArr[j - 1] - 'a' + 1;
				} else if (charArr[j - 1] >= 'A' && charArr[j - 1] <= 'Z') {
					map[i][j] = charArr[j - 1] - 'A' + 27;
				}

				total += map[i][j];
				if (i != j && map[i][j] != 0) {
					pq.add(new Node(i, j, map[i][j]));
				}
			}
		}

		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		int answer = 0;
		int connectionCount = 0;
		while (!pq.isEmpty()) {
			Node current = pq.poll();
			if (union(current.to, current.from)) {
				answer += current.value;
				connectionCount++;
			}
		}
		System.out.println(connectionCount == N - 1 ? total - answer : -1);
	}

	public static int find(int x) {
		if (x != parent[x]) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}

	public static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y)
			return false;
		parent[y] = x;
		return true;
	}
}