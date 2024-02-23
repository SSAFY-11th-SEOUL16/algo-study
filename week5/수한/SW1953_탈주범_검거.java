package algorithm.SWEA.problems;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1953_탈주범_검거
{
	static class Coord{
		int x, y, cnt;

		public Coord(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	
	public static boolean isBound(int x, int y, int N, int M) {
		return x < 0 || x >= N || y < 0 || y >= M;
	}
	
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(br.readLine());

        StringTokenizer tokens;
        
        // 하, 좌, 우, 상
        int[][] dirs = {{1,0},{0,-1},{0,1},{-1,0}};
        
        int[][] tunnerDirs = {
        		{},
        		{2,1,0,3},
        		{0,3},
        		{2,1},
        		{2,3},
        		{2,0},
        		{1,0},
        		{1,3}
        };
        
        StringBuilder sb = new StringBuilder();
        
        for (int t = 0; t < T; t++) {
        	tokens = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(tokens.nextToken()); // 세로
			int M = Integer.parseInt(tokens.nextToken()); // 가로
			int R = Integer.parseInt(tokens.nextToken()); // 맨홀 뚜껑 세로
			int C = Integer.parseInt(tokens.nextToken()); // 맨홀 뚜껑 가로
			int L = Integer.parseInt(tokens.nextToken()); // 탈출 후 소요시간
			
			int[][] map = new int[N][M];
			
			for (int i = 0; i < N; i++) {
				tokens = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			
			boolean[][] visited = new boolean[N][M];
			
			Queue<Coord> q = new ArrayDeque<>();
			visited[R][C] = true;
			q.add(new Coord(R,C,1));
			
			int answer = 0;
			
			while(!q.isEmpty()) {
				Coord coord = q.poll();

				answer++;
				
				if(coord.cnt == L) {
					continue;
				}
				
				int num = map[coord.x][coord.y];
				
				for (int dir : tunnerDirs[num]) {
					int nx = coord.x + dirs[dir][0];
					int ny = coord.y + dirs[dir][1];
					
					if(isBound(nx,ny,N,M) || map[nx][ny] == 0  || visited[nx][ny]) continue;
					
					int nextNum = map[nx][ny];
					
					for (int nextDir : tunnerDirs[nextNum]) {
						if(3-dir == nextDir) {
							visited[nx][ny] = true;
							q.add(new Coord(nx,ny,coord.cnt+1));
							break;
						}
					}
				}
			}
			
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
    }
}