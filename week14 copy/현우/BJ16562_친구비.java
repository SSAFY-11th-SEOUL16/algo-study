import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 156 ms
 * Union-Find
 * 전체 비용 합 계산
 * 친구 관계마다 Union 실행
 * 루트 노드의 비용 작은 금액으로 갱신
 * 큰 금액은 전체 비용에서 차감
 * */
public class BJ16562_친구비 {
	private static final String IMPOSSIBLE = "Oh no";
	
	private static int sum;
	private static int[] costs;
	private static int[] roots; // Parent + Rank
	
	private static final int find(int v) {
		if (roots[v] <= 0) { // 루트 노드
			return v;
		}
		return roots[v] = find(roots[v]); // Path Compression
	}
	
	private static final void union(int u, int v) {
		int ru;
		int rv;
		
		ru = find(u);
		rv = find(v);
		if (ru == rv) { // 조상이 같음
			return;
		}
		if (roots[ru] > roots[rv]) { // Union by Rank
			roots[ru] = rv;
			if (costs[ru] < costs[rv]) { // 루트 노드의 금액이 더 큰 경우
				sum -= costs[rv]; // 전체 비용에서 차감
				costs[rv] = costs[ru]; // 루트 노드 비용 갱신
			} else { // 루트 노드의 금액이 더 작거나 같은 경우
				sum -= costs[ru]; // 자식 노드 값 차감
			}
		} else {
			if (roots[ru] == roots[rv]) { // Rank 동일
				roots[ru]--; // Rank 1 증가
			}
			roots[rv] = ru;
			if (costs[ru] > costs[rv]) {
				sum -= costs[ru];
				costs[ru] = costs[rv];
			} else {
				sum -= costs[rv];
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int k;
		int i;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		costs = new int[n + 1];
		sum = 0;
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) { // 비용 입력
			costs[i] = Integer.parseInt(st.nextToken());
			sum += costs[i]; // 전체 비용
		}
		roots = new int[n + 1];
		for (i = 0; i < m; i++) { // 친구 관계 Union
			st = new StringTokenizer(br.readLine());
			union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		if (k < sum) { // 비용이 K 초과
			System.out.print(IMPOSSIBLE);
			return;
		}
		System.out.print(sum); // 비용 출력
	}
}
