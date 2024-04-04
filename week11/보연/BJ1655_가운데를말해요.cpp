#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int N;
priority_queue<int> MAX;
priority_queue<int, vector<int>, greater<int>> MIN;

void solve() {
    cin >> N;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;
		if(MAX.size() == MIN.size()) {
		    MAX.push(n);
		} else {
		    MIN.push(n);
		}

		while (!MAX.empty() && !MIN.empty() && (MAX.top() > MIN.top())) {
			int maxV = MAX.top();
			int minV = MIN.top();
			MAX.pop();
			MIN.pop();
			MIN.push(maxV);
			MAX.push(minV);
		}

		cout << MAX.top() << '\n';
	}
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
	solve();
	return 0;
}
