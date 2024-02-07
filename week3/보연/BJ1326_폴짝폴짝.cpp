#include <iostream>
#include <cstring>
#include <queue>

using namespace std;

int N, A, B, ans;
int arr[10001]; //징검다리
//int dp[10001]; //최소로 갈 수 있는 거리
bool visited[10001]; //방문 배열 -> BFS 자체가 최단거리??
int order[10001];

void simulation() {
	queue<int> que;
	que.push(A);
	visited[A] = true;

	while (!que.empty()) {
		int curr = que.front();
		que.pop();

		if (curr == B) {
			//목적지에 도달하면 종료
			return;
		}

		//앞으로 점프
		for (int i = curr + arr[curr]; i <= N; i += arr[curr]) {
			if (visited[i]) continue;
			visited[i] = true;
			order[i] = order[curr] + 1;
			que.push(i);
		}

		//뒤로 점프
		for (int i = curr - arr[curr]; i >= 1; i -= arr[curr]) {
			if (visited[i]) continue;
			visited[i] = true;
			order[i] = order[curr] + 1;
			que.push(i);
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);
	memset(arr, 0, sizeof(arr));
	memset(visited, 0, sizeof(visited));
	memset(order, 0, sizeof(order));
	ans = 0;
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}

	cin >> A >> B;
	if (A == B) { //같은 경우 점프 안해도 됨
		cout << "0";
		return 0;
	}
	simulation();

	if (order[B] == 0) ans = -1;
	else {
		ans = order[B];
	}
	cout << ans;
}

//import java.util.*;
//
//public class Main {
//	static int N, A, B, ans;
//	static int[] arr; // 징검다리
//	static boolean[] visited; // 방문 배열 -> BFS 자체가 최단거리??
//	static int[] order;
//
//	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//		N = scanner.nextInt();
//		arr = new int[N + 1];
//		visited = new boolean[N + 1];
//		order = new int[N + 1];
//
//		for (int i = 1; i <= N; i++) {
//			arr[i] = scanner.nextInt();
//		}
//
//		A = scanner.nextInt();
//		B = scanner.nextInt();
//
//		if (A == B) { // 같은 경우 점프 안해도 됨
//			System.out.println("0");
//			return;
//		}
//
//		simulation();
//
//		if (order[B] == 0) ans = -1;
//		else {
//			ans = order[B];
//		}
//		System.out.println(ans);
//	}
//
//	static void simulation() {
//		Queue<Integer> que = new LinkedList<>();
//		que.offer(A);
//		visited[A] = true;
//
//		while (!que.isEmpty()) {
//			int curr = que.poll();
//
//			if (curr == B) {
//				// 목적지에 도달하면 종료
//				return;
//			}
//
//			// 앞으로 점프
//			for (int i = curr + arr[curr]; i <= N; i += arr[curr]) {
//				if (visited[i]) continue;
//				visited[i] = true;
//				order[i] = order[curr] + 1;
//				que.offer(i);
//			}
//
//			// 뒤로 점프
//			for (int i = curr - arr[curr]; i >= 1; i -= arr[curr]) {
//				if (visited[i]) continue;
//				visited[i] = true;
//				order[i] = order[curr] + 1;
//				que.offer(i);
//			}
//		}
//	}
//}
