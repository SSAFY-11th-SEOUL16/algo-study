import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Point {
	int key;
	int px;
	int py;

	public Point(int key, int px, int py) {
		this.key = key;
		this.px = px;
		this.py = py;
	}
}

public class Solution {
	static int N;
	static int[][] board;
	static int[] distX = { -1, 1, 0, 0 };
	static int[] distY = { 0, 0, -1, 1 };
	static int count = 0, startX, startY;
	static List<Point> holes = new ArrayList<Point>();
	static int maxScore = 0;

	public static int change(int shape, int d) {
		int change = 0;
		switch (shape) {
		case 1:
			if (d == 2) change = 4;
			else if (d == 3) change = 1;
			break;
		case 2:
			if (d == 1) change = 4;
			else if (d == 3) change = 2;
			break;
		case 3:
			if (d == 1) change = 3;
			else if (d == 4) change = 2;
			break;
		case 4:
			if (d == 2) change = 3;
			else if (d == 4) change = 1;
			break;
		}
		return change;
	}

	// d : 1(상) 2(하) 3(좌) 4(우)
	public static void solution(int x, int y, int d) {
		int nx = 0, ny = 0;
		while (true) {
			nx = x + distX[d - 1];
			ny = y + distY[d - 1];

			if ((board[nx][ny] == 1 && (d == 1 || d == 4)) || (board[nx][ny] == 2 && (d == 2 || d == 4))
					|| (board[nx][ny] == 3 && (d == 2 || d == 3))
					|| (board[nx][ny] == 4 && (d == 1 || d == 3) || board[nx][ny] == 5 || board[nx][ny] == -2)) {
				// 벽을 만난 경우 or 면이 수직/수평인 모양을 만난 경우
				count = count * 2 + 1;
				maxScore = Math.max(count, maxScore);
				break;
			}

			if (board[nx][ny] == -1 || (startX == nx && startY == ny)) {
				// 블랙홀 또는 시작지점에 도착한 경우, 점수가 최대인지
				maxScore = Math.max(count, maxScore);
				break;
			}

			x = nx;
			y = ny;

			if (board[nx][ny] >= 1 && board[nx][ny] <= 4) {
				count++;
				d = change(board[nx][ny], d);
			} else if (board[nx][ny] >= 6 && board[nx][ny] <= 10) {
				// 웜홀을 만난 경우
				for (int i = 0; i < holes.size(); i++) {
					Point p = holes.get(i);
					if (p.key == board[nx][ny] && (p.px != nx || p.py != ny)) {
						// 다른 웜홀로 이동
						x = p.px;
						y = p.py;
						break;
					}
				}
			}

		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= T; t++) {
			maxScore = 0;
			holes.clear();
			N = Integer.parseInt(br.readLine().trim());
			board = new int[N + 2][N + 2];
			for (int[] cols : board)
				Arrays.fill(cols, -2);
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				for (int j = 1; j <= N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if (board[i][j] >= 6 && board[i][j] <= 10) {
						holes.add(new Point(board[i][j], i, j));
					}
				}
			}

			for (int i = 1; i <= N; i++)
				for (int j = 1; j <= N; j++)
					if (board[i][j] == 0) {
						startX = i;
						startY = j;
						for (int k = 1; k <= 4; k++) {
							count = 0;
							solution(i, j, k);
						}
					}

			sb.append("#" + t + " " + maxScore + "\n");
		}
		System.out.println(sb);
	}
}