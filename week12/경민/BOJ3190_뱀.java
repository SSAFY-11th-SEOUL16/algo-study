import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {

	static int n, k;
	static int[][] board;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static class Node {
		int time;
		char d;

		public Node(int time, char d) {
			this.time = time;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());

		board = new int[n + 1][n + 1];

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			board[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
		}

		int l = Integer.parseInt(br.readLine());
		Deque<Node> spin = new ArrayDeque<>();
		for (int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			spin.add(new Node(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));
		}

		// 초기값 셋팅
		int time = 0;
		int x = 1, y = 1;
		int dir = 1; // 오른쪽으로 이동
		Deque<Point> snake = new ArrayDeque<Point>();
		snake.add(new Point(x, y));
		board[x][y] = 2;

		while (true) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			time++;
			if (nx < 1 || nx > n || ny < 1 || ny > n)
				break; // 벽에 부딪힌 경우
			else if (board[nx][ny] == 2)
				break; // 자기자신에게 부딪힌 경우
			else if (board[nx][ny] == 0) {
				// 사과가 없다면 몸이 안늘어나므로 꼬리부분 빼기
				Point point = snake.poll();
				board[point.x][point.y] = 0;
			}

			if (!spin.isEmpty() && time == spin.peek().time) {
				Node node = spin.poll();

				if (node.d == 'L')
					dir = dir - 1 < 0 ? 3 : dir - 1;
				else if (node.d == 'D')
					dir = dir + 1 > 3 ? 0 : dir + 1;
			}

			board[nx][ny] = 2;
			snake.offer(new Point(nx, ny));
			x = nx;
			y = ny;

		}
		System.out.println(time);

	}

}