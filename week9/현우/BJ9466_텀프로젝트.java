import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ9466_텀프로젝트 {
	
	/**
	 * 920 ms
	 * SCC 단순버전
	 * DFS로 탐색하면서
	 * 이미 방문된 노드를 만나면 노드번호 반환
	 * 거꾸로 되돌아가면서 카운트를 증가시키다가
	 * 해당 노드를 다시 만나면 사이클
	 * 학생 수에서 사이클 크기 차감
	 */
	
	private static final int MAX_N = 100001;
	private static final boolean[] VISITED = new boolean[MAX_N];
	
	private static int cnt, ans;
	private static int[] selection;
	private static boolean[] visited;
	
	private static int dfs(int node) {
		int val;
		
		if (visited[node]) { // 이미 방문한 노드를 만나면
			return node; // 노드 번호 반환
		}
		visited[node] = true; // 방문 체크
		val = dfs(selection[node]); // DFS
		if (val == node) { // 해당 노드를 다시 만나면
			ans -= ++cnt; // 학생 수에서 사이클 크기 차감
		}
		cnt++; // 사이클 크기 카운트
		return val;
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int n, i;
		
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			selection[i] = Integer.parseInt(st.nextToken()); // 선택된 학생
		}
		visited = Arrays.copyOf(VISITED, n + 1);
		ans = n; // 전체 학생 수
		for (i = 1; i <= n; i++) {
			cnt = 0; // 사이클 크기 카운트
			dfs(i);
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		selection = new int[MAX_N];
		t = Integer.parseInt(br.readLine());
		for (testCase = 0; testCase < t; testCase++) {
			sb.append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}
