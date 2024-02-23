package algorithm.SWEA.problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2105_디저트_카페
{
	static int answer;
	
	static int N;
	
	static int[][] map;
    
	// 대각선
	// 시계방향 : 오른쪽 아래, 왼쪽 아래, 왼쪽 위, 오른쪽 위
	static int[] dx = {1,1,-1,-1};
	static int[] dy = {1,-1,-1,1};
	
	static class Coord{
		int x, y, cnt;

		public Coord(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	
	public static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	
	public static void dfs(int x, int y, int cnt, boolean[] visited, int stX, int stY, int dir) {
		// 일직선
		int nx = x + dx[dir];
		int ny = y + dy[dir];

		if(isRange(nx, ny)) {
			if(nx == stX && ny == stY) {
				answer = Math.max(answer, cnt);
				return;
			}
			if(!visited[map[nx][ny]]) {
				visited[map[nx][ny]] = true;
				dfs(nx,ny,cnt+1,visited,stX, stY, dir);
				visited[map[nx][ny]] = false;
			}
		}
		
		if(dir != 3) {
			dfs(x,y,cnt,visited,stX, stY, dir+1);
		}
	}
	
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(br.readLine());

        StringTokenizer tokens;
        
        StringBuilder sb = new StringBuilder();
        
        for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			
			int maxHeight = 0;
			
			for (int i = 0; i < N; i++) {
				tokens = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(tokens.nextToken());
					if(maxHeight < map[i][j]) maxHeight = map[i][j];
				}
			}
			
			answer = -1;
			
			boolean[] visited = new boolean[101];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visited[map[i][j]] = true;
					dfs(i,j,1, visited, i,j, 0);
					visited[map[i][j]] = false;
				}
			}

			sb.append('#').append(t).append(' ').append(answer < 4 ? "-1" : answer).append('\n');
		}
        
        System.out.print(sb.toString());
    }
}