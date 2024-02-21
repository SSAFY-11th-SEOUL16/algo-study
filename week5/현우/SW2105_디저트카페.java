import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW2105_디저트카페 {
	
	/*
	 * 129 ms
	 * 왼쪽 아래부터 돌면서 DFS 탐색
	 * 사각형이 만들어질 수 있도록
	 * 방향별 최대 갈 수 있는 길이 저장
	 * 이미 방문한 디저트는 백트래킹
	 */
	
	private static final int SIZE = 22;
	private static final int MAX = 101;
	
	private static int ldMax, rdMax, ruMax, luMax, ans;
	private static int[][] map;
	private static boolean[] visited;
	
	private static void ld(int x, int y, int depth, int cnt) { // 왼쪽 아래
		if (visited[map[x][y]]) {
			return;
		}
		visited[map[x][y]] = true;
		if (depth < ldMax) {
			ld(x + 1, y - 1, depth + 1, cnt + 1); // 직진
		}
		ruMax = depth; // 이후에 ru에서 가야하는 길이
		rd(x + 1, y + 1, 1, cnt + 1); // 오른쪽 아래로 꺾음
		visited[map[x][y]] = false;
	}
	
	private static void rd(int x, int y, int depth, int cnt) { // 오른쪽 아래
		if (visited[map[x][y]]) {
			return;
		}
		visited[map[x][y]] = true;
		if (depth < rdMax) {
			rd(x + 1, y + 1, depth + 1, cnt + 1); // 직진
		}
		luMax = depth; // 이후에 lu에서 가야하는 길이
		ru(x - 1, y + 1, 1, cnt + 1); // 오른쪽 위로 꺾음
		visited[map[x][y]] = false;
	}

	private static void ru(int x, int y, int depth, int cnt) { // 오른쪽 위
		if (visited[map[x][y]]) {
			return;
		}
		visited[map[x][y]] = true;
		if (depth == ruMax) {
			lu(x - 1, y - 1, 1, cnt + 1); // ld에서 갔던 만큼 직진
		} else {
			ru(x - 1, y + 1, depth + 1, cnt + 1); // 오른쪽 위로 꺾음
		}
		visited[map[x][y]] = false;
	}

	private static void lu(int x, int y, int depth, int cnt) { // 왼쪽 위
		if (depth == luMax) { // 시작 지점 도달
			ans = Math.max(ans, cnt); // 디저트 종류 계산
			return;
		}
		if (visited[map[x][y]]) {
			return;
		}
		visited[map[x][y]] = true;
		lu(x - 1, y - 1, depth + 1, cnt + 1); // 시작 지점까지 직진
		visited[map[x][y]] = false;
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int n, i, j;
		
		n = Integer.parseInt(br.readLine());
		for (i = 0; i <= n + 1; i++) {
			map[i][n + 1] = 0;
			map[n + 1][i] = 0;
		}
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans = 0;
		for (i = 1; i < n - 1; i++) {
			for (j = 2; j < n; j++) {
				visited[map[i][j]] = true;
				ldMax = n - i - 1; // 왼쪽 아래 최대 길이
				rdMax = n - j; // 오른쪽 아래 최대 길이
				ld(i + 1, j - 1, 1, 1); // 왼쪽 아래부터 탐색
				visited[map[i][j]] = false;
			}
		}
		return ans > 1 ? ans : -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		map = new int[SIZE][SIZE];
		visited = new boolean[MAX];
		visited[0] = true;
		t = Integer.parseInt(br.readLine());
		for (testCase = 1; testCase <= t; testCase++) {
			sb.append('#').append(testCase).append(' ').append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}
