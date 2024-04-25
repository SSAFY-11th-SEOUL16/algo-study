import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * java
 * 560 ms
 * 
 * DP
 * 
 * 게임의 각 지시에 대하여 최소 힘을 재귀적으로 계산
 * 		이미 계산된 값이 있는 경우 해당 값 return
 * 		없을 경우, 해당 지시를 수행한 후의 최소 힘 계산
 */

public class BOJ2342_Dance_Dance_Revolution {
	static ArrayList<Integer> turn = new ArrayList<>();
	static int[][][] dp;
	static int length;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		while (st.hasMoreTokens()) {
			int tmp = Integer.parseInt(st.nextToken());
			if (tmp == 0)
				break;
			turn.add(tmp);
		}
		length = turn.size();

		dp = new int[5][5][length];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		System.out.println(solve(0, 0, 0));
	}

	public static int solve(int left, int right, int cnt) {
		if (cnt == length) // 게임을 모두 수행한 경우
			return 0;
		if (dp[left][right][cnt] != -1) // 이미 계산된 값이 있는 경우
			return dp[left][right][cnt];

		int next = turn.get(cnt); // 현재 지시를 수행한 후의 최소 힘 계산
		dp[left][right][cnt] = Math.min(solve(next, right, cnt + 1) + energy(left, next),
				solve(left, next, cnt + 1) + energy(right, next));
		return dp[left][right][cnt];
	}

	public static int energy(int pos, int des) { // 발의 위치에 따라 드는 힘을 계산
		int diff = Math.abs(pos - des); // 현재 위치와 목표 위치의 차이
		if (pos == 0)
			return 2;
		else if (diff == 0)
			return 1;
		else if (diff == 1 || diff == 3)
			return 3;
		return 4;
	}
}