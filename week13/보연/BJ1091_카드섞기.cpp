#include <iostream>
#include <cstring>
#include <vector>
#include <set>

using namespace std;

/*
[카드 섞기]
#구현 #시뮬레이션

1. 문제를 잘 읽어야 한다. 특히 p[i]는 처음에 i번에 있던 카드가 p[i]번 플레이어에게 가야한다.
2. 즉, 섞은 카드를 card라 할 때, card[i]는 (i % 3)번 플레이어에게 가는 처음에 위치한 카드의 번호다.
3. p[card[i]] == i % 3 일 때, 시뮬레이션을 종료한다.

*/

const int SIZE = 49;
int N, ans;
int p[SIZE], s[SIZE];
int temp[SIZE];
vector<int> curr;
set<vector<int>> repo;

void input() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> p[i];
	}
	for (int i = 0; i < N; i++) {
		cin >> s[i];
	}

}

bool isSame() {
	for (int i = 0; i < N; i++) {
		//if (curr[i] % 3 != p[i]) return false;
		if (i % 3 != (p[curr[i]])) return false;
	}
	return true;
}

void printArr() {
	cout << ans << "번 : ";
	for (int i = 0; i < N; i++) {
		cout << curr[i] << " ";
	}
	cout << '\n';
}

void solve() {
	ans = 0;
	for (int i = 0; i < N; i++) {
		curr.push_back(i);
	}

	while (true) {
		if (isSame()) {
			break;
		}
		//카드 섞기
		for (int i = 0; i < N; i++) {
			temp[s[i]] = curr[i];
		}

		for (int i = 0; i < N; i++) {
			curr[i] = temp[i];
		}
		//printArr();
		if (repo.find(curr) != repo.end()) {
			ans = -1;
			break;
		}
		repo.insert(curr);
		ans++;
	}

	cout << ans;

}

int main()
{
	input();
	solve();
	return 0;
}
