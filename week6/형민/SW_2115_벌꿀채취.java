import java.io.*;
import java.util.*;

class SW_2115_벌꿀채취 {
	static int n, m, c; //벌통 크기, 벌통 개수, 벌꿀 최대 양
	static int arr[][];
	static boolean visited[][];
	static ArrayList<Integer> worker1; //일꾼1이 선택한
	static ArrayList<Integer> worker2;
	static int result, worker1Benefit, worker2Benefit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			result = 0;
			arr = new int[n][n];
			visited = new boolean[n][n];

			for (int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < n; k++) {
					arr[j][k] = Integer.parseInt(st.nextToken());
				}
			}

			worker1 = new ArrayList<>();
			worker2 = new ArrayList<>();
			worker1Benefit = worker2Benefit = 0;
			backtrack(0, 0, 0);

			sb.append("#" + (i + 1) + " " + result + "\n");
		}
		System.out.println(sb);
	}

	static void backtrack(int x, int y, int select) {
		//두 일꾼이 벌통을 선택한 경우
		if (select == 2) {
			worker1Benefit = worker2Benefit = 0;
			backtrack2(0, 0, 0); //일꾼1이 얻을 수 있는 최대 수익 계산
			backtrack3(0, 0, 0); //일꾼2가 얻을 수 있는 최대 수익 계산

			result = Math.max(result, (worker1Benefit + worker2Benefit));
			return;
		}

		for (int i = x; i < n; i++) {
			if (i > x)
				y = 0;

			for (int j = y; j <= n - m; j++) {
				if (select == 0) {
					for (int k = 0; k < m; k++) {
						worker1.add(arr[i][j + k]);
					}
				} else if (select == 1) {
					for (int k = 0; k < m; k++) {
						worker2.add(arr[i][j + k]);
					}
				}

				backtrack(i, j + m, select + 1);

				if (select == 0)
					worker1.clear();
				else if (select == 1)
					worker2.clear();
			}
		}

	}

	static void backtrack2(int x, int totalHoney, int benefit) {
		worker1Benefit = Math.max(worker1Benefit, benefit);
		for (int i = x; i < worker1.size(); i++) {
			int curHoney = worker1.get(i);
			if (totalHoney + curHoney <= c) {
				backtrack2(i + 1, totalHoney + curHoney, benefit + curHoney * curHoney);
			}
		}
	}

	static void backtrack3(int x, int totalHoney, int benefit) {
		worker2Benefit = Math.max(worker2Benefit, benefit);

		for (int i = x; i < worker2.size(); i++) {
			int curHoney = worker2.get(i);
			if (totalHoney + curHoney <= c) {
				backtrack3(i + 1, totalHoney + curHoney, benefit + curHoney * curHoney);
			}
		}
	}

}
