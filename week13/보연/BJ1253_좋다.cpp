#include <iostream>
#include <algorithm>

using namespace std;

const int SIZE = 2001;
int N, cnt;
int arr[SIZE];

/*

[좋다]

1. 두 수의 합으로 나타낼 수 있으면 좋은 수
2. 투포인터로 배열의 양쪽 끝에서 시작해서 같은지 판별
3. 만약 target보다 합이 더 크면 right를 감소하고 작으면 left를 증가시킨다.

*/

void input() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}
}

void solve() {
	sort(arr, arr + N);
	cnt = 0;
	for (int i = 0; i < N; i++) {
		int target = arr[i];
		int left = 0;
		if (i == 0) left = 1;
		int right = N - 1;

		while (left < right) {
			if (right == i) {
				right--;
				continue;
			}
			if (left == i) {
				left++;
				continue;
			}

			if (arr[left] + arr[right] == target) {
				cnt++;
				break;
			}

			if (arr[left] + arr[right] > target) {
				right--;
				continue;
			}

			if (arr[left] + arr[right] < target) {
				left++;
			}
		}

	}

	cout << cnt;
}

int main()
{
	input();
	solve();
	return 0;
}
