import java.io.*;
import java.util.*;

public class Main {
	static int n, m, result, count, countCopy;
	static int[][] board, boardCopy;
	static List<int[]> list = new ArrayList<int[]>();
	static List<int[]> wall = new ArrayList<int[]>();
	static List<int[]> birus = new ArrayList<int[]>();
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void bfs(int[] start) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { start[0], start[1] });
		while (!q.isEmpty()) {
			int[] now = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];

				if (nx >= 0 && nx < n && ny >= 0 && ny < m && boardCopy[nx][ny] == 0) {
					boardCopy[nx][ny]++;
					countCopy--;
					q.offer(new int[] { nx, ny });
				}
			}
		}
	}

	public static void copy() {
		// 깊은 복사
		for (int i = 0; i < board.length; i++) 
			boardCopy[i] = board[i].clone();
		
		// 0 인 영역에서 벽을 세울 3칸 제외한 수 count 
		countCopy = count - 3;
	}

	// 0인 영역 list에서 3개 뽑기
	public static void combi(int start) {
		if (wall.size() == 3) {
			copy();
			
			// 벽 세우기
			for (int[] i : wall)
				boardCopy[i[0]][i[1]] = 1;

			// 바이러스 번식
			for (int[] i : birus)
				bfs(i);
			
			result = Math.max(result, countCopy);
			return;
		}

		for (int i = start; i < list.size(); i++) {
			wall.add(new int[] { list.get(i)[0], list.get(i)[1] });
			combi(start + 1);
			wall.remove(wall.size() - 1);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		// 0은 빈칸, 1은 벽, 2는 바이러스
		// 안전영역의 최대크기
		board = new int[n][m];
		boardCopy = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());

				if (board[i][j] == 0) {
					list.add(new int[] { i, j });
					count++;
				}
				if (board[i][j] == 2)
					birus.add(new int[] { i, j });
			}
		}

		combi(0);
		
		System.out.println(result);

	}
}
