#include <iostream>
#include <cstring>
#include <set>
#include <algorithm>
using namespace std;


int N, M;
int arr[10];
int height[10];
int visited[10];
set<int> ans;
bool prime[9001];

void permutation(int cnt) {
	if (cnt == M) {
		int sum = 0;
		for (int i = 0; i < M; i++) {
			sum += height[arr[i]];
		}

		if (prime[sum]) {
			ans.insert(sum);
		}

		return;
	}

	for (int i = 0; i < N; i++) {
		if (visited[i]) continue;
		visited[i] = true;
		arr[cnt] = i;
		permutation(cnt + 1);
		visited[i] = false;
	}
}

void getPrime() {
	prime[0] = prime[1] = 0;
	for (int i = 2; i * i < 9001; i++) {
		if (prime[i]) {
			for (int j = i * i; j < 9001; j += i) {
				prime[j] = 0;
			}
		}
	}
}

int main()
{
	cin >> N >> M;

	memset(height, 0, sizeof(height));
	memset(arr, 0, sizeof(arr));
	memset(prime, 1, sizeof(prime));

	for (int i = 0; i < N; i++) {
		cin >> height[i];
	}

	//소수 배열 구하기
	getPrime();
	permutation(0);

//	if (ans.size() == 0) {
	if (ans.empty()) {
		cout << "-1";
		return 0;
	}

	for(set<int>::iterator iter = ans.begin(); iter != ans.end(); iter++){
//	for (auto x : ans) {
//		cout << x << ' ';
		cout << *iter << ' ';
	}

	return 0;
}

//import java.util.HashSet;
//import java.util.Set;
//
//public class Main {
//	static int N, M;
//	static int[] arr = new int[10];
//	static int[] height = new int[10];
//	static boolean[] visited = new boolean[10];
//	static Set<Integer> ans = new HashSet<>();
//	static boolean[] prime = new boolean[9001];
//
//	static void permutation(int cnt) {
//		if (cnt == M) {
//			int sum = 0;
//			for (int i = 0; i < M; i++) {
//				sum += height[arr[i]];
//			}
//
//			if (prime[sum]) {
//				ans.add(sum);
//			}
//
//			return;
//		}
//
//		for (int i = 0; i < N; i++) {
//			if (visited[i]) continue;
//			visited[i] = true;
//			arr[cnt] = i;
//			permutation(cnt + 1);
//			visited[i] = false;
//		}
//	}
//
//	static void getPrime() {
//		prime[0] = prime[1] = false;
//		for (int i = 2; i * i < 9001; i++) {
//			if (prime[i]) {
//				for (int j = i * i; j < 9001; j += i) {
//					prime[j] = false;
//				}
//			}
//		}
//	}
//
//	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//
//		N = scanner.nextInt();
//		M = scanner.nextInt();
//
//		for (int i = 0; i < N; i++) {
//			height[i] = scanner.nextInt();
//		}
//
//		// 소수 배열 구하기
//		Arrays.fill(prime, true);
//		getPrime();
//		permutation(0);
//
//		if (ans.isEmpty()) {
//			System.out.println("-1");
//			return;
//		}
//
//		for (int x : ans) {
//			System.out.print(x + " ");
//		}
//	}
//}
