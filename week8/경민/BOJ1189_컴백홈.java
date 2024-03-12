import java.io.*;
import java.util.*;

public class Main {

	static int n, m, k, result;
	static char[][] board;
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	static void dfs(int x, int y, int count) {
		if(count > k) return;
		if (x == 0 && y == m - 1) {
			if(count == k)
				result++;
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && nx < n && ny >= 0 && ny < m && board[nx][ny] != 'T' && !visited[nx][ny]) {
				visited[nx][ny] = true;
				dfs(nx, ny, count + 1);
				visited[nx][ny] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		board = new char[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++)
				board[i][j] = str.charAt(j);
		}

		visited[n - 1][0] = true;
		dfs(n - 1, 0, 1);

		System.out.println(result);
	}

}
