import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] board;
	static boolean[][] check;      // 체크한 구역인지 
	static int[] distX = { -1, 1, 0, 0 };
	static int[] distY = { 0, 0, 1, -1 };
	static int max = 0, count = 0;
	static int max_rain = 0;       // 최대로 올 수 있는 비 양 

	// 물에 잠긴 구역 체크 
	public void full(int rain) {
		if (rain == 0) {
			return;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				check[i][j] = false;
				if (board[i][j] <= rain) {
					check[i][j] = true; 
				}
			}
		}
	}

	// 출발점과 동일한 안전 구역 체크 
	public void DFS(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int dx = x + distX[i];
			int dy = y + distY[i];

			if (dx >= 0 && dx < N && dy >= 0 && dy < N) {
				if (!check[dx][dy]) {
					check[dx][dy] = true;
					DFS(dx, dy);
				}
			}
		}
	}

	// 안전구역의 출발점 찾기 
	public int safe_area(int rain) {
		full(rain);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!check[i][j]) {
					count++;            // 안전 구역 발견 
					check[i][j] = true; // 해당 구역을 출발점으로 동일한 구역의 칸 체크 
					DFS(i, j);
				}
			}
		}
		return count;
	}

	// 비의 양에 따른 최대 안전 구역 개수 찾기 
	public void solution(int rain) {
		while (rain < max_rain) {
			max = Math.max(safe_area(rain++), max);
			count = 0;
		}
	}

	public static void main(String[] args) throws IOException {
		Main M = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		check = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				max_rain = Math.max(board[i][j], max_rain);
			}
		}

		M.solution(0);
		System.out.println(max);
	}
}