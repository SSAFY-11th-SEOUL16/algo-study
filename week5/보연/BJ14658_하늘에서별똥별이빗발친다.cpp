#include <iostream>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;
typedef pair<int, int> ci;

//ci stars[101];
vector<ci> stars;
int dp[101];

bool cmp(ci a, ci b) {
	return a.first < b.first;
}

int N, M, L, K;
int main() {
	cin >> N >> M >> L >> K;
	memset(dp, 0, sizeof(dp));
	for (int i = 0; i < K; i++) {
		int x, y;
		cin >> x >> y;
		stars.push_back(make_pair(x, y));
	}
	sort(stars.begin(), stars.end(), cmp);

	int MAX = 0;
	for (int i = 0; i < K; i++) {
		for (int j = 0; j < K; j++) {
			int x = stars[i].first;
			int y = stars[j].second;

			//if (x + L <= stars[j].first) continue; -> 왜 넣으면 틀릴까

			int cnt = 0;
			for (int k = 0; k < K; k++) {
				int cx = stars[k].first;
				int cy = stars[k].second;
				if (x <= cx && cx <= x + L && y <= cy && cy <= y + L) cnt++;
			}
			if (cnt > MAX) MAX = cnt;
		}


	}
	cout << stars.size() - MAX;
	return 0;
}