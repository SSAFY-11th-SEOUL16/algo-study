#include <iostream>
#include <cstring>
#include <algorithm>

using namespace std;

typedef long long ll;

struct Person {
	int p;
	int v;
};

const int SIZE = 100001;
int N, D;
Person person[SIZE];
ll ans;

bool cmp(Person p1, Person p2) {
	return p1.p < p2.p;
}

void input() {
	cin >> N >> D;
	for (int i = 0; i < N; i++) {
		int p, v;
		cin >> p >> v;
		person[i] = { p, v };
	}
	ans = 0;
}


void solve() {
	sort(person, person + N, cmp);
	ll sum = 0;

	int start = 0;
	int end = 1;
	sum += person[start].v;
	ans = sum;
	while (end < N) {
		if (person[end].p - person[start].p < D) {
			sum += person[end].v;
			end++;
			if (sum > ans) {
				ans = sum;
			}
		}
		else {
			sum -= person[start].v;
			start++;
		}

	}

	cout << ans;
}

int main() {
	input();
	solve();
	return 0;
}
