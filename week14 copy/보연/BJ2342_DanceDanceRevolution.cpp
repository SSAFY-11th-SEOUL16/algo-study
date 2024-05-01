#include <iostream>
#include <vector>
#include <cstring>
#include <algorithm>

using namespace std;

const int SIZE = 100001;
int N;
vector<int> vec;
int dp[SIZE][5][5];

void input() {
	int t;
	cin >> t;
	while (t != 0) {
		vec.push_back(t);
		cin >> t;
	}
    N = vec.size();
}

int calcStr(int prev, int curr) {
	if (prev == curr) return 1;
	if (prev == 0 && curr > 0) return 2;
	if (abs(prev - curr) == 1 || abs(prev - curr) == 3) return 3;
	if (abs(prev - curr) == 2) return 4;
}

int dfs(int idx, int left, int right) {
    if(idx == N) return 0;
    if(dp[idx][left][right] != -1) return dp[idx][left][right];
    int l = dfs(idx + 1, vec[idx], right) + calcStr(left, vec[idx]);
    int r = dfs(idx + 1, left, vec[idx]) + calcStr(right, vec[idx]);
    return dp[idx][left][right] = min(l, r);
}

void solve() {
    memset(dp, -1, sizeof(dp));
    cout << dfs(0, 0, 0);
}

int main() {
	input();
	if (N == 0) {
		cout << 0;
	}
	else
		solve();
	return 0;
}
