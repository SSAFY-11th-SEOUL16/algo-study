#include <iostream>
#include <cstring>
#include <queue>

using namespace std;

typedef pair<int, int> ci;

int N;
int ans, MAX;

int map[100][100];
int copy_map[100][100];
bool visited[100][100] = { 0, };
int dr[4] = { -1, 1, 0, 0 };
int dc[4] = { 0,0,-1,1 };


void copyMap() {
	memset(copy_map, 0, sizeof(copy_map));
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			copy_map[i][j] = map[i][j];
		}
	}
}

void waterDown(int h) {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (copy_map[i][j] < h) {
				copy_map[i][j] = 0;
				visited[i][j] = true;
			}
		}
	}
}

void bfs(int i, int j) {
	queue<ci> que;
	que.push(make_pair(i, j));
	visited[i][j] = true;

	while (!que.empty()) {
		ci curr = que.front();
		que.pop();

		for (int d = 0; d < 4; d++) {
			int nr = curr.first + dr[d];
			int nc = curr.second + dc[d];

			if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			if (visited[nr][nc]) continue;

			que.push(make_pair(nr, nc));
			visited[nr][nc] = true;
		}
	}
}

int countArea() {
	int cnt = 0;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (!visited[i][j] && copy_map[i][j] > 0) {
				//물에 잠기지 않은 지역
				bfs(i, j);
				cnt++;
			}
		}
	}

	return cnt;
}

void simulation(int H) {
	int cnt = 0;
	for (int i = 1; i <= H; i++) {
		copyMap();
		memset(visited, 0, sizeof(visited));
		//높이 i이하는 물에 잠김
		waterDown(i);
		//물에 잠기지 않은 영역 개수
		cnt = countArea();
		if (cnt > MAX) {
			MAX = cnt;
		}
	}
}

int main() {
	cin >> N;

	memset(map, 0, sizeof(map));
	ans = 0;
	MAX = 0;

	int H = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			int h;
			cin >> h;
			map[i][j] = h;
			if (H < h) {
				H = h;
			}
		}
	}

	simulation(H);

	cout << MAX;
	return 0;
}

// import java.util.LinkedList;
// import java.util.Queue;
// import java.util.Scanner;

// public class Main {
//     static class Pair {
//         int first, second;

//         Pair(int first, int second) {
//             this.first = first;
//             this.second = second;
//         }
//     }

//     static int N;
//     static int ans, MAX;
//     static int[][] map;
//     static int[][] copy_map;
//     static boolean[][] visited;
//     static int[] dr = {-1, 1, 0, 0};
//     static int[] dc = {0, 0, -1, 1};

//     static void copyMap() {
//         for (int i = 0; i < N; i++) {
//             System.arraycopy(map[i], 0, copy_map[i], 0, N);
//         }
//     }

//     static void waterDown(int h) {
//         for (int i = 0; i < N; i++) {
//             for (int j = 0; j < N; j++) {
//                 if (copy_map[i][j] < h) {
//                     copy_map[i][j] = 0;
//                     visited[i][j] = true;
//                 }
//             }
//         }
//     }

//     static void bfs(int i, int j) {
//         Queue<Pair> queue = new LinkedList<>();
//         queue.add(new Pair(i, j));
//         visited[i][j] = true;

//         while (!queue.isEmpty()) {
//             Pair curr = queue.poll();

//             for (int d = 0; d < 4; d++) {
//                 int nr = curr.first + dr[d];
//                 int nc = curr.second + dc[d];

//                 if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
//                 if (visited[nr][nc]) continue;

//                 queue.add(new Pair(nr, nc));
//                 visited[nr][nc] = true;
//             }
//         }
//     }

//     static int countArea() {
//         int cnt = 0;

//         for (int i = 0; i < N; i++) {
//             for (int j = 0; j < N; j++) {
//                 if (!visited[i][j] && copy_map[i][j] > 0) {
//                     bfs(i, j);
//                     cnt++;
//                 }
//             }
//         }

//         return cnt;
//     }

//     static void simulation(int H) {
//         int cnt;
//         for (int i = 1; i <= H; i++) {
//             copyMap();
//             for (boolean[] row : visited) {
//                 Arrays.fill(row, false);
//             }
//             waterDown(i);
//             cnt = countArea();
//             if (cnt > MAX) {
//                 MAX = cnt;
//             }
//         }
//     }

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         N = scanner.nextInt();

//         map = new int[N][N];
//         copy_map = new int[N][N];
//         visited = new boolean[N][N];

//         ans = 0;
//         MAX = 0;

//         int H = 0;
//         for (int i = 0; i < N; i++) {
//             for (int j = 0; j < N; j++) {
//                 int h = scanner.nextInt();
//                 map[i][j] = h;
//                 if (H < h) {
//                     H = h;
//                 }
//             }
//         }

//         simulation(H);

//         System.out.println(MAX);
//     }
// }
