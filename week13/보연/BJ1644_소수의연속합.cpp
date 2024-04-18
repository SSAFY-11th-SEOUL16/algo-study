#include <iostream>
#include <cstring>
#include <vector>

using namespace std;

/*
[소수의 연속합]

1. 연속합에서 슬라이딩 윈도우를 떠올릴 수 있다.
2. 소수를 에라토스테네스의 체를 이용해 구한다음, 소수 배열에 추가한다.
3. 이 소수 배열을 가지고 슬라이딩 윈도우로 합을 구할 수 있다면 카운트를 증가 시킨다.
4. 합과 N이 같다면 끝내는 것이 아니라 슬라이딩 윈도우 범위를 늘려서 탐색을 계속한다.

*/

const int SIZE = 4e6 + 1;
int N, cnt;
bool is_prime[SIZE];
vector<int> prime;

void input() {
	cin >> N;
}

void getPrime() {
	memset(is_prime, 1, sizeof(is_prime));
	is_prime[0] = is_prime[1] = false;
	for (int i = 2; i * i <= N; i++) {
		if (!is_prime[i]) continue;
		for (int j = i * i; j <= N; j += i) {
			is_prime[j] = false;
		}
	}

	for (int i = 2; i <= N; i++) {
		if (!is_prime[i]) continue;
		//cout << i << '\n';
		prime.push_back(i);
	}
}

void solve() {
	getPrime();
	int target = N;
	int left = 0;
	int right = 0;
	int sum = 0;
	cnt = 0;
	while (true) {
		if (target < sum) {
			if (left > right) break;
			sum -= prime[left++];
			continue;
		}

		if (target > sum) {
			if (right >= prime.size()) break;
			sum += prime[right++];
			continue;
		}
		if (target == sum) {
			//cout << target << " " << left << " + "<< right <<'\n';
			cnt++;
			if (right >= prime.size()) break;
			sum += prime[right++];
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
