import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * java
 * 572 ms
 * dfs
 * 
 * 상사가 부하 직원을 칭찬 => 칭찬은 부하의 부하 직원에게까지 전달됨
 * 상사의 리스트에 직원 추가
 * DFS를 이용해 칭찬을 전파
 */

public class BJ_14267_회사_문화1 {
	static int n, m;
	static ArrayList<Integer>[] tree;
	static int[] compliment;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		compliment = new int[n + 1];
		tree = new ArrayList[n + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 1; i <= n; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			if (tmp != -1)
				tree[tmp].add(i);
		}

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());

			int num = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			compliment[num] += w;
		}

		dfs(1);

		for (int i = 1; i <= n; i++) {
			sb.append(compliment[i]).append(" ");
		}

		System.out.println(sb.toString());
	}

	public static void dfs(int index) { // DFS를 이용해 칭찬을 전파
		for (int num : tree[index]) {
			compliment[num] += compliment[index];
			dfs(num);
		}
	}
}