import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11660_구간_합_구하기5 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str);

		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		int[][] array = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				array[i][j] = array[i][j - 1] + array[i - 1][j] - array[i - 1][j - 1] + Integer.valueOf(st.nextToken());
			}
		}

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int[] x = new int[2];
			int[] y = new int[2];
			for (int j = 0; j < 2; j++) {
				x[j] = Integer.valueOf(st.nextToken());
				y[j] = Integer.valueOf(st.nextToken());
			}
			int sum = array[x[1]][y[1]] - array[x[0] - 1][y[1]] - array[x[1]][y[0] - 1] + array[x[0] - 1][y[0] - 1];
			System.out.println(sum);
		}
	}
}