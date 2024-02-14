#include <iostream>
#include <cstring>
#include <cmath>

using namespace std;

int n, ans;
int input[41]; //입력값
int num[41]; //input 숫자가 나온 빈도수

int main() {
	memset(input, 0, sizeof(input));
	memset(num, 0, sizeof(num));
	cin >> n; //동물의 수
	int idx = 0; //나올 수 있는 최댓값
	for (int i = 0; i < n; i++) {
		cin >> input[i];
		num[input[i]] ++;
		if (idx <= input[i]) idx = input[i] + 1;
	}
	bool flag = false;
	int prev = 2;
	int cnt_1 = 0;
	int cnt_2 = 0;
	for (int i = 0; i < idx; i++) {
		if (num[i] > prev) {
			flag = true;
			break;
		}
		if (num[i] == 2) cnt_2++;
		if (num[i] == 1) cnt_1++;
		prev = num[i];
	}

	if (flag) {
		cout << "0";
	}
	else {
		ans = pow(2, cnt_1 > 0 ? cnt_2 + 1 : cnt_2);
		cout << ans;
	}

	return 0;
}