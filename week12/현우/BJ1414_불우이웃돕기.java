import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BJ1414_불우이웃돕기 {
	
	/**
	 * 80 ms
	 * MST
	 * 알파벳 별 길이 값 저장
	 * 전체 랜선 길이 합 - MST
	 * MST 구성이 불가능하면 -1 출력
	 * Kruskal 이용
	 */
	
	private static final int a = 'a';
	private static final int z = 'z';
	private static final int A = 'A';
	private static final int Z = 'Z';
	private static final int NUM = 27;
	private static final String IMPOSSIBLE = "-1";
	
	private static final class Edge implements Comparable<Edge> {
		int from, to, weight;
		
		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) { // 길이 오름차순 정렬
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	private static int[] roots; // Parent + Rank
	
	private static int find(int v) {
		if (roots[v] <= 0) { // 루트 노드
			return v;
		}
		return roots[v] = find(roots[v]); // Path compression
	}
	
	private static boolean union(int u, int v) {
		int ru, rv;
		
		ru = find(u);
		rv = find(v);
		if (ru == rv) { // 조상이 같음
			return false;
		}
		if (roots[ru] > roots[rv]) { // Union by Rank
			roots[ru] = rv;
		} else {
			if (roots[ru] == roots[rv]) { // Rank 동일
				roots[ru]--; // Rank 1 증가
			}
			roots[rv] = ru;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n, sum, i, j;
		int[] weights;
		char[] str;
		Edge edge;
		PriorityQueue<Edge> pq;
		
		weights = new int[z + 1];
		for (i = a; i <= z; i++) { // 소문자 길이
			weights[i] = i - a + 1;
		}
		for (i = A; i <= Z; i++) { // 대문자 길이
			weights[i] = i - A + NUM;
		}
		n = Integer.parseInt(br.readLine());
		sum = 0;
		pq = new PriorityQueue<>();
		for (i = 0; i < n; i++) {
			str = br.readLine().toCharArray();
			for (j = 0; j < n; j++) {
				sum += weights[str[j]]; // 전체 랜선 길이 합
				if (weights[str[j]] != 0) { // 랜선 존재
					pq.offer(new Edge(i + 1, j + 1, weights[str[j]])); // 간선 추가
				}
			}
		}
		roots = new int[n + 1];
		for (i = 0; i < n - 1;) { // Kruskal
			edge = pq.poll();
            if (edge == null) { // MST 구성 불가
                System.out.print(IMPOSSIBLE);
			    return;
            }
			if (union(edge.from, edge.to)) { // Union 성공
				i++; // 간선 카운트
				sum -= edge.weight; // 전체 - 선택된 간선
			}
		}
		System.out.print(sum);
	}
}
