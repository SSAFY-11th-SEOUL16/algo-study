import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SW2383_점심_식사시간 {
	static int[][] map;
	static int result = 0;
	static ArrayList<Point> list;
	static ArrayList<Point> stair;
	static int[] step;
	static int min;
	static int[] times;

	static class Point implements Comparable<Point> {
		int r;
		int c;
		int distance;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		public Point(int r, int c, int distance) {
			super();
			this.r = r;
			this.c = c;
			this.distance = distance;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.distance, o.distance);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			list = new ArrayList<>();
			stair = new ArrayList<>();
			min = Integer.MAX_VALUE;
			times = new int[2];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						list.add(new Point(i, j));
					} else if (map[i][j] >= 2) {
						stair.add(new Point(i, j));
					}
				}
			}
			step = new int[list.size()];

			makePerm(0);

			System.out.println("#" + test_case + " " + min);
		}
	}

	private static void makePerm(int depth) {
		if (depth == list.size()) {
			findDistance(0);
			findDistance(1);
			min = Math.min(min, Math.max(times[0], times[1]));
			return;
		}

		for (int i = 0; i < 2; i++) {
			step[depth] = i;
			makePerm(depth + 1);
		}
	}

	private static void findDistance(int value) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		Point currentStep = stair.get(value);

		for (int i = 0; i < list.size(); i++) {
			if (step[i] == value) {
				Point currentPerson = list.get(i);
				pq.offer(new Point(currentPerson.r, currentPerson.c, calculateDistance(currentPerson, currentStep)));
			}
		}

		ArrayList<Point> tmp = new ArrayList<>();

		int cnt = 0;
		int time = 0;
		int num = map[currentStep.r][currentStep.c];

		while (!pq.isEmpty()) {
			int size = pq.size();
			time++;
			while (size-- > 0) {
				Point cur = pq.poll();

				if (cur.distance - 1 > 0) {
					tmp.add(new Point(cur.r, cur.c, cur.distance - 1));
				} else if (cur.distance == 0) {
					if (cnt < 3) {
						cnt++;
						tmp.add(new Point(cur.r, cur.c, cur.distance - 1));
					} else {
						tmp.add(new Point(cur.r, cur.c, 0));
					}
				} else if (cur.distance > -num) {
					tmp.add(new Point(cur.r, cur.c, cur.distance - 1));
				} else if (cur.distance == -num) {
					cnt--;
				}
			}
			pq.addAll(tmp);
			tmp.clear();
		}
		times[value] = time;
	}

	private static int calculateDistance(Point person, Point stair) {
		return Math.abs(person.r - stair.r) + Math.abs(person.c - stair.c);
	}
}