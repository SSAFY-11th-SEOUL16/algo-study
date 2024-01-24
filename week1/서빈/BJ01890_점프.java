import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ01890_점프 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] array = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str);
			for (int j = 1; j <= n; j++) {
				array[i][j] = Integer.valueOf(st.nextToken());
			}
		}

		long[][] path = new long[n + 1][n + 1];
		path[1][1] = 1;

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				int move = array[i][j];
				if (move == 0)
					break;
				if (move + i <= n)
					path[i + move][j] += path[i][j];
				if (move + j <= n)
					path[i][j + move] += path[i][j];
			}
		}
		System.out.println(path[n][n]);
	}
}