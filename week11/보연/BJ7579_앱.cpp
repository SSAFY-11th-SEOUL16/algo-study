#include <iostream>
#include <cstring>
#include <algorithm>
using namespace std;

struct App {
	int weight;
	int cost;
};

const int SIZE = 101;
int N, M;
App app[SIZE];
int dp[10001];

void input() {
	memset(app, 0, sizeof(app));
	memset(dp, 0, sizeof(dp));
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		cin >> app[i].weight;
	}
	for (int i = 0; i < N; i++) {
		cin >> app[i].cost;
	}
}

void solve() {
	for (int i = 0; i < N; i++) {
		for (int j = 10000; j >= 0; j--) {
		    if(j >= app[i].cost)
        		dp[j] = max(dp[j], dp[j - app[i].cost] + app[i].weight);
		}
	}
    int ans=10001;
    for(int i=0; i<=10000; i++) {
        if(dp[i] >= M) {
            ans = i;
            break;
        }
    }

	cout << ans;
}

int main()
{
	input();
	solve();
	return 0;
}
