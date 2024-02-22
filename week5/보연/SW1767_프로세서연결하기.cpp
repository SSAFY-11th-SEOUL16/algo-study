#include <iostream>
#include <cstring>
#include <vector>

using namespace std;

typedef pair<int, int> ci;

const int SIZE = 13;
int N, ans, MAX; //전선길이최솟값, 연결한코어최댓값
int map[SIZE][SIZE];
bool visited[SIZE][SIZE];
vector<ci> cores;

int dr[4] = { -1, 0, 1, 0 };
int dc[4] = { 0, 1, 0, -1 };

int calcLen(ci curr, int dir) {
	int res = 0;
	
	while (curr.first >= 0 && curr.second >= 0 && curr.first < N && curr.second < N) {
		if (map[curr.first][curr.second] != 0) return -1; //전원연결불가

		curr.first += dr[dir];
		curr.second += dc[dir];
		map[curr.first][curr.second] = 2; //전선
		res++;
	}

	return res;
}

void rollback(ci curr, int dir) {
	while (curr.first >= 0 && curr.second >= 0 && curr.first < N && curr.second < N) {
		map[curr.first][curr.second] = 0; //전선
		curr.first += dr[dir];
		curr.second += dr[dir];
	}
	return;
}

void dfs(int idx, int len, int cnt) { //len: 현재까지사용한전선길이, cnt: 연결성공한코어수
	if (cnt > MAX) {
		MAX = cnt;
		ans = len;
	}
	else if (cnt == MAX) { //코어 개수가 같다면 전선길이 최솟값
		if (ans > len) ans = len;
	}

	if (idx == cores.size()) return;

	for (int i = 0; i < 4; i++) { //상하좌우 탐색
		ci curr = cores[i];
		int nr = curr.first + dr[i];
		int nc = curr.second + dc[i];

		int wire = calcLen(curr, i);
		if (wire == -1) continue; //이 방향으로 전선 불가
		dfs(idx + 1, len + wire, cnt + 1);
		rollback(curr, i); //초기화
	}
	dfs(idx + 1, len, cnt); //연결 가능한 전선이 없음
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	int T;
	cin >> T;
	for (int tc = 1; tc <= T; tc++) {
		cin >> N;
		memset(map, 0, sizeof(map));
		memset(visited, 0, sizeof(visited));
		cores.clear();
		ans = 0;
		MAX = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cin >> map[i][j];
				if (map[i][j] == 1) cores.push_back({ i, j });
			}
		}

		dfs(0, 0, 0);

		cout << "#" << tc << " " << ans << '\n';
	}

	return 0;
}