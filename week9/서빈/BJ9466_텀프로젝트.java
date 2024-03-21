import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * java
 * 1060 ms
 * DFS
 * 
 * 프로젝트 팀 구성을 배열으로부터 어느 팀에도 속하지 않는 학생들의 수를 계산
 * 모든 학생에 대해 DFS 탐색
 * 팀을 형성할 수 있는 학생들을 찾음
 * - DFS에서 사이클을 발견하는 순간, 해당 사이클에 속하는 학생들은 팀을 형성할 수 있음
 * 
 */

public class BJ9466_텀프로젝트 {
	private static int T, N, result;
	private static int[] arr;
	private static boolean[] visited, checked; // DFS 실행 중 방문 여부 체크, 검사 여부를 체크

	public static void dfs(int idx) {
		if (checked[idx]) // 현재 방문한 학생의 검사 여부를 체크
			return;
		if (visited[idx]) { // 순환 구조(사이클)가 형성되는지 체크
			checked[idx] = true;
			result++;
		}

		visited[idx] = true; // 처음 방문하는 곳 => visited 표시만 함
		dfs(arr[idx]);
		checked[idx] = true; // 검사 완료
		visited[idx] = false; // 원상복구
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			result = 0;
			arr = new int[N + 1];
			checked = new boolean[N + 1];
			visited = new boolean[N + 1];

			st = new StringTokenizer(br.readLine());

			for (int n = 1; n <= N; n++)
				arr[n] = Integer.parseInt(st.nextToken());

			for (int i = 1; i <= N; i++) {
				if (checked[i])
					continue;
				dfs(i);
			}

			System.out.println(N - result); // 전체 학생 수 - 팀에 속하는 학생 = 속하지 않는 학생들의 수
		}
	}
}