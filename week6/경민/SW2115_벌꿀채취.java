import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 가로로 m개의 벌통을 선택할 수 있고, 채취할 수 있는 꿀의 최대는 c
 */
public class Solution {
	static int result, n, m, c;
	static int[][] board;
	static boolean[][] visited;
	static Node[] rowValue;
	static List<int[]> honeyList = new ArrayList<>();
	static List<Point> combi = new ArrayList<>();

	static class Node {
		int money; // 최대수익
		List<int[]> points = new ArrayList<>(); // 채취한 벌통의 좌표 리스트 (1~m개)

		public Node(int money, List<int[]> points) {
			this.money = money;
			this.points = points;
		}
	}

	static class Point {
		int x;
		int y;
		int value;

		public Point(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}

	public static void selected(int index, int cnt, int amount, int row) {
		if (index > m || amount > c)
			return;
		if (index == m) {
			int sum = 0;
			for (Point p : combi)
				sum += p.value * p.value;

			if (rowValue[row].money < sum) {
				rowValue[row].money = sum;
				rowValue[row].points.clear();
				for (Point p : combi)
					rowValue[row].points.add(new int[] { p.x, p.y });

			}
			return;
		}

		Point p = new Point(honeyList.get(index)[0], honeyList.get(index)[1],
				board[honeyList.get(index)[0]][honeyList.get(index)[1]]);

		combi.add(p);
		selected(index + 1, cnt + 1, amount + p.value, row);
		combi.remove(p);
		selected(index + 1, cnt, amount, row);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken()); // m개 중 채취할 벌통 선택
			c = Integer.parseInt(st.nextToken());

			board = new int[n][n];
			rowValue = new Node[n];
			visited = new boolean[n][n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++)
					board[i][j] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < n; i++) {
				// i 행 마다 최대 수익 구하기
				rowValue[i] = new Node(0, new ArrayList<>());
				for (int j = 0; j <= n - m; j++) {
					// j부터 j+m 까지 추출 후 부부집합찾기
					for (int k = j; k < j + m; k++)
						honeyList.add(new int[] { i, k });

					// 추출한 honeyList 에서 최대 수익과 그 좌표 구하기
					selected(0, 0, 0, i);

					honeyList.clear();
				}
			}

			// 행마다 구한 최대 수익 중 가장 수익이 큰 행 구하기
			int index = 0, max = 0;
			for (int i = 0; i < rowValue.length; i++) {
				if (max < rowValue[i].money) {
					max = rowValue[i].money;
					index = i;
				}
			}

			result += rowValue[index].money;

			// 방문체크
			rowValue[index].money = 0;
			for (int i = 0; i < rowValue[index].points.size(); i++)
				visited[index][rowValue[index].points.get(i)[1]] = true;

			// 인덱스 행의 최대 수익 다시 구하기
			for (int j = 0; j <= n - m; j++) {
				// j부터 j+m 까지 추출 후 부부집합찾기 단, 방문체크가 안된 구역만
				for (int k = j; k < j + m; k++) {
					if (visited[index][k])
						break;
					honeyList.add(new int[] { index, k });
				}
				// 추출한 honeyList 에서 최대 수익과 그 좌표 구하기
				if (honeyList.size() == m)
					selected(0, 0, 0, index);

				honeyList.clear();
			}

			max = 0;
			index = 0;
			for (int i = 0; i < rowValue.length; i++) {
				if (max < rowValue[i].money) {
					max = rowValue[i].money;
					index = i;
				}
			}

			result += rowValue[index].money;

			sb.append('#').append(t).append(' ').append(result).append('\n');
			result = 0;
		}
		System.out.println(sb);
	}
}