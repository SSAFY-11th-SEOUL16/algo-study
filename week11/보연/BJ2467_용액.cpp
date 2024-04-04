#include <iostream>
#include <cstring>
#include <cmath>
using namespace std;

typedef long long ll;
typedef pair<ll, ll> cll;

const int SIZE = 100001;
int N, K;
ll num[SIZE];
cll ans;

void input() {
	memset(num, 0, sizeof(num));
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> num[i];
	}
}

void solve() {
	int left = 0;
	int right = N - 1;
	ll MIN = 2000000001;
	while (left < right) {
		ll curr = num[left] + num[right];
		if (MIN > abs(curr) || curr == 0) {
			MIN = abs(curr);
			ans = make_pair(num[left], num[right]);
		}
		if (curr < 0) {
			left++;
		}
		else {
			right--;
		}
	}

	cout << ans.first << " " << ans.second;

}

int main() {
	input();
	solve();

	return 0;
}
