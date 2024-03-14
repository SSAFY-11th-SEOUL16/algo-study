import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1189_컴백홈 {
	
	/**
	 * 80 ms
	 * 방문 체크 하면서 DFS 4방 탐색
	 * K번으로 도착지점 도달 시
	 * 정답 카운트
	 */
	
	private static final char ROAD = '.';
	private static final int[] dx = {-1, 0, 1, 0};
	private static final int[] dy = {0, 1, 0, -1};
	
	private static int r, c, k, ans;
	private static boolean[][] isRoad;
	
	private static void dfs(int x, int y, int depth) {
		int i, nx, ny;
		
		if (depth == k) { // K번 이동
			if (x == 1 && y == c) {
				ans++; // 도착 지점이면 카운트
			}
			return;
		} else if (x == 1 && y == c) {
			return; // K번 전에 도착해버린 경우
		}
		
		for (i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if (isRoad[nx][ny]) { // 도로이면
				isRoad[nx][ny] = false; // 방문한 곳 도로 없애기
				dfs(nx, ny, depth + 1);
				isRoad[nx][ny] = true; // 원상복구
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int i, j;
		char[] str;
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		isRoad = new boolean[r + 2][c + 2];
		for (i = 1; i <= r; i++) {
			str = br.readLine().toCharArray();
			for (j = 1; j <= c; j++) {
				isRoad[i][j] = (str[j - 1] == ROAD); // 도로 저장
			}
		}
		ans = 0;
		isRoad[r][1] = false; // 방문한 곳 도로 없애기
		dfs(r, 1, 1); // DFS
		System.out.print(ans);
	}
}
