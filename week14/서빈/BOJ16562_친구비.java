import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 
 * java
 * 164 ms
 * 
 * Union-Find
 * 
 * 각 학생들을 독립적인 집합으로 만들어두고, 친구 관계를 통해 집합을 합쳐가며 최소 비용을 계산함
 * union-find 알고리즘을 사용하여 부모를 찾고, 친구 관계를 합치는 과정에서 최소 비용을 선택함
 * 		union 함수에서는 각 집합의 부모를 찾아서 더 적은 비용을 갖는 쪽으로 합치는 과정을 진행함
 * 		결과적으로 각 집합의 부모가 자기 자신일 경우에만 해당 집합의 친구비를 결과값에 더함
 */

public class BOJ16562_친구비 {
	static int N, M, k;
	static int parent[], money[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		money = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			money[i] = Integer.parseInt(st.nextToken());
			parent[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			union(v, w); // 친구 관계 입력받아 합치기
		}

		int result = 0;
		for (int i = 1; i <= N; i++) {
			if (parent[i] == i) { // 부모가 자기 자신인 경우
				result += money[i]; // 해당 집합의 친구비를 결과값에 더함
			}
		}
		if (result > k) {
			System.out.println("Oh no");
		} else {
			System.out.println(result);
		}
	}

	public static boolean union(int a, int b) {
		int a_parent = find(a);
		int b_parent = find(b);

		if (a_parent == b_parent) { // 이미 같은 집합에 속해있다면
			return false;
		}
		if (money[a_parent] < money[b_parent]) { // 친구비가 적은 쪽의 부모로 합침
			parent[b_parent] = a_parent;
		} else {
			parent[a_parent] = b_parent;
		}
		return true;
	}

	public static int find(int a) {
		if (a == parent[a]) { // 자기 자신이 부모인 경우
			return a;
		}
		return parent[a] = find(parent[a]); // 아니면 부모를 찾아가며 경로 압축
	}
}