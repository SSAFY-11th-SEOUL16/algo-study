import java.io.*;
import java.util.*;

public class Main {

	static class Node implements Comparable<Node> {
		String name;
		int depth;
		HashMap<String, Node> map;

		public Node(String name, int depth, HashMap<String, Node> map) {
			this.name = name;
			this.depth = depth;
			this.map = map;
		}

		@Override
		public int compareTo(Node o) {
			if (this.depth == o.depth)
				return this.name.compareTo(o.name);
			else
				return o.depth - this.depth;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		HashMap<String, Node> firstMap = new HashMap<>(); 

		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int dep = Integer.parseInt(st.nextToken());

			int curDep = 0;
			HashMap<String, Node> curMap = null;
			String key = st.nextToken();
			if (firstMap.containsKey(key))
				curMap = firstMap.get(key).map;
			else {
				firstMap.put(key, new Node(key, curDep, new HashMap<>()));
				curMap = firstMap.get(key).map;
			}
			
			for (int j = 1; j < dep; j++) {
				key = st.nextToken();
				if (curMap.containsKey(key))
					curMap = curMap.get(key).map;
				else {
					curMap.put(key, new Node(key, ++curDep, new HashMap<>()));
					curMap = curMap.get(key).map;
				}
			}
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (Node node : firstMap.values()) {
			pq.add(node);
		}

		while (!pq.isEmpty()) {
			Node poll = pq.poll();

			for (int i = 0; i < poll.depth; i++) {
				sb.append("--");
			}

			sb.append(poll.name + "\n");

			for (Node node : poll.map.values()) {
				pq.add(node);
			}
		}

		System.out.println(sb.toString());
	}
}
