#include <iostream>
#include <cstring>
using namespace std;

/*
[개똥벌레]
0. 2중 for문을 돌리면 20만x50만 으로 시간초과가 난다. for문을 1개만 써야함.
1. 아래에서 자라는 석순을 길이에 따라 저장한다.
2. 구간 i를 지날때 부수는 장애물 수를 누적합으로 저장한다.
3. 위에서 자라는 석순도 마찬가지로 저장한다.
4. 결과 배열에 구간 i를 지날때 부수는 장애물 수를 합하고 동시에 최솟값 갱신한다.
*/

const int SIZE = 500001;
int N, H;
int arr[SIZE];
int top[SIZE];
int bottom[SIZE];

void input() {
	cin >> N >> H;
	for (int i = 1; i <= N; i++) {
		int h;
		cin >> h;
		if (i % 2 == 1) {
			//홀수라면
			bottom[h]++;
		}
		else {
			//짝수라면
			top[H - h + 1]++;
		}
	}
}

void solve() {
	int MIN = 2e9;
	int cnt = 1;
	for (int i = H - 1; i >= 1; i--) {
		bottom[i] += bottom[i + 1];
	}


	for (int i = 1; i <= H; i++) {
		top[i] += top[i - 1];
		arr[i] = top[i] + bottom[i];
		if (MIN > arr[i]) {
			MIN = arr[i];
			cnt = 1;
			continue;
		}

		if (MIN == arr[i]) {
			cnt++;
		}
	}

	cout << MIN << " " << cnt;
}

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);
	input();
	solve();

	return 0;
}
