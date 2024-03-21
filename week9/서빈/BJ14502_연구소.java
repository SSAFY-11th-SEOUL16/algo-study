import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * java
 * 788 ms
 * dfs, bfs
 * 
 * 연구소 내 바이러스 확산을 최소화하기 위한 벽을 세우는 방법 
 * 
 * DFS는 가능한 모든 벽의 배치를 탐색
 * 각 경우에 대해 BFS를 사용하여 바이러스가 퍼진 후의 상태를 시뮬레이션하며
 * 안전 영역의 최대 크기를 계산
 */

public class BJ14502_연구소 {
	static int N, M;
	static int[][] map, copyMap; // 확산 시뮬레이션을 위한 복사본 지도
	static int[] dx = { 1, -1, 0, 0 }; // 바이러스는 상화좌우로 퍼질 수 있음
	static int[] dy = { 0, 0, 1, -1 };

	static Queue<Virus> queue = new LinkedList<>(); // 바이러스가 있는 위치 저장 & BFS에 사용
	static int maxSafeRoom = 0; // 안전 영역의 최대 크기

	static class Virus {
		int x;
		int y;

		public Virus(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0);
		System.out.println(maxSafeRoom);
	}

	public static void dfs(int cnt) { // 벽을 세울 수 있는 모든 경우를 탐색
		if (cnt == 3) { // 3개의 벽을 모두 세웠다면
			bfs();
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) { // 연구소 지도 map 상의 모든 빈 칸 0을 찾아 벽을 세움
					map[i][j] = 1;
					dfs(cnt + 1);
					map[i][j] = 0; // 원상복구
				}
			}
		}
	}

	public static void bfs() { // 바이러스의 확산을 시뮬레이션
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 2) {
					queue.offer(new Virus(i, j)); // 바이러스가 있는 위치를 큐에 추가
				}
			}
		}

		copyMap = new int[N][M]; // 지도의 상태 복사

		for (int i = 0; i < N; i++) {
			copyMap[i] = map[i].clone();
		}

		while (!queue.isEmpty()) {
			Virus v = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = v.x + dx[i];
				int ny = v.y + dy[i];

				if (0 <= nx && nx < N && 0 <= ny && ny < M) {
					if (copyMap[nx][ny] == 0) {
						copyMap[nx][ny] = 2;
						queue.add(new Virus(nx, ny));
					}
				}
			}
		}
		findSafeZone(copyMap);
	}

	private static void findSafeZone(int[][] copyMap) { // 안전 영역의 크기를 계산
		int safeZone = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copyMap[i][j] == 0) {
					safeZone++;
				}
			}
		}
		maxSafeRoom = Math.max(safeZone, maxSafeRoom); // 최대값보다 크다면 업데이트
	}
}