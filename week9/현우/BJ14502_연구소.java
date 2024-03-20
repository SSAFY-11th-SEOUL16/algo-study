import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ14502_연구소 {
	
	/**
	 * 136 ms
	 * 연구소 주변에 벽 두르기
	 * 1 차원 배열로 변환
	 * 3중 for문으로 벽 3개 세우기
	 * 바이러스부터 DFS로 확산 영역 카운트
	 * 초기 안전 영역 개수에서 차감
	 */
	
	private static final int EMPTY = -1;
	private static final int WALL = 0; // 벽을 0으로 두르기 위해 1씩 뺌
	private static final int VIRUS = 1;
	
	private static int n, m, size, max, cnt, ans;
	private static int[] map;
	private static boolean[] source, visited;
	private static ArrayList<Integer> viruses;
	
	private static void dfs(int pos) {
		if (visited[pos] || map[pos] == WALL) {
			return;
		}
		visited[pos] = true;
		cnt++; // 확산 영역 카운트
		dfs(pos - size); // 1 차원 4방 탐색
		dfs(pos + size);
		dfs(pos - 1);
		dfs(pos + 1);
	}
	
	private static int infect() {
		cnt = 0;
		System.arraycopy(source, 0, visited, 0, max); // visited 초기화
		for (int virus : viruses) { // 바이러스 위치부터 DFS
			dfs(virus);
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int sum, pos, range, i, j, k;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		size = m + 2;
		max = (n + 2) * (m + 2); // 벽으로 둘러싸기 위해 + 2, + 2
		sum = 0;
		map = new int[max]; // 1 차원 배열로 변환
		viruses = new ArrayList<>();
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 1; j <= m; j++) {
				map[pos = i * size + j] = Integer.parseInt(st.nextToken()) - 1;  // 벽을 0으로 두르기 위해 1씩 뺌
				if (map[pos] == EMPTY) {
					sum++; // 초기 안전 영역
				} else if (map[pos] == VIRUS) {
					sum++;
					viruses.add(pos); // 바이러스 위치 저장
				}
			}
		}
		range = max - size - 1;
		source = new boolean[max];
		visited = new boolean[max];
		ans = 0;
		sum -= 3; // 안전 영역 벽 3개만큼 차감
		for (i = size + 1; i < range; i++) { // 첫 번째 벽 위치
			if (map[i] != EMPTY) { // 비어있지 않으면
				continue;
			}
			map[i] = WALL; // 벽 세우기
			for (j = i + 1; j < range; j++) { // 두 번째 벽 위치
				if (map[j] != EMPTY) { // 비어있지 않으면
					continue;
				}
				map[j] = WALL; // 벽 세우기
				for (k = j + 1; k < range; k++) { // 세 번째 벽 위치
					if (map[k] != EMPTY) { // 비어있지 않으면
						continue;
					}
					map[k] = WALL; // 벽 세우기
					ans = Math.max(ans, sum - infect()); // 안전 영역 카운트
					map[k] = EMPTY; // 원상 복구
				}
				map[j] = EMPTY; // 원상 복구
			}
			map[i] = EMPTY; // 원상 복구
		}
		System.out.print(ans);
	}
}
