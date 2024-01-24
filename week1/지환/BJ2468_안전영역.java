package week1.지환;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2468_안전영역 {
	static int n;
	static int[][] board;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		board = new int[n][n];
		StringTokenizer st;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int res = 0;
		for(int i=0; i<=100; i++) {
			res = Math.max(res, find(i));
		}
		System.out.println(res);
	}

	static int find(int k) {
		boolean[][] visited = new boolean[n][n];
		int cnt = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(board[i][j]-k > 0 && !visited[i][j]) {
					dfs(i, j, k, visited);
					cnt++;
				}
			}
		}
		return cnt;
	}

	static void dfs(int x, int y, int k, boolean[][] visited) {
		if(visited[x][y]) {
			return;
		}
		visited[x][y] = true;
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(checkRange(nx,ny) && board[nx][ny]-k>0) {
				dfs(nx, ny, k, visited);
			}
		}
	}

	static boolean checkRange(int x, int y) {
		return x>=0 && x<n && y>=0 && y<n;
	}
}
