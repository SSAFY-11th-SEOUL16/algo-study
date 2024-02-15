#include <iostream>
#include <cstring>
using namespace std;

int N, M;
int L, R, mid;
int arr[300001];

//200ms
int main() {
	cin >> N >> M;
	memset(arr, 0, sizeof(arr));
	L = 1;
	R = 1;
	for (int i = 0; i < M; i++) {
		cin >> arr[i];
		if (R < arr[i]) R = arr[i];
	}

	int ans = 0;
	while (L <= R) {
		int cnt = 0;
		mid = (L + R) / 2;
		for (int i = 0; i < M; i++) {
			int curr = arr[i];
			cnt += curr / mid;
			if (curr % mid > 0) cnt++;

		}

		if (cnt <= N) {
			ans = mid;
			R = mid - 1;
		}

		
		if (cnt > N) {
			L = mid + 1;
		}
	}

	cout << ans;

	return 0;
}