import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW1953_탈주범검거 {
	
	/*
	 * 135 ms
	 * 터널 종류 비트로 저장
	 * ex) ㄱ자 터널 = 0110(2) = 6
	 * 연결된 곳 DFS로 탐색
	 * 원래 저장된 거리보다 줄어드는 경우에만 탐색
	 */
	
	private static final int INF = Integer.MAX_VALUE;
	private static final int SIZE = 52;
	private static final int UP = 8;
	private static final int DOWN = 4;
	private static final int LEFT = 2;
	private static final int RIGHT = 1;
	private static final int[] type = {0, 15, 12, 3, 9, 5, 6, 10}; // 터널 종류 비트
	
	private static int l, ans;
	private static int[][] map, distance;
	
	private static void dfs(int x, int y, int depth) {
		if (distance[x][y] <= depth) { // 기존에 저장된 거리가 더 짧으면
			return;
		}
		if (distance[x][y] == INF) { // 처음 탐색하는 곳인 경우
			ans++; // 정답 + 1
		}
		distance[x][y] = depth;
		if (depth < l) { // 현재 거리가 L 미만일 경우
			if ((map[x][y] & UP) == UP && (map[x - 1][y] & DOWN) == DOWN) { // 연결된 곳 탐색
				dfs(x - 1, y, depth + 1);
			}
			if ((map[x][y] & DOWN) == DOWN && (map[x + 1][y] & UP) == UP) {
				dfs(x + 1, y, depth + 1);
			}
			if ((map[x][y] & LEFT) == LEFT && (map[x][y - 1] & RIGHT) == RIGHT) {
				dfs(x, y - 1, depth + 1);
			}
			if ((map[x][y] & RIGHT) == RIGHT && (map[x][y + 1] & LEFT) == LEFT) {
				dfs(x, y + 1, depth + 1);
			}
		}
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int n, m, r, c, i, j;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken()) + 1;
		c = Integer.parseInt(st.nextToken()) + 1;
		l = Integer.parseInt(st.nextToken());
		for (i = 1; i <= n; i++) {
			map[i][m + 1] = 0;
		}
		for (i = 1; i <= m; i++) {
			map[n + 1][i] = 0;
		}
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 1; j <= m; j++) {
				map[i][j] = type[Integer.parseInt(st.nextToken())]; // 터널 타입 저장
			}
		}
		ans = 0;
		distance = new int[n + 1][m + 1];
		for (i = 1; i <= n; i++) {
			Arrays.fill(distance[i], INF); // 거리 초기화
		}
		dfs(r, c, 1); // 탐색
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		map = new int[SIZE][SIZE];
		t = Integer.parseInt(br.readLine());
		for (testCase = 1; testCase <= t; testCase++) {
			sb.append('#').append(testCase).append(' ').append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}
