import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * N : 셀의 개수
 * M : 격리 시간
 * K : 미생물 군집의 개수
 */
class Cluster implements Comparable<Cluster> {
	int index;
	int x;
	int y;
	int num;
	int d;

	public void move() {
		if (this.d == 1)
			y--;
		else if (this.d == 2)
			y++;
		else if (this.d == 3)
			x--;
		else if (this.d == 4)
			x++;
	}

	public void change() {
		if (this.d == 1)
			this.d = 2;
		else if (this.d == 2)
			this.d = 1;
		else if (this.d == 3)
			this.d = 4;
		else if (this.d == 4)
			this.d = 3;
	}

	public void plus(Cluster c) {
		this.num += c.num;
	}

	public void die() {
		this.num /= 2;
		change();
	}

	public Cluster(int index, int y, int x, int num, int d) {
		this.index = index;
		this.x = x;
		this.y = y;
		this.num = num;
		this.d = d;
	}

	@Override
	public int compareTo(Cluster c) {
		return Integer.compare(c.num, this.num);
	}
}

// 상1 하2 좌3 우4
class Solution {
	static List<Cluster> clusters = new ArrayList<>();

	public static Cluster findByIdx(int index) {
		for(Cluster c : clusters) {
			if(c.index == index)
				return c;
		}
		return null;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			clusters.clear();
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int[][] board = new int[N][N];

			for (int[] col : board)
				Arrays.fill(col, -1);

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				Cluster c = new Cluster(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

				board[c.x][c.y] = i;
				clusters.add(c);
			}

			// M 시간이 끝날 때 까지
			int time = 0;
			while (true) {
				time++;
				Collections.sort(clusters);

				// 큰 미생물부터 이동
				for (Cluster c : clusters) {
					if (c.num == 0)
						continue;
					if (board[c.x][c.y] == c.index) {
						board[c.x][c.y] = -1;
					}
					c.move();
					if (c.x == 0 || c.y == 0 || c.x == N - 1 || c.y == N - 1) {
						board[c.x][c.y] = c.index;
						c.die();
						continue;
					}

					if (board[c.x][c.y] != -1) {
					
						Cluster tmp = findByIdx(board[c.x][c.y]);
						if (c.num < tmp.num) {
							tmp.plus(c);
							c.num = 0;
						} else {
							board[c.x][c.y] = c.index;
						}
					} else {
						board[c.x][c.y] = c.index;
					}

				}
				if (time == M)
					break;

			}
			int sum = 0;
			for (Cluster c : clusters) {
				if (c != null) {
					sum += c.num;
				}
			}

			System.out.printf("#%d %d\n", t, sum);
		}

	}
}