import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_5650_핀볼게임 {
	private static final int RIGHT = 0;
	private static final int DOWN = 1;
	private static final int LEFT = 2;
	private static final int UP = 3;
	
	private static int size;
	private static int[] map, diff;
	private static int[][] wormhole;

	private static int getScore(int start, int dir) {
		int score, curr;

		score = 0;
		for (curr = start + diff[dir];; curr += diff[dir]) {
			if (curr == start) {
				return score;
			}
			switch (map[curr]) {
			case -1:
				return score;
			case 0:
				break;
			case 1:
				switch (dir) {
				case RIGHT: case UP:
					return 2 * score + 1;
				case DOWN:
					dir = RIGHT;
					break;
				case LEFT:
					dir = UP;
				}
				score++;
				break;
			case 2:
				switch (dir) {
				case RIGHT: case DOWN:
					return 2 * score + 1;
				case LEFT:
					dir = DOWN;
					break;
				case UP:
					dir = RIGHT;
				}
				score++;
				break;
			case 3:
				switch (dir) {
				case DOWN: case LEFT:
					return 2 * score + 1;
				case RIGHT:
					dir = DOWN;
					break;
				case UP:
					dir = LEFT;
				}
				score++;
				break;
			case 4:
				switch (dir) {
				case LEFT: case UP:
					return 2 * score + 1;
				case RIGHT:
					dir = UP;
					break;
				case DOWN:
					dir = LEFT;
				}
				score++;
				break;
			case 5:
				return 2 * score + 1;
			case 6: case 7: case 8: case 9: case 10:
				if (wormhole[map[curr]][0] == curr) {
					curr = wormhole[map[curr]][1];
				} else {
					curr = wormhole[map[curr]][0];
				}
			}
		}
	}

	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int n, end, loc, max, i, j;

		n = Integer.parseInt(br.readLine().trim());
		size = n + 2;
		end = size * size;
		diff = new int[] {1, size, -1, -size};
		wormhole = new int[11][2];
		map = new int[end];
		for (i = 0; i < size; i++) {
			map[i] = 5;
		}
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			map[i * size] = 5;
			for (j = 1; j <= n; j++) {
				loc = i * size + j;
				map[loc] = Integer.parseInt(st.nextToken());
				if (map[loc] > 5) {
					if (wormhole[map[loc]][0] == 0) {
						wormhole[map[loc]][0] = loc;
					} else {
						wormhole[map[loc]][1] = loc;
					}
				}
			}
			map[i * size + size - 1] = 5;
		}
		for (i = (size - 1) * size; i < end; i++) {
			map[i] = 5;
		}
		max = 0;
		for (i = 1; i <= n; i++) {
			for (j = 1; j <= n; j++) {
				loc = i * size + j;
				if (map[loc] == 0) {
					max = Math.max(max, Math.max(getScore(loc, LEFT),
							Math.max(getScore(loc, RIGHT),
							Math.max(getScore(loc, UP),
							getScore(loc, DOWN)))));
				}
			}
		}
		return max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int t, testCase;

		t = Integer.parseInt(br.readLine());
		for (testCase = 1; testCase <= t; testCase++) {
			sb.append('#').append(testCase).append(' ').append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}