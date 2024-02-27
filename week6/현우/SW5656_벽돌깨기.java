import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SW5656_벽돌깨기 {
	
	/**
	 * 293 ms
	 * 맵 클론 생성
	 * 구슬 발사 DFS
	 * 벽돌 폭발 4방 DFS
	 * 벽돌 낙하: 세로줄 벽돌 stack에 다 집어 넣음
	 * 다시 꺼내면서 아래쪽부터 채우기
	 */
	
	private static final int MAX_W = 12;
	private static final int MAX_H = 15;
	private static final int EMPTY = 0;
	
	private static int n, w, h, min;
	private static int[][] matrix;
	private static Deque<Integer> stack;
	
	private static int[][] getClone(int[][] map) { // 맵 클론 생성
		int i, j;
		int[][] clone;
		
		clone = new int[h][w];
		for(i = 0; i < h; i++) { // 깊은 복사
			for (j = 0; j < w; j++) {
				clone[i][j] = map[i][j];
			}
		}
		return clone;
	}
	
	private static void explode(int[][] map, int x, int y) { // 벽돌 폭발
		int range, i;
		
		range = map[x][y]; // 폭발 범위
		map[x][y] = EMPTY; // 벽돌 삭제
		if (range <= 1) {
			return;
		}
		for (i = 1; i < range && x - i >= 0; i++) { // 위쪽 폭발 범위
			explode(map, x - i, y); // 벽돌 폭발
		}
		for (i = 1; i < range && y - i >= 0; i++) { // 왼쪽 폭발 범위
			explode(map, x, y - i); // 벽돌 폭발
		}
		for (i = 1; i < range && x + i < h; i++) { // 아래쪽 폭발 범위
			explode(map, x + i, y); // 벽돌 폭발
		}
		for (i = 1; i < range && y + i < w; i++) { // 오른쪽 폭발 범위
			explode(map, x, y + i); // 벽돌 폭발
		}
	}
	
	private static int count(int[][] map) { // 벽돌 낙하
		int cnt, size, i, j;
		
		cnt = 0;
		for (i = 0; i < w; i++) {
			size = 0;
			for (j = 0; j < h; j++) { // 세로 줄 탐색
				if (map[j][i] != EMPTY) { // 벽돌 존재
					stack.push(map[j][i]); // stack에 삽입
					map[j][i] = EMPTY; // 맵 비우기
					size++; // stack 사이즈
				}
			}
			for (j = 1; j <= size; j++) { // 가장 아래 칸부터
				map[h - j][i] = stack.pop(); // stack에서 꺼내서 채우기
				cnt++; // 벽돌 개수 카운트
			}
		}
		return cnt;
	}
	
	private static void drop(int[][] map, int y, int cnt, int depth) { // 구슬 발사
		int i;
		
		for (i = 0; i < h; i++) { // 구슬 낙하
			if (map[i][y] != EMPTY) { // 벽돌 존재
				explode(map, i, y); // 벽돌 폭발 DFS
				cnt = count(map); // 벽돌 낙하, 남은 벽돌 카운트
				break;
			}
		}
		if (depth == n) { // N 개 구슬 소진
			min = Math.min(min, cnt); // 남은 벽돌 최소값 업데이트
			return;
		}
		for (i = 0; i < w; i++) {
			drop(getClone(map), i, cnt, depth + 1); // 맵 클론 생성 후 다음 구슬 발사
		}
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int i, j;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		min = 0;
		for (i = 0; i < h; i++) { // 초기 벽돌 정보 입력
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < w; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
				if (matrix[i][j] != EMPTY) {
					min++; // 초기 벽돌 개수 카운트
				}
			}
		}
		for (i = 0; i < w; i++) {
			drop(getClone(matrix), i, min, 1); // 맵 클론 생성 후 구슬 발사 DFS
		}
		return min; // 남은 벽돌 최소값 반환
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		matrix = new int[MAX_H][MAX_W];
		stack = new ArrayDeque<>();
		t = Integer.parseInt(br.readLine());
		for (testCase = 1; testCase <= t; testCase++) {
			sb.append('#').append(testCase).append(' ').append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}
