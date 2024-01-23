import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2468_안전영역 {
	private static int n;
	private static int[][] city;
	private static boolean[][] visited;
	
	private static void dfs(int i, int j, int rain) {
		if (i < 0 || j < 0 || i >= n || j >= n || visited[i][j] || city[i][j] <= rain) {
			return;
		}
		visited[i][j] = true;
		dfs(i, j - 1, rain);
		dfs(i - 1, j, rain);
		dfs(i, j + 1, rain);
		dfs(i + 1, j, rain);
	}
	
	private static int safe(int rain) {
		int cnt, i, j;
		
		visited = new boolean[n][n];
		cnt = 0;
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				if (city[i][j] > rain && !visited[i][j]) {
					dfs(i, j, rain);
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int max, second, ans, i, j;
		
		n = Integer.parseInt(br.readLine());
		city = new int[n][n];
		max = 0;
		second = -1;
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < n; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
				if (city[i][j] > max) {
					second = max;
					max = city[i][j];
				} else if (second < city[i][j] && city[i][j] < max) {
					second = city[i][j];
				}
			}
		}
		ans = 1;
		for (i = 1; i <= second; i++) {
			ans = Math.max(ans, safe(i));
		}
		System.out.println(ans);
	}
}