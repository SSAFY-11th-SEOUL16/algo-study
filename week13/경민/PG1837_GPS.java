import java.util.*;

class Solution {
	public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {

		int dp[][] = new int[k][n + 1]; // i시간대 거점에 j거점이 왔을때, 다른 최소 횟수
		List<Integer>[] list = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
			list[i].add(i); // 이동하지않고 머무를 수도 있으므로 본인에게 돌아가는 간선 추가
		}

		for (int i = 0; i < m; i++) {
			list[edge_list[i][0]].add(edge_list[i][1]);
			list[edge_list[i][1]].add(edge_list[i][0]);
		}

		for (int i = 0; i < k; i++) {
			for (int j = 1; j <= n; j++) {
				dp[i][j] = 101; // 최소를 구해야하므로 k의 최대값(100)보다 큰 101로 초기화
			}
		}

		dp[0][gps_log[0]] = 0; // 시작점은 고정되있으므로 0으로 초기화

		for (int i = 0; i < k - 1; i++) {
			for (int j = 1; j <= n; j++) {
				if (dp[i][j] == 101)
					continue;
				for (int h = 0; h < list[j].size(); h++) {
					int next = list[j].get(h);
					int error = 0;
					if (gps_log[i + 1] != next)	error = 1; // 경로가 존재하지 않으면 1
					dp[i + 1][next] = Math.min(dp[i + 1][next], dp[i][j] + error);
				}
			}
		}

		int answer = dp[k - 1][gps_log[k - 1]];
		return answer == 101 ? -1 : answer;
	}
}