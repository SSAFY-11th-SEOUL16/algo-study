#include <vector>
#include <cstring>
#include <algorithm>

using namespace std;

const int SIZE = 201;

int solution(int n, int m, vector<vector<int>> edge_list, int k, vector<int> gps_log) {
	int answer = 0;
	vector<int> edge[SIZE];
	//int dp[k][n+1];
	//memset(dp, 2e8, sizeof(dp));
	vector<vector<int>> dp;
	dp.assign(k, vector<int>(n + 1, 2e9));
	for (int i = 0; i < edge_list.size(); i++) {
		edge[edge_list[i][0]].push_back(edge_list[i][1]);
		edge[edge_list[i][1]].push_back(edge_list[i][0]);
	}

	dp[0][gps_log[0]] = 0;
	for (int i = 1; i < k; i++) { //시간
		for (int j = 1; j <= n; j++) { //거점 번호
			dp[i][j] = min(dp[i][j], dp[i - 1][j] + (gps_log[i] == j ? 0 : 1));
			for (int d = 0; d < edge[j].size(); d++) { //인접한 노드
				int next = edge[j][d];
				dp[i][j] = min(dp[i][j], dp[i - 1][next] + (gps_log[i] == j ? 0 : 1));
			}
		}
	}

	int end = gps_log[k - 1];
	if (dp[k - 1][end] == 2e9) answer = -1;
	else answer = dp[k - 1][end];
	return answer;
}