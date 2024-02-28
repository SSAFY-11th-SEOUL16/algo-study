import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW2112_보호필름 {
	
	/**
	 * 207 ms
	 * 약품 투입 막 생성
	 * 일반 막 약품 투입 막으로 교체하면서 DFS 백트래킹
	 * 위쪽 특성과 비교하면서 연속 특성 성능검사
	 * 성능검사 통과 시 최소값 업데이트
	 * 현재 최소값보다 많은 약품 투입 막 필요 시 백트래킹
	 */
	
	private static final int B = 1;
	private static final int MAX_D = 13;
	private static final int MAX_W = 20;
	private static final int[] CHEM_A = new int[MAX_W]; // A 약품 투입 막
	private static final int[] CHEM_B = new int[MAX_W]; // B 약품 투입 막
	
	private static int d, w, k, min;
	private static int[][] films, copy;
	
	private static boolean test() { // 성능검사
		int temp, i, j;
		
		loop:
		for (i = 0; i < w; i++) { // 세로줄 하나씩 검사
			temp = 1; // 같은 특성 연속 개수
			for (j = 1; j < d; j++) {
				if (films[j][i] == films[j - 1][i]) { // 위쪽 특성과 같음
					if (++temp == k) { // 같은 특성 연속 K 개 도달
						continue loop; // 다음 줄 탐색
					}
				} else { // 위쪽 특성과 다름
					if (d - j < k) { // 남은 칸 수가 K 미만
						return false; // 성능검사 불합격
					}
					temp = 1; // 연속 개수 1로 초기화
				}
			}
		}
		return true; // 모든 줄 합격
	}
	
	private static void dfs(int start, int cnt) {
		int i;
		
		if (cnt >= min) { // 현재 최소값 이상의 약품 투입 막 사용
			return; // 백트래킹
		} else if (test()) { // 성능검사 합격 시
			min = cnt; // 최소값 업데이트
			return; // 백트래킹
		} else if (cnt >= min - 1) { // 추가적인 막 교체 시 현재 최소값 이상
			return; // 백트래킹
		}
		for (i = start; i < d; i++) {
			if (films[i] != CHEM_A && films[i] != CHEM_B) { // 약품 투입 막 X
				films[i] = CHEM_A; // A 약품 투입 막으로 교체
				dfs(i + 1, cnt + 1); // 탐색
				films[i] = copy[i]; // 원상 복구
			}
		}
		for (i = start; i < d; i++) {
			if (films[i] != CHEM_A && films[i] != CHEM_B) { // 약품 투입 막 X
				films[i] = CHEM_B; // B 약품 투입 막으로 교체
				dfs(i + 1, cnt + 1); // 탐색
				films[i] = copy[i]; // 원상 복구
			}
		}
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int i, j;
		
		st = new StringTokenizer(br.readLine());
		d = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		for (i = 0; i < d; i++) { // 초기 보호 필름 입력
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < w; j++) {
				films[i][j] = Integer.parseInt(st.nextToken());
			}
			copy[i] = films[i]; // 한 막씩 얕은 복사
		}
		if (k == 1) { // K == 1이면 약품 필요 X
			return 0;
		}
		min = k; // 최대 K 개의 약품 투입 막 필요
		dfs(0, 0); // DFS 탐색
		return min; // 약품 투입 막 최소 개수
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		films = new int[MAX_D][MAX_W];
		copy = new int[MAX_D][];
		Arrays.fill(CHEM_B, B);
		t = Integer.parseInt(br.readLine());
		for (testCase = 1; testCase <= t; testCase++) {
			sb.append('#').append(testCase).append(' ').append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}