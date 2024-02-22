package week5.지환;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Java(158ms)
public class SW2105_디저트카페 {
	static int n;
	static int[][] board;
	static int max;
	//각 디저트를 방문했는지 체크
	static boolean[] visited = new boolean[101];
	//각 대각선 체크, 무조건 오른쪽아래부터 이동한다고 가정, 어짜피 다른 방향은 중복이므로 계산해줄 필요 x
	static int[] dx = {1,1,-1,-1};
	static int[] dy = {1,-1,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int test = Integer.parseInt(br.readLine());
		for(int t=1; t<=test; t++) {
			n = Integer.parseInt(br.readLine());
			board = new int[n][n];
			max = -1;
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<n; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<n-1; i++) {
				for(int j=1; j<n-1; j++) {
					dfs(0, i, j, i, j, 0);
				}
			}
			System.out.println("#" + t + " " + max);
		}
	}
	
	public static void dfs(int d, int x, int y, int sx, int sy, int cnt) {
		//오른쪽 위로 올라오고 출발지점으로 돌아온다면 종료(오른쪽 아래로 시작했으니 마지막은 오른쪽 위로 올라와야함)
		if(d==3 && x==sx && y==sy) {
			max = Math.max(max, cnt);
			return;
		}
		if(d==4 || cnt > (n-1)*2) return;
		if(visited[board[x][y]]) return;
		visited[board[x][y]] = true;
		int nx = x + dx[d];
		int ny = y + dy[d];
		//가던 방향으로 쭉 가는 경우
		if(checkRange(nx, ny)) dfs(d, nx, ny, sx, sy, cnt+1);
		//방향을 꺽어서 가는 경우
		if(d+1<=3) {
			nx = x + dx[d+1];
			ny = y + dy[d+1];
			if(checkRange(nx, ny)) dfs(d+1, nx, ny, sx, sy, cnt+1);
		}
		visited[board[x][y]] = false;
		
	}
	
	public static boolean checkRange(int x, int y) {
		return x>=0 && x<n && y>=0 && y<n;
	}
}
