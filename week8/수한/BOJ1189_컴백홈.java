package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class Main_1189_컴백홈{

	static int R, C, K;
	static char[][] map;
	
	static int answer;
	
	static boolean[][] visited;
	
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	
	public static void dfs(int x, int y, int dist) {
		if(x == 1 && y == C && dist == K) {
			answer++;
		}
		
		if(dist >= K) return;
		
		visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(map[nx][ny] == '.' && !visited[nx][ny]) {
				dfs(nx,ny,dist+1);
			}
		}
		visited[x][y] = false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens;
		
		tokens = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		map = new char[R+2][C+2];
		
		for (int i = 1; i <= R; i++) {
			String str = br.readLine();
			for (int j = 1; j <= C; j++) {
				map[i][j] = str.charAt(j-1);
			}
		}
		
		visited = new boolean[R+2][C+2];
		
		dfs(R,1,1);
		
		System.out.println(answer);
	}

}
