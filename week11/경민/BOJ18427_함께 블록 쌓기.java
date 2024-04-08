import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());

		List<Integer>[] list = new ArrayList[n + 1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			list[i] = new ArrayList<Integer>();
			while (st.hasMoreTokens())
				list[i].add(Integer.parseInt(st.nextToken()));
		}

		int[][] dp = new int[n + 1][h + 1]; // dp[i][j]: i번 학생이 j 높이를 만들 수 있는 경우의 수 저장


		for (int i = 1; i <= n; i++) {
			// i 번 학생이 j 높이를 만들 수 있는지 
			for (int j = 1; j <= h; j++) {
				/*
				 * 1번. j 높이의 블록을 내가 가지고 있는지 → 가지고 있다면 + 1
				 * 2번. 이전 학생이 j - (내가 가진 블록의 높이)까지 만들 수 있는 경우의 수 
                 * 3번. 이전 학생이 j 높이까지 만들 수 있는 경우의 수 
				 */
				for(int block : list[i]) {
					if( j == block ) dp[i][j]++; // 1번
					if(j > block) 
						dp[i][j] += dp[i-1][j-block]; // 2번
				}
				dp[i][j] += dp[i-1][j]; // 3번
				dp[i][j] %= 10007;
			}
		}
		
		System.out.println(dp[n][h]);
	}
}