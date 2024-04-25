import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * java
 * 276 ms
 * 
 * 위상 정렬
 * 
 * 위상 정렬을 통해 건물 완성 시간 계산
 */

public class BOJ1516_게임개발 {
	static int N;
	static int degree[], times[], result[];
	static ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
	static Queue<Integer> queue = new LinkedList<>(); // 위상 정렬을 위한 큐

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		degree = new int[N + 1];
		times = new int[N + 1];
		result = new int[N + 1];
		StringTokenizer st = null;

		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<Integer>()); // 인접 리스트 초기화
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			times[i] = Integer.parseInt(st.nextToken()); // 각 건물 건설 시간 입력
			int cnt = 0;
			while (st.hasMoreTokens()) {
				int tmp = Integer.parseInt(st.nextToken()); // 건물 간 관계 설정
				if (tmp == -1)
					break;
				list.get(tmp).add(i);
				cnt++;
			}
			degree[i] = cnt; // 진입 차수 설정
		}

		topologicalSort();

		for (int i = 1; i <= N; i++) {
			System.out.println(result[i] + times[i]);
		}
	}

	public static void topologicalSort() {
		for (int i = 1; i <= N; i++) {
			if (degree[i] == 0) {
				queue.add(i); // 진입 차수가 0인 건물을 큐에 추가
			}
		}

		while (!queue.isEmpty()) {
			int current = queue.poll();
			int endTime = times[current] + result[current]; // 건물이 완성되는 시간

			for (int num : list.get(current)) {
				result[num] = Math.max(result[num], endTime); // 건물이 완성되는 시간 갱신
				degree[num]--; // 진입 차수 감소
				if (degree[num] == 0) {
					queue.add(num); // 진입 차수가 0이 되면 큐에 추가
				}
			}
		}
	}
}