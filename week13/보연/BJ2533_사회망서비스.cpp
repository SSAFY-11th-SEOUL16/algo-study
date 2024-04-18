#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

/*
[사회망서비스]
#DFS #DP

1. dp[i][j] : i번 사람이 얼리어답터인지 아닌지(j=0, 1)일 때, 최소 얼리어답터의 수
2. i번 노드의 인접한 노드를 방문체크하면서 최소 얼리어답터 수를 구한다.
3. i번 노드가 얼리어답터라면, 인접한 노드는 얼리어답터거나 얼리어답터가 아닐 수 있으므로 두 경우 중 최솟값을 dp[i][1]에 더한다.
4. i번 노드가 얼리어답터가 아니라면, 인접한 노드는 반드시 얼리어답터여야 한다.
5. 임의의 정점 노드가 얼리어답터일때, 아닐때 중 최솟값을 정답에 출력

*/

const int SIZE = 1e6 + 1;
int N, ans;
vector<int> edgeList[SIZE];
bool visited[SIZE];
int dp[SIZE][2];

void input() {
	cin >> N;
	for (int i = 0; i < N - 1; i++) {
		int u, v;
		cin >> u >> v;
		edgeList[u].push_back(v);
		edgeList[v].push_back(u);
	}

}

void dfs(int curr) {
	visited[curr] = true;
	dp[curr][1] = 1;
	for (int i = 0; i < edgeList[curr].size(); i++) {
		int next = edgeList[curr][i];
		if (visited[next]) continue;
		dfs(next);
		dp[curr][0] += dp[next][1];
		dp[curr][1] += min(dp[next][0], dp[next][1]);
	}
}

void solve() {
	dfs(1);
	ans = min(dp[1][0], dp[1][1]);
	cout << ans;
}

int main() {
	//이 코드를 추가하면 1392ms -> 740ms로 줄어듦
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);
	input();
	solve();
	return 0;
}
