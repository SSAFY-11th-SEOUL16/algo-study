#include <iostream>
#include <cstring>
#include <queue>
using namespace std;

/*
[뱀]

1. 매 초마다 뱀을 이동시킨다.
2. 뱀 이동 정보는 현재 뱀의 머리가 가리키는 방향으로 이동한다.
3. 다음 칸에 사과가 있다면 머리만 이동한다.
4. 사과가 없다면 머리도 이동하고 꼬리도 이동한다.
5. 꼬리를 이동할때는 이동 정보를 큐에서 관리한다. 
*/

typedef pair<int, int> ci;
typedef pair<int, char> cc;

struct Snake {
	ci head;
	ci tail;
	int len;
};

int dr[4] = { 0, 1, 0, -1 };
int dc[4] = { 1, 0, -1, 0 };

int N, K, L, ans;
int board[101][101];
bool visited[101][101];
queue<cc> que;
queue<ci> order;

void printBoard() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cout << visited[i][j] << ' ';
		}
		cout << '\n';
	}
}

void input() {
	cin >> N >> K;
	memset(board, 0, sizeof(board));
	memset(visited, 0, sizeof(visited));
	ans = 0;
	for (int i = 0; i < K; i++) {
		int x, y;
		cin >> x >> y;
		board[x-1][y-1] = 1;
	}
	cin >> L;
	for (int i = 0; i < L; i++) {
		int t;
		char d;
		cin >> t >> d;
		que.push({ t, d });
	}
}

void solve() {
	Snake s;
	s.head = make_pair(0, 0);
	s.tail = make_pair(0, 0);
	visited[0][0] = true;
	s.len = 0;
	int t = 0; //시간
	int d = 0; //오른쪽부터 시작

	int sec = que.front().first;
	char dir = que.front().second;
	while (true) {
	    t++;
		//뱀 이동
		int nr = s.head.first + dr[d];
		int nc = s.head.second + dc[d];

		if (!que.empty()) {
			sec = que.front().first;
			dir = que.front().second;
		}

		//벽이나 자기자신 몸과 부딪히면 끝
		if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
			ans = t;
			break;
		}

		if (visited[nr][nc]) {
			ans = t;
			break;
		}

		if (board[nr][nc] == 0) {
			//사과가 없다면
			s.head.first = nr;
			s.head.second = nc;
			visited[nr][nc] = true;
			order.push({ nr, nc });

			visited[s.tail.first][s.tail.second] = false;
			s.tail.first = order.front().first;
			s.tail.second = order.front().second;
			order.pop();

			
		}else {
			//사과가 있다면
			board[nr][nc] = 0;
			s.head.first = nr;
			s.head.second = nc;
			visited[nr][nc] = true;
			order.push({ nr, nc });
		}


		//sec 초 후 방향 전환
		if (t == sec) {
			if (dir == 'L') d = (d + 3) % 4;
			if (dir == 'D') d = (d + 1) % 4;

			que.pop();
		}

		//cout << t << "초 *** \n";
 		//printBoard();
		
	}
}

int main()
{
	input();
	solve();
	cout << ans;
	return 0;
}
