import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ3190_뱀 {
	static int N, K, L, map[][], result = 1, len = 1;
	static PriorityQueue<Turn> turnlist = new PriorityQueue<>();
	static Deque<Pos> snake = new ArrayDeque<>();
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Turn implements Comparable<Turn> {
		int X;
		char C;

		public Turn(int x, char c) {
			X = x;
			C = c;
		}

		@Override
		public int compareTo(Turn o) {
			return Integer.compare(this.X, o.X);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y] = 1;
		}

		snake.offer(new Pos(1, 1));
		map[1][1] = -1;

		L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			turnlist.add(new Turn(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));
		}

		int direction = 0;
		int px = 1;
		int py = 1;

		while (true) {
			int nx = px + dx[direction];
			int ny = py + dy[direction];

			if (nx < 1 || ny < 1 || nx > N || ny > N || map[nx][ny] == -1) {
				break;
			}

			if (map[nx][ny] != 1) {
				Pos pos = snake.pollFirst();
				map[pos.x][pos.y] = 0;
			}

			snake.add(new Pos(nx, ny));
			map[nx][ny] = -1;

			if (!turnlist.isEmpty() && turnlist.peek().X == len) {
				Turn turn = turnlist.poll();
				direction = changeDirection(direction, turn.C);
			}

			px = nx;
			py = ny;
			len++;
			result++;
		}
		System.out.println(result);
	}

	static int changeDirection(int curDir, char c) {
		if (c == 'D') {
			curDir = (curDir + 1) % 4;
		} else if (c == 'L') {
			curDir = (curDir + 3) % 4;
		}
		return curDir;
	}
}
