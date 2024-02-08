class Solution {

	static int[][] board;
	static int[] distX = { 1, -1, 0, 0 };
	static int[] distY = { 0, 0, 1, -1 };
	static boolean[][] visited;
	static int answer = Integer.MAX_VALUE;

	public void dfs(int x, int y, int count, int itemX, int itemY) {
		if (count > answer)
			return;
		if (x == itemX && y == itemY) {
			answer = Math.min(count, answer);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + distX[i];
			int ny = y + distY[i];

			if (board[ny][nx] == 1 && !visited[ny][nx]) {
				visited[ny][nx] = true;
				dfs(nx, ny, count + 1, itemX, itemY);
				visited[ny][nx] = false;
			}
		}

	}

	public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
		board = new int[102][102];
		visited = new boolean[102][102];
		for (int i = 0; i < rectangle.length; i++) {
			int x1 = rectangle[i][0];
			int y1 = rectangle[i][1];
			int x2 = rectangle[i][2];
			int y2 = rectangle[i][3];

			for (int x = x1 * 2; x <= x2 * 2; x++) {
				board[2 * y1][x] = (board[2 * y1][x] == -1 ? -1 : 1);
				board[2 * y2][x] = (board[2 * y2][x] == -1 ? -1 : 1);

			}
			for (int y = y1 * 2; y <= y2 * 2; y++) {
				board[y][2 * x1] = (board[y][2 * x1] == -1 ? -1 : 1);
				board[y][2 * x2] = (board[y][2 * x2] == -1 ? -1 : 1);
			}
			for (int y = 2 * y1 + 1; y < 2 * y2; y++) {
				for (int x = 2 * x1 + 1; x < 2 * x2; x++) {
					board[y][x] = -1;
				}
			}
		}

		dfs(2 * characterX, 2 * characterY, 0, 2 * itemX, 2 * itemY);
		return answer / 2;
	}

}