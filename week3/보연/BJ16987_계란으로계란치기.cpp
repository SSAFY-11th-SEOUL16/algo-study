#include <iostream>
#include <cstring>
using namespace std;

struct Egg {
	int d; //내구도
	int w; //무게
};

int N, ans;
Egg eggs[9];

void simulation(int idx) {
	if (idx >= N - 1) {
		//가장 오른쪽에 있는 계란을 들면 종료
		
		return;
	}

	if (eggs[idx].d <= 0) {
		//손에 든 계란이 깨졌다면 다음 계란으로
		simulation(idx + 1);
		return;
	}
	//Egg curr = eggs[idx];
	for (int i = 0; i < N; i++) {
		
		//나머지 계란에 대해서 모든 경우의 수 탐색	
		//Egg other = eggs[i]; //깨트릴 계란
		//자기 자신이거나 이미 깨진 계란이면 패스
		if (idx == i || eggs[i].d <= 0) continue;
		//깨트리기
		eggs[idx].d -= eggs[i].w;
		eggs[i].d -= eggs[idx].w;
		simulation(idx + 1); //다음 순서
		eggs[idx].d += eggs[i].w;
		eggs[i].d += eggs[idx].w;

	}

	//깨진 계란 개수 세기
	int cnt = 0;
	for (int i = 0; i < N; i++) {
		if (eggs[i].d <= 0) cnt++;
	}

	if (ans < cnt) {
		ans = cnt;
	}
}

int main() {
	cin >> N;
	memset(eggs, 0, sizeof(eggs));
	ans = 0;
	for (int i = 0; i < N; i++) {
		cin >> eggs[i].d >> eggs[i].w;
	}

	simulation(0);
	cout << ans;

	return 0;
}
//
//import java.util.Scanner;
//
//public class Main {
//	static class Egg {
//		int d; // 내구도
//		int w; // 무게
//
//		public Egg(int d, int w) {
//			this.d = d;
//			this.w = w;
//		}
//	}
//
//	static int N, ans;
//	static Egg[] eggs;
//
//	static void simulation(int idx) {
//		if (idx >= N - 1) {
//			// 가장 오른쪽에 있는 계란을 들면 종료
//			return;
//		}
//
//		if (eggs[idx].d <= 0) {
//			// 손에 든 계란이 깨졌다면 다음 계란으로
//			simulation(idx + 1);
//			return;
//		}
//
//		for (int i = 0; i < N; i++) {
//			// 나머지 계란에 대해서 모든 경우의 수 탐색
//			if (idx == i || eggs[i].d <= 0) continue;
//			// 깨트리기
//			eggs[idx].d -= eggs[i].w;
//			eggs[i].d -= eggs[idx].w;
//			simulation(idx + 1); // 다음 순서
//			eggs[idx].d += eggs[i].w;
//			eggs[i].d += eggs[idx].w;
//		}
//
//		// 깨진 계란 개수 세기
//		int cnt = 0;
//		for (int i = 0; i < N; i++) {
//			if (eggs[i].d <= 0) cnt++;
//		}
//
//		if (ans < cnt) {
//			ans = cnt;
//		}
//	}
//
//	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//		N = scanner.nextInt();
//		eggs = new Egg[N];
//		ans = 0;
//
//		for (int i = 0; i < N; i++) {
//			int d = scanner.nextInt();
//			int w = scanner.nextInt();
//			eggs[i] = new Egg(d, w);
//		}
//
//		simulation(0);
//		System.out.println(ans);
//	}
//}
