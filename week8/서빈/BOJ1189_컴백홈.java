import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 84 ms
 * 출발 (R-1, 0) -> 도착 (0, C-1) : 이동 횟수가 K인 경로의 수
 * 
 * DFS 
 * 
 * visited 배열로 방문 여부 확인 
 * 이미 방문했거나 T인 경우 지나갈 수 없다!
 */

public class BOJ1189_컴백홈 {
	static int R, C, K;
	static char[][] map;
	static int[][] visited;
	static int answer;

	static int[] moveR = { 1, -1, 0, 0 };
	static int[] moveC = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new int[R][C];

		for (int i = 0; i < R; i++) {
			String tmp = br.readLine();
			map[i] = tmp.toCharArray();
		}

		visited[R - 1][0] = 1;
		dfs(R - 1, 0, 1); // DFS

		System.out.println(answer);
	}

	static void dfs(int r, int c, int moved) {
		if (r == 0 && c == C - 1) { // 도착 지점 도착
			if (moved == K) // K번 이동
				answer++;
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nextR = r + moveR[i];
			int nextC = c + moveC[i];
			if (nextR < 0 || nextR >= R || nextC < 0 || nextC >= C) // 범위를 벗어난 경우
				continue;
			if (visited[nextR][nextC] == 1 || map[nextR][nextC] == 'T') // 이미 방문했거나 T인 경우 지나갈 수 없다!
				continue;
			visited[nextR][nextC] = 1;
			dfs(nextR, nextC, moved + 1);
			visited[nextR][nextC] = 0;
		}
	}
}