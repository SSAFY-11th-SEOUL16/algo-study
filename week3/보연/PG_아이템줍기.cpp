#include <iostream>
#include <string>
#include <vector>
#include <cstring>
#include <queue>

using namespace std;
typedef pair<int, int> ci;

const int MAX_SIZE = 101;
int board[MAX_SIZE][MAX_SIZE];
int MIN = MAX_SIZE;
int result = 0;

void printBoard() {
	for (int i = 0; i < MAX_SIZE; i++) {
		for (int j = 0; j < MAX_SIZE; j++) {
			cout << board[i][j] << " ";
		}
		cout << '\n';
	}
}

void bfs(int characterX, int characterY, int itemX, int itemY) {
	int dx[4] = { -1, 0, 1, 0 };
	int dy[4] = { 0, 1, 0, -1 };
	queue<ci> que;
	//que.push(make_pair(characterY, characterX));
	que.emplace(characterY, characterX);
	result = 0;
	while (!que.empty()) {
		ci curr = que.front();
		que.pop();
		if (curr.first == itemY && curr.second == itemX) {
			//아이템 위치에 도달하면 기록 반환
			if (MIN > board[curr.first][curr.second]) {
				MIN = board[curr.first][curr.second];
				result = MIN;
			}
			result = board[curr.first][curr.second];
			break;
		}

		//테두리 돌기
		for (int i = 0; i < 4; i++) {
			int nx = curr.second + dx[i];
			int ny = curr.first + dy[i];

			if (board[ny][nx] == 1) {
				board[ny][nx] = board[curr.first][curr.second] + 1; //거리 기록
				//que.push(make_pair(nr, nc));
				que.emplace(ny, nx);
			}
		}

	}

}

int solution(vector<vector<int>> rectangle, int characterX, int characterY, int itemX, int itemY) {
	int answer = 0;
	memset(board, 0, sizeof(board));

	//사각형 1로 채우기
	for (int i = 0; i < rectangle.size(); i++) {
		int x1 = rectangle[i][0] * 2;
		int y1 = rectangle[i][1] * 2;
		int x2 = rectangle[i][2] * 2;
		int y2 = rectangle[i][3] * 2;

		for (int i = y1; i <= y2; i++) {
			for (int j = x1; j <= x2; j++) {
				board[i][j] = 1;
			}
		}
	}

	//사각형 내부 0으로 채우기
	for (int i = 0; i < rectangle.size(); i++) {
		int x1 = rectangle[i][0] * 2;
		int y1 = rectangle[i][1] * 2;
		int x2 = rectangle[i][2] * 2;
		int y2 = rectangle[i][3] * 2;

		for (int x = y1 + 1; x < y2; x++) {
			for (int y = x1 + 1; y < x2; y++) {
				board[x][y] = 0;
			}
		}
	}

	//printBoard();
	bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
	answer = result / 2;
	return answer;
}
//
//import java.util.*;
//
//public class Solution {
//	static final int MAX_SIZE = 101;
//	static int[][] board = new int[MAX_SIZE][MAX_SIZE];
//	static int MIN = MAX_SIZE;
//	static int result = 0;
//
//	public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
//		int answer = 0;
//		Arrays.stream(board).forEach(a->Arrays.fill(a, 0));
//
//		// 사각형 1로 채우기
//		for (int[] rec : rectangle) {
//			int x1 = rec[0] * 2;
//			int y1 = rec[1] * 2;
//			int x2 = rec[2] * 2;
//			int y2 = rec[3] * 2;
//
//			for (int i = y1; i <= y2; i++) {
//				for (int j = x1; j <= x2; j++) {
//					board[i][j] = 1;
//				}
//			}
//		}
//
//		// 사각형 내부 0으로 채우기
//		for (int[] rec : rectangle) {
//			int x1 = rec[0] * 2;
//			int y1 = rec[1] * 2;
//			int x2 = rec[2] * 2;
//			int y2 = rec[3] * 2;
//
//			for (int x = y1 + 1; x < y2; x++) {
//				for (int y = x1 + 1; y < x2; y++) {
//					board[x][y] = 0;
//				}
//			}
//		}
//
//		bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
//		answer = result / 2;
//		return answer;
//	}
//
//	static void bfs(int characterX, int characterY, int itemX, int itemY) {
//		int[] dx = { -1, 0, 1, 0 };
//		int[] dy = { 0, 1, 0, -1 };
//		Queue<int[]> que = new LinkedList<>();
//		result = 0;
//
//		que.offer(new int[] {characterY, characterX});
//		while (!que.isEmpty()) {
//			int[] curr = que.poll();
//			if (curr[0] == itemY && curr[1] == itemX) {
//				if (MIN > board[curr[0]][curr[1]]) {
//					MIN = board[curr[0]][curr[1]];
//					result = MIN;
//				}
//				result = board[curr[0]][curr[1]];
//				break;
//			}
//
//			for (int i = 0; i < 4; i++) {
//				int nx = curr[1] + dx[i];
//				int ny = curr[0] + dy[i];
//
//				if (board[ny][nx] == 1) {
//					board[ny][nx] = board[curr[0]][curr[1]] + 1;
//					que.offer(new int[] {ny, nx});
//				}
//			}
//		}
//	}
//}
