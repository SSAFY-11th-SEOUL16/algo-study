import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW2115_벌꿀채취 {
	static int N, M, C;
	static int[][] profit;
	static int[][] map;
	static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			profit = new int[N][N];
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			result = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= N - M; j++) {
					makeProfit(i, j, 0, 0, 0);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= N - M; j++) {
					makeComb(i, j + M, 1, profit[i][j]);
				}
			}
			System.out.println("#" + test_case + " " + result);
		}
	}

	private static void makeComb(int x, int y, int count, int sum) {
		if (count == 2) {
			result = Math.max(result, sum);
			return;
		}

		for (int i = x; i < N; i++) {
			for (int j = y; j <= N - M; j++) {
				makeComb(i, j, count + 1, sum + profit[i][j]);
			}
			y = 0;
		}
	}

	private static void makeProfit(int i, int j, int count, int sum, int totalSum) {
		if (sum > C)
			return;
		if (count == M) {
			profit[i][j - M] = Math.max(profit[i][j - M], totalSum);
			return;
		}

		makeProfit(i, j + 1, count + 1, sum + map[i][j], totalSum + map[i][j] * map[i][j]);
		makeProfit(i, j + 1, count + 1, sum, totalSum);
	}
}