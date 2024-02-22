import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SW1949_등산로조성 {
	
	/*
	 * 107 ms
	 * 가장 높은 지점들 탐색
	 * DFS로 점점 낮은 지점으로 이동
	 * 현재 높이보다 높은 곳은 현재 높이 - 1
	 * 깎음 여부 저장하고 이동
	 */
	
	private static final int SIZE = 10;
	private static final int INF = Integer.MAX_VALUE;
	private static final int[] dirX = {-1, 0, 1, 0};
	private static final int[] dirY = {0, -1, 0, 1};
	
	private static int k, ans;
	private static int[][] map;
	private static boolean[][] visited;
	private static LinkedList<int[]> starts;
	
	private static void dfs(int x, int y, boolean cut, int height, int prev, int depth) {
		int nx, ny, i;
		
		if (visited[x][y]) {
			return;
		}
		if (height >= prev) {
			ans = Math.max(ans, depth);
			return;
		}
		visited[x][y] = true;
		for (i = 0; i < 4; i++) {
			nx = x + dirX[i];
			ny = y + dirY[i];
			if (!cut && map[nx][ny] >= height) {
				dfs(nx, ny, true, Math.max(height - 1, map[nx][ny] - k), height, depth + 1);
			} else {
				dfs(nx, ny, cut, map[nx][ny], height, depth + 1);
			}
		}
		visited[x][y] = false;
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int n, max, i, j;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		for (i = 0; i <= n + 1; i++) {
			map[n + 1][i] = INF;
			map[i][n + 1] = INF;
		}
		max = 0;
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > max) {
					starts = new LinkedList<>();
					max = map[i][j];
					starts.add(new int[] {i, j});
				} else if (map[i][j] == max) {
					starts.add(new int[] {i, j});
				}
			}
		}
		ans = 0;
		visited = new boolean[n + 2][n + 2];
		for (int[] start : starts) {
			dfs(start[0], start[1], false, max, INF, 0);
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase, i;
		
		map = new int[SIZE][SIZE];
		for (i = 0; i < SIZE; i++) {
			map[0][i] = INF;
			map[i][0] = INF;
		}
		t = Integer.parseInt(br.readLine());
		for (testCase = 1; testCase <= t; testCase++) {
			sb.append('#').append(testCase).append(' ').append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}
