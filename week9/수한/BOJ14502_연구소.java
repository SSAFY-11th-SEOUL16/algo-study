package algorithm.baekjoon;
import java.util.*;
import java.io.*;

public class Main_14502_연구소
{
	static int N, M;
	static int[][] map;
	static int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
	static int answer;
	static int numBlanks;
	static int numVirus;
	
	public static void insfect(int x, int y, boolean[][] visited) {
		for (int i = 0; i < dirs.length; i++) {
			int nx = x+dirs[i][0];
			int ny = y+dirs[i][1];
			
			if(nx < 0 || nx == N || ny < 0 || ny == M || map[nx][ny] != 0 || visited[nx][ny]) continue;
			visited[nx][ny] = true;
			numVirus++;
			insfect(nx,ny,visited);
		}
	}
	
	public static void comb(int x, int y, int cnt) {
		if(cnt == 3) {
			boolean[][] visited = new boolean[N][M];
			numVirus = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] == 2) {
						insfect(i,j,visited);
					}
				}
			}
			answer = Math.max(answer, numBlanks - numVirus - 3);
			return;
		}
		
		// 같은 라인
		for (int j = y+1; j < M; j++) {
			if(map[x][j] != 0) continue;
			
			map[x][j] = 1;
			comb(x,j,cnt+1);
			map[x][j] = 0;	
		}
		
		// 다른 라인
		for (int i = x+1; i < N; i++) {
			for (int j = 0; j < M; j++) {
				
				if(map[i][j] != 0) continue;
				
				map[i][j] = 1;
				comb(i,j,cnt+1);
				map[i][j] = 0;
				
			}
		}
	}
	
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        
		N=Integer.parseInt(tokenizer.nextToken());
		M=Integer.parseInt(tokenizer.nextToken());
			
		// 0은 빈 칸, 1은 벽, 2는 바이러스
		numBlanks = 0;
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {				
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
				if(map[i][j] == 0) numBlanks++;
			}
		}
		
		answer = 0;
		comb(0,-1,0);
		
		System.out.println(answer);

	}
}