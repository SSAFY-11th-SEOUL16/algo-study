import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ02468_안전_영역 {
	static int[][] array;
	static boolean[][] visited;
	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		array = new int[n][n];
		int max_height = 0, max_count = 0;

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str);

			for (int j = 0; j < n; j++) {
				array[i][j] = Integer.valueOf(st.nextToken());

				if (array[i][j] > max_height)
					max_height = array[i][j];	// 가장 높은 건물의 높이
			}
		}

		for (int height = 0; height <= max_height; height++) {
			int count = 0;
			visited = new boolean[n][n];
			for (int x = 0; x < n; x++) {
				for (int y = 0; y < n; y++) {
					if (!visited[x][y] && array[x][y] > height) {
						count += dfs(x, y, height);
					}
				}
			}
			if (count > max_count)
				max_count = count;
		}
		System.out.println(max_count);
	}

	public static int dfs(int x, int y, int height) {
		visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + deltas[i][0];
			int ny = y + deltas[i][1];

			if (nx < 0 || ny < 0 || nx >= array.length || ny >= array.length) {
				continue;
			}
			if (visited[nx][ny] || array[nx][ny] <= height) {
				continue;
			}
			if (!visited[nx][ny] && array[nx][ny] > height) {
				dfs(nx, ny, height);
			}
		}
		return 1;
	}
}