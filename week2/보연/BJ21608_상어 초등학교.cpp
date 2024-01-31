#include <iostream>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

typedef pair<int, int> ci;

struct Student {
	bool like[401];
};

struct Temp {
	ci pos;
	int data;
};

const int MAX_SIZE = 21;
int N;
int map[MAX_SIZE][MAX_SIZE];
Student stud[MAX_SIZE];

bool cmp(Temp a, Temp b) {
	if (a.data == b.data) {
		if (a.pos.first == b.pos.first) {
			return a.pos.second < b.pos.second;
		}
		return a.pos.first < b.pos.first;
	}
	return a.data > b.data;
}

void findLike(int id) {
	int dr[] = { -1, 1, 0, 0 };
	int dc[] = { 0, 0, -1, 1 };
	int liked[MAX_SIZE][MAX_SIZE] = { 0, }; //인접한 곳에 좋아하는 사람이 몇명있는지 기록
	int blanked[MAX_SIZE][MAX_SIZE] = { 0, }; //인접한 곳에 빈칸이 몇개 있는지 기록
	int MAX = 0, MAX_B = 0, cnt = 0;
	vector<Temp> temp;
	ci res = make_pair(0, 0);

	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			if (map[i][j] > 0) continue; //이미 배치가 끝난 곳
			for (int d = 0; d < 4; d++) {
				int nr = i + dr[d];
				int nc = j + dc[d];

				if (nr < 1 || nc < 1 || nr > N || nc > N) continue;

				if (map[nr][nc] == 0) {
					blanked[i][j]++;
				}

				if (stud[id].like[map[nr][nc]]) {
					liked[i][j] ++;
				}
			}

			if (MAX < liked[i][j]) { //좋아하는 사람이 가장 많은 곳
				MAX = liked[i][j];
				res = make_pair(i, j);
			}
			else if (MAX == liked[i][j]) { //많은 곳이 여러개
				//인접한 칸 중에서 최댓값 구하기
				if (blanked[res.first][res.second] < blanked[i][j]) {
					res = make_pair(i, j);
				}
				else if (blanked[res.first][res.second] == blanked[i][j]) {
					if (res.first > i) {
						res = make_pair(i, j);
					}
					else if (res.first == i) {
						if (res.second > j) res = make_pair(i, j);
					}
				}

			}

		}
	}

	map[res.first][res.second] = id;
	return;
}

void printArr() {
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			cout << map[i][j] << ' ';
		}
		cout << '\n';
	}
}

void getHappyValue() {
	int dr[] = { -1, 1, 0, 0 };
	int dc[] = { 0, 0, -1, 1 };
	int count[MAX_SIZE][MAX_SIZE] = { 0, };
	int score[] = { 0, 1, 10, 100, 1000 };
	int SUM = 0, cnt = 0;
	for (int i = 1; i <= N; i++) { //좋아하는 사람이 몇이나 앉았는지 기록
		for (int j = 1; j <= N; j++) {
			int curr_stud = map[i][j];
			cnt = 0;
			for (int d = 0; d < 4; d++) {
				int nr = i + dr[d];
				int nc = j + dc[d];
				if (nr < 1 || nc < 1 || nr > N || nc > N) continue;

				if (stud[curr_stud].like[map[nr][nc]]) {
					cnt++;
				}


			}
			count[i][j] = cnt;
			SUM += score[cnt];
		}
	}

	cout << SUM;
}

int main() {
	cin >> N;

	memset(map, 0, sizeof(map));
	memset(stud, 0, sizeof(stud));

	for (int i = 0; i < N * N; i++) {
		int id;
		cin >> id;
		for (int j = 0; j < 4; j++) {
			int l;
			cin >> l;
			stud[id].like[l] = true;
		}
		//학생 배치하기
		findLike(id);
	}


	printArr();
	//학생 만족도 구하기
	getHappyValue();

	return 0;
}
//
//import java.util.Scanner;
//import java.util.Vector;
//
//class ci {
//	int first, second;
//
//	public ci(int first, int second) {
//		this.first = first;
//		this.second = second;
//	}
//}
//
//class Student {
//	boolean[] like = new boolean[401];
//}
//
//class Temp {
//	ci pos;
//	int data;
//
//	public Temp(ci pos, int data) {
//		this.pos = pos;
//		this.data = data;
//	}
//}
//
//public class Main {
//	static final int MAX_SIZE = 21;
//	static int N;
//	static int[][] map = new int[MAX_SIZE][MAX_SIZE];
//	static Student[] stud = new Student[MAX_SIZE];
//
//	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//
//		N = scanner.nextInt();
//		Vector<Temp> temp = new Vector<>();
//		int MAX_SIZE = 401;
//		int ans = 0;
//
//		for (int i = 1; i <= N; i++) {
//			stud[i] = new Student();
//			for (int j = 1; j <= N; j++) {
//				map[i][j] = 0;
//			}
//		}
//
//		for (int i = 1; i <= N * N; i++) {
//			int id = scanner.nextInt();
//			for (int j = 0; j < 4; j++) {
//				int l = scanner.nextInt();
//				stud[id].like[l] = true;
//			}
//			findLike(id, temp);
//		}
//
//		printArr();
//		getHappyValue();
//	}
//
//	static void findLike(int id, Vector<Temp> temp) {
//		int[] dr = { -1, 1, 0, 0 };
//		int[] dc = { 0, 0, -1, 1 };
//		int[][] liked = new int[MAX_SIZE][MAX_SIZE];
//		int[][] blanked = new int[MAX_SIZE][MAX_SIZE];
//
//		ci res = new ci(0, 0);
//		int MAX = 0;
//
//		for (int i = 1; i <= N; i++) {
//			for (int j = 1; j <= N; j++) {
//				if (map[i][j] > 0) continue;
//
//				for (int d = 0; d < 4; d++) {
//					int nr = i + dr[d];
//					int nc = j + dc[d];
//
//					if (nr < 1 || nc < 1 || nr > N || nc > N) continue;
//
//					if (map[nr][nc] == 0) {
//						blanked[i][j]++;
//					}
//
//					if (stud[id].like[map[nr][nc]]) {
//						liked[i][j]++;
//					}
//				}
//
//				if (MAX < liked[i][j]) {
//					MAX = liked[i][j];
//					res = new ci(i, j);
//				}
//				else if (MAX == liked[i][j]) {
//					if (blanked[res.first][res.second] < blanked[i][j]) {
//						res = new ci(i, j);
//					}
//					else if (blanked[res.first][res.second] == blanked[i][j]) {
//						if (res.first > i || (res.first == i && res.second > j)) {
//							res = new ci(i, j);
//						}
//					}
//				}
//			}
//		}
//
//		map[res.first][res.second] = id;
//	}
//
//	static void printArr() {
//		for (int i = 1; i <= N; i++) {
//			for (int j = 1; j <= N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//	}
//
//	static void getHappyValue() {
//		int[] dr = { -1, 1, 0, 0 };
//		int[] dc = { 0, 0, -1, 1 };
//		int[][] count = new int[MAX_SIZE][MAX_SIZE];
//		int[] score = { 0, 1, 10, 100, 1000 };
//		int SUM = 0;
//
//		for (int i = 1; i <= N; i++) {
//			for (int j = 1; j <= N; j++) {
//				int curr_stud = map[i][j];
//				int cnt = 0;
//
//				for (int d = 0; d < 4; d++) {
//					int nr = i + dr[d];
//					int nc = j + dc[d];
//
//					if (nr < 1 || nc < 1 || nr > N || nc > N) continue;
//
//					if (stud[curr_stud].like[map[nr][nc]]) {
//						cnt++;
//					}
//				}
//
//				count[i][j] = cnt;
//				SUM += score[cnt];
//			}
//		}
//
//		System.out.println(SUM);
//	}
//}
