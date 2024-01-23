#include <iostream>
#include <cstring>
#include <queue>

using namespace std;

const int MAX_SIZE = 100001;

int simulation(int N, int K) {
	int visited[MAX_SIZE];
	for (int i = 0; i < MAX_SIZE; i++) {
		visited[i] = 2e9;
	}
	queue<int> que;
	que.push(N);
	visited[N] = 0;

	while (!que.empty()) {
		int curr = que.front();
		que.pop();

		if (curr == K) return visited[K];

		// 거리 + 1
		if (curr + 1 < MAX_SIZE && visited[curr + 1] > visited[curr] + 1) {
			visited[curr + 1] = visited[curr] + 1;
			que.push(curr + 1);
		}
		// 거리 - 1
		if (curr - 1 >= 0 && visited[curr - 1] > visited[curr] + 1) {
			visited[curr - 1] = visited[curr] + 1;
			que.push(curr - 1);
		}

		// 순간이동 *2
		if (curr * 2 < MAX_SIZE && visited[curr * 2] > visited[curr]) {
			visited[curr * 2] = visited[curr];
			que.push(curr * 2);
		}
	}
}

int main(){
	ios::sync_with_stdio(false);
	cin.tie(0);
	int N, K;
	//cin >> N >> K;
	N = 5, K = 17;

	int ans = 0;

	cout << simulation(N, K);

	return 0;
}

// import java.util.Arrays;
// import java.util.LinkedList;
// import java.util.Queue;

// public class Main {
//     static final int MAX_SIZE = 100001;

//     static int simulation(int N, int K) {
//         int[] visited = new int[MAX_SIZE];
//         Arrays.fill(visited, 2_000_000_000);
//         Queue<Integer> queue = new LinkedList<>();
//         queue.add(N);
//         visited[N] = 0;

//         while (!queue.isEmpty()) {
//             int curr = queue.poll();

//             if (curr == K) return visited[K];

//             // 거리 + 1
//             if (curr + 1 < MAX_SIZE && visited[curr + 1] > visited[curr] + 1) {
//                 visited[curr + 1] = visited[curr] + 1;
//                 queue.add(curr + 1);
//             }
//             // 거리 - 1
//             if (curr - 1 >= 0 && visited[curr - 1] > visited[curr] + 1) {
//                 visited[curr - 1] = visited[curr] + 1;
//                 queue.add(curr - 1);
//             }

//             // 순간이동 *2
//             if (curr * 2 < MAX_SIZE && visited[curr * 2] > visited[curr]) {
//                 visited[curr * 2] = visited[curr];
//                 queue.add(curr * 2);
//             }
//         }
//         return -1; // 도달할 수 없는 경우
//     }

//     public static void main(String[] args) {
//         int N = 5, K = 17;
//         int ans = simulation(N, K);
//         System.out.println(ans);
//     }
// }

