import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static String[] guitars;
	static int[][] music;
	static int[] isPlayed;
	static int cnt = 0, min = Integer.MAX_VALUE, max = 0;

	/*
	 * max : 연주할 수 있는 곡의 최대
	 * min : max 곡을 연주하는 기타의 최소 개수
	 * cnt : 연주가능한 곡의 개수
	 */

	/*
	 * 연주하는 곡 표시
	 * 
	 *  연주 표시를 true/false로 값을 설정했지만 같은 곡을 연주하는 기타가 여러 개 존재 시 하나의 기타를 백트래킹하면 
	 *  다른 기타가 연주가능한 곡을 false로 바꿔버리는 이슈  => +1/-1 로 기타 연주 표현 
	 *  
	 *  이 후, 조건문에서 0 이상일 경우, 연주 가능한 곡임을 체크
	 */
	public static void play(int index, int flag) {
		for (int i = 0; i < M; i++) {
			if (music[index][i] == 1) {
				isPlayed[i] += flag;
			}
		}
	}

	/* 
	 * 1. max 보다 많은 곡을 연주하면 max 값 변경
	 * 2. max 와 같은 수의 곡을 연주할 시, 기타 개수가 더 작은 개수로 min 값 변경
	 */
	public static void check(int guitarCnt) {
		int cnt = 0;
		for (int i = 0; i < M; i++)
			if (isPlayed[i] > 0)
				cnt++;
		if (max < cnt) {
			max = cnt;
			min = guitarCnt;
			if (cnt > M)
				return;
		} else if (max == cnt) {
			min = Math.min(min, guitarCnt);

		}
	}

	/* 최소의 기타로 최대한 많은 곡을 연주 */
	public static void dfs(int guitarCnt, int start) {
		if (guitarCnt > N)
			return;
		if (start == N) {
			if (guitarCnt != 0)
				check(guitarCnt);
			return;
		}

		// 포함한다.
		play(start, 1);
		dfs(guitarCnt + 1, start + 1);
		// 포함하지 않는다.
		play(start, -1);
		dfs(guitarCnt, start + 1);

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		guitars = new String[N];
		music = new int[N][M]; // Y는 1 N 은 0
		isPlayed = new int[M];
		boolean flag = false;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			guitars[i] = st.nextToken();
			String str = st.nextToken();
			for (int j = 0; j < M; j++) {
				music[i][j] = (str.charAt(j) == 'Y' ? 1 : 0);
				if (!flag && music[i][j] == 1) {
					flag = true;
				}
			}
		}
		if (!flag) {
			System.out.println(-1);
			return;
		}
		dfs(0, 0);
		System.out.println(min);
	}
}