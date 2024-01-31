#include <iostream>
#include <cstring>
#include <vector>
#include <map>
#include <algorithm>

using namespace std; 
typedef pair<int, int> ci;

struct Cell {
	int id;
	int r, c; //�̻��� ���� ��ġ
	int num; //������ �̻��� ��
	int dir; //�̵�����(��1, ��2, ��3, ��4)

};

bool cmp(Cell a, Cell b) {
	return a.num < b.num;
}

const int N_MAX_SIZE = 101;
const int M_MAX_SIZE = 1001;
Cell cells[M_MAX_SIZE];
map<ci, vector<Cell>> visit;

int dr[] = { -1, 1, 0, 0 };
int dc[] = { 0,0,-1,1 };

int ans, N, M, K;

int changeDir(int dir) {
	if (dir == 0 || dir == 2) dir++;
	if (dir == 1 || dir == 3) dir--;

	return dir;
}

void move() {
	for (int i = 0; i < K; i++) {
		if (cells[i].num == 0) continue;
		int nr = cells[i].r + dr[cells[i].dir - 1];
		int nc = cells[i].c + dc[cells[i].dir - 1];

		if (nr == 0 || nc == 0 || nr == N - 1 || nc == N - 1) {
			//�����ڸ��� �����ϸ� ���� �� �̻��� �� /2, �̵����� �ݴ�� �ٲ�
			cells[i].num /= 2;
			//cells[i].dir = changeDir(cells[i].dir);
			if (cells[i].dir % 2 == 0) cells[i].dir++;
			else cells[i].dir--;
		}
		cells[i].r = nr;
		cells[i].c = nc;
		visit[make_pair(cells[i].r, cells[i].c)].push_back(cells[i]);
	}
}

void add() {
	for (int i = 0; i < K; i++) {
		vector<Cell> curr = visit[make_pair(cells[i].r, cells[i].c)];
		if (cells[i].num == 0 || curr.size() <= 1) continue;
		
		//��ġ�� �������� �ִٸ�
		sort(curr.begin(), curr.end(), cmp);
		for (int j = 1; j < curr.size(); j++) {
			curr[0].num += curr[j].num;
			curr[j].num = 0;
			cells[curr[j].id] = curr[j]; //��ġ�� ���� �� ���� �ֵ�(0����)
		}
		cells[curr[0].id] = curr[0]; //��ġ�� ���� �� ���� ū �ֵ�
		
	}
}

void simulation() {
	//M�ð����� �̻��� �ݸ�
	while (M > 0) {
		visit.clear(); //�ʱ�ȭ
		//1�ð����� ���� ���� �̵�
		move();
		//�� �� �̻��� ������ �� ���� ���̸� ��ģ��
		add();
		M--;
	}

	for (int i = 0; i < K; i++) {
		if (cells[i].num == 0) continue;
		ans += cells[i].num;
	}
}

int main() {
	int T, tc;
	cin >> T;

	for (tc = 1; tc <= T; tc++) {
		ans = 0;
		memset(cells, 0, sizeof(cells));
		cin >> N >> M >> K;
		for (int i = 0; i < K; i++) {
			cin >> cells[i].r >> cells[i].c >> cells[i].num >> cells[i].dir;
			cells[i].id = i; //���� ��ȣ
		}

		simulation();

		cout << '#' << tc << ' ' << ans << '\n';
	}

	return 0;
}
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//
//class Cell {
//	int id;
//	int r, c;
//	int num;
//	int dir;
//
//	public Cell(int id, int r, int c, int num, int dir) {
//		this.id = id;
//		this.r = r;
//		this.c = c;
//		this.num = num;
//		this.dir = dir;
//	}
//}
//
//public class Main {
//	static final int N_MAX_SIZE = 101;
//	static final int M_MAX_SIZE = 1001;
//	static Cell[] cells = new Cell[M_MAX_SIZE];
//	static Map<ci, ArrayList<Cell>> visit = new HashMap<>();
//
//	static int[] dr = { -1, 1, 0, 0 };
//	static int[] dc = { 0, 0, -1, 1 };
//
//	static int ans, N, M, K;
//
//	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//
//		int T = scanner.nextInt();
//		for (int tc = 1; tc <= T; tc++) {
//			ans = 0;
//			visit.clear();
//			N = scanner.nextInt();
//			M = scanner.nextInt();
//			K = scanner.nextInt();
//
//			for (int i = 0; i < K; i++) {
//				int r = scanner.nextInt();
//				int c = scanner.nextInt();
//				int num = scanner.nextInt();
//				int dir = scanner.nextInt();
//				cells[i] = new Cell(i, r, c, num, dir);
//			}
//
//			simulation();
//
//			System.out.println('#' + tc + ' ' + ans);
//		}
//	}
//
//	static boolean cmp(Cell a, Cell b) {
//		return a.num < b.num;
//	}
//
//	static int changeDir(int dir) {
//		if (dir == 0 || dir == 2) dir++;
//		if (dir == 1 || dir == 3) dir--;
//
//		return dir;
//	}
//
//	static void move() {
//		for (int i = 0; i < K; i++) {
//			if (cells[i].num == 0) continue;
//			int nr = cells[i].r + dr[cells[i].dir - 1];
//			int nc = cells[i].c + dc[cells[i].dir - 1];
//
//			if (nr == 0 || nc == 0 || nr == N - 1 || nc == N - 1) {
//				cells[i].num /= 2;
//				if (cells[i].dir % 2 == 0) cells[i].dir++;
//				else cells[i].dir--;
//			}
//			cells[i].r = nr;
//			cells[i].c = nc;
//			visit.computeIfAbsent(new ci(cells[i].r, cells[i].c), k -> new ArrayList<>()).add(cells[i]);
//		}
//	}
//
//	static void add() {
//		for (int i = 0; i < K; i++) {
//			ArrayList<Cell> curr = visit.get(new ci(cells[i].r, cells[i].c));
//			if (cells[i].num == 0 || curr.size() <= 1) continue;
//
//			Collections.sort(curr, this::cmp);
//			for (int j = 1; j < curr.size(); j++) {
//				curr.get(0).num += curr.get(j).num;
//				curr.get(j).num = 0;
//				cells[curr.get(j).id] = curr.get(j);
//			}
//			cells[curr.get(0).id] = curr.get(0);
//		}
//	}
//
//	static void simulation() {
//		while (M > 0) {
//			visit.clear();
//			move();
//			add();
//			M--;
//		}
//
//		for (int i = 0; i < K; i++) {
//			if (cells[i].num == 0) continue;
//			ans += cells[i].num;
//		}
//	}
//}
