package week3.지환;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW5650_핀볼 {
	static int[] dx = new int[]{-1, 0, 1, 0};
	static int[] dy = new int[] {0, 1, 0, -1};
	static int n;
	static int[][] board;
	//시작 좌표
	static int[] start;
	//가능한 최고 점수
	static int max = 0;
	//2개의 웜홀 좌표의 합 
	static int[][] warmhole;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine().trim());
		StringTokenizer st;
		for(int t=1; t<=test; t++) {
			n = Integer.parseInt(br.readLine().trim());
			board = new int[n][n];
			warmhole = new int[11][2];
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<n; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if(board[i][j] >= 6) {
						warmhole[board[i][j]][0] += i;
						warmhole[board[i][j]][1] += j;
					}
				}
			}
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(board[i][j] == 0) {
						start = new int[] {i,j};
						for(int d=0; d<4; d++) {
							dfs(start[0], start[1], d, 0, 0);
						}
					}
				}
			}
			
			System.out.println("#" + t + " " + max);
			max = 0;
		}
	}
	
	public static void dfs(int x, int y, int d, int cnt, int dep) {
		//블랙홀이거나 출발점의 경우 계산 후 종료
		if(checkRange(x, y)) {
			if(board[x][y] == -1 || (dep>0 && x==start[0] && y==start[1])) {
				max = Math.max(max, cnt);
				return;
			}
		}

		int nx = x + dx[d];
		int ny = y + dy[d];
		if(checkRange(nx, ny)) {
			//블록일 경우
			if(board[nx][ny] >= 1 && board[nx][ny] <= 5) {
				//블록인데 반대방향으로 되돌아가는 경우
				if(checkIfWall(nx, ny, d)) {
					dfs(nx, ny, (d+2)%4, cnt+1, dep+1);
				} else {
					//블록에 따라 90도로 방향이 바뀌는 경우
					dfs(nx, ny, changeDir(nx, ny, d), cnt+1, dep+1);
				}
				//웜홀인 경우 좌표 이동
			} else if (board[nx][ny] >= 6 && board[nx][ny] <= 10) {
				int tx = warmhole[board[nx][ny]][0];
				int ty = warmhole[board[nx][ny]][1];
				dfs(tx - nx, ty - ny, d, cnt, dep+1);
				//0이나 -1일 경우에 이동
			} else {
				dfs(nx, ny, d, cnt, dep+1);
			}
			//벽에 맞고 반대방향으로 되돌아가는 경우
		} else {
			dfs(nx, ny, (d+2)%4, cnt+1, dep+1);
		}
	}
	
	public static boolean checkIfWall(int x, int y, int d) {
		int block = board[x][y];
		switch (block) {
		case 1: return !(d==2 || d==3);
		case 2: return !(d==0 || d==3);
		case 3: return !(d==0 || d==1);
		case 4: return !(d==1 || d==2);
		}
		return true;
	}
	
	public static int changeDir(int x, int y, int d) {
		int block = board[x][y];
		if(block==1) {
			if(d==2) return 1;
			else if(d==3) return 0;
		} else if(block==2) {
			if(d==0) return 1;
			else if(d==3) return 2;
		} else if(block==3) {
			if(d==0) return 3;
			else if(d==1) return 2;
		} else if(block==4) {
			if(d==1) return 0;
			else if(d==2) return 3;
		}
		return 5;
	}
	
	public static boolean checkRange(int x, int y) {
		return x>=0 && x<n && y>=0 && y<n;
	}
}
