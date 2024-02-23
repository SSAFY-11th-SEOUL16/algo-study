package algorithm.SWEA.problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1949_등산로_조성
{
	static int answer;
	
	static int N,K;
	
	static int[][] map;
	static boolean[][] visited;
    
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	
	static class Coord{
		int x, y, cnt;

		public Coord(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	
	public static boolean isBound(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
	
	public static void dfs(int x, int y, int cnt, boolean isDone) {
		answer = Math.max(answer, cnt);
		
		visited[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(isBound(nx, ny) || visited[nx][ny] || map[nx][ny] - K >= map[x][y]) continue;
			
			int temp = map[nx][ny];
			boolean tempDone = isDone;
			if(map[x][y] <= map[nx][ny]) {
				if(isDone) continue;
				map[nx][ny] = map[x][y]-1;
				tempDone = true;
			}
			
			dfs(nx,ny,cnt+1,tempDone);

			map[nx][ny] = temp;
		}
		
		visited[x][y] = false;
	}
	
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(br.readLine());

        StringTokenizer tokens;
        
        StringBuilder sb = new StringBuilder();
        
        for (int t = 1; t <= T; t++) {
        	tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken()); // 세로
			K = Integer.parseInt(tokens.nextToken()); // 가로
			
			map = new int[N][N];
			
			int maxHeight = 0;
			
			for (int i = 0; i < N; i++) {
				tokens = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(tokens.nextToken());
					if(maxHeight < map[i][j]) maxHeight = map[i][j];
				}
			}
			
			visited = new boolean[N][N];
			
			answer = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] == maxHeight) {
						dfs(i,j,1,false);
					}
				}
			}

			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
        
        System.out.print(sb.toString());
    }
}