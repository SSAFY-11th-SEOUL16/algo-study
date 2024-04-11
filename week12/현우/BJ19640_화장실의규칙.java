import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ19640_화장실의규칙 {
	
	/**
	 * 328 ms
	 * 구현
	 * 데카의 줄에서 데카와 데카 앞의 사원들 중
	 * 가장 낮은 min 사원 구하기
	 * 1. min 사원이 나가야 데카가 이용 가능
	 * 2. min 사원이 나가면 이후로는
	 * 	데카의 줄에서만 연속으로 빠져나감
	 * 다른 줄 각각에서 맨 앞 부터 탐색하면서
	 * min 사원보다 낮은 사원이 나올 때까지 카운트
	 * 데카의 줄에 데카 앞에 있는 사원 수 더해서 출력
	 */
	
	private static final int[] EMPTY = {-1, -1}; // 빈 공간
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, k, cnt, size, row, col, ans, i, j;
		int[] min;
		int[][] line;
		int[][][] map;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		size = (n - 1) / m + 1;
		map = new int[m][size][];
		cnt = 0;
		for (i = 0; i < size; i++) {
			for (j = 0; j < m; j++) {
				if (cnt++ < n) { // N 명의 사원 입력
					st = new StringTokenizer(br.readLine());
					map[j][i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
				} else { // N 명 초과
					map[j][i] = EMPTY; // 빈 공간은 가장 낮은 사원으로 만듬
				}
			}
		}
		row = k % m; // 데카의 줄
		col = k / m; // 데카가 서있는 위치
		line = map[row];
		min = line[0];
		for (i = 1; i <= col; i++) { // 앞에서부터 데카까지 탐색
			if (line[i][0] < min[0] || (line[i][0] == min[0] && line[i][1] < min[1])) {
				min = line[i]; // 데카의 줄에서 데카와 데카 앞의 사원들 중 가장 낮은 사원
			}
		}
		ans = 0;
		for (i = 0; i < row; i++) { // 데카보다 줄 번호가 작은 줄들
			line = map[i];
			for (j = 0; j < size; j++) {
				if (line[j][0] < min[0] || (line[j][0] == min[0] && line[j][1] < min[1])) {
					break; // min 보다 낮은 사원 발견
				}
			}
			ans += j; // 해당 사원 앞의 사원 수만큼 카운트
		}
		for (++i; i < m; i++) { // 데카보다 줄 번호가 큰 줄들
			line = map[i];
			for (j = 0; j < size; j++) {
				if (line[j][0] < min[0] || (line[j][0] == min[0] && line[j][1] <= min[1])) {
					break; // min 보다 낮거나 같은 사원 발견
				}
			}
			ans += j; // 해당 사원 앞의 사원 수만큼 카운트
		}
		System.out.print(ans + col); // 데카의 줄에 데카 앞에 있는 사원 수 더해서 출력
	}
}
