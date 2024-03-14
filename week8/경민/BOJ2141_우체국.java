import java.io.*;
import java.util.*;

public class Main {
	// N개의 마을 정렬
	static class Node implements Comparable<Node> {
		int index;
		long count;

		public Node(int index, long count) {
			this.index = index;
			this.count = count;
		}

		@Override
		public int compareTo(Node o) {
			if (this.index < o.index)
				return -1;
			return 1;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		List<Node> list = new ArrayList<Main.Node>();
		long sum = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Node(Integer.parseInt(st.nextToken()), Long.parseLong(st.nextToken())));
			sum += list.get(i).count;
		}

		Collections.sort(list);

		// 전체 인원 수의 중간이 되는 값
		long count = 0;
		int result = 0;
		for (int i = 0; i < n; i++) {
			count += list.get(i).count;

			if (count >= (sum + 1) / 2) {
				result = list.get(i).index;
				break;
			}
		}

		System.out.println(result);
	}
}
