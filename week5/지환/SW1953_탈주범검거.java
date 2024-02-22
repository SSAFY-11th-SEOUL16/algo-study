package week5.지환;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_1953_탈주범검거 {
	static int n;
	static int m;
	//파이프에서 갈수 있는 방향을 담은 리스트
	static List<int[]>[] d = new ArrayList[8];
	public static void main(String[] args) throws Exception {
		//리스트 초기화 ex) 1번 파이브는 상하좌우 갈 수 있다.
		d[1] = new ArrayList<>(Arrays.asList(new int[]{-1,0}, new int[]{1,0}, new int[]{0,-1}, new int[]{0,1}));
		d[2] = new ArrayList<>(Arrays.asList(new int[]{-1,0}, new int[]{1,0}));
		d[3] = new ArrayList<>(Arrays.asList(new int[]{0,-1}, new int[]{0,1}));
		d[4] = new ArrayList<>(Arrays.asList(new int[]{-1,0}, new int[]{0,1}));
		d[5] = new ArrayList<>(Arrays.asList(new int[]{1,0}, new int[]{0,1}));
		d[6] = new ArrayList<>(Arrays.asList(new int[]{1,0}, new int[]{0,-1}));
		d[7] = new ArrayList<>(Arrays.asList(new int[]{-1,0}, new int[]{0,-1}));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t=1; t<=test; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int[][] board = new int[n][m];
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<m; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			boolean[][] visited = new boolean[n][m];
			//1시간마다 파이르를 따라 이동할 수 있는 블록을 구해야하므로 BFS
			Queue<int[]> q = new LinkedList<>();
			q.add(new int[]{r,c});
			int cnt = 0;
			int time = 0;
			while(!q.isEmpty()) {
				int len = q.size();
				for(int i=0; i<len; i++) {
					int[] tmp = q.poll();
					int x = tmp[0], y = tmp[1];
					if(visited[x][y]) continue;
					visited[x][y] = true;
					cnt++;
					int pipe = board[x][y];
					for(int[] d: d[pipe]) {
						int nx = x + d[0];
						int ny = y + d[1];
						if(checkRange(nx, ny) && board[nx][ny] > 0 && checkPosPipe(d[0], d[1], board[nx][ny])) {
							q.add(new int[]{nx, ny});
						}
					}
				}
				time++;
				if(time == l) break;
			}
			System.out.println("#" + t + " " + cnt);
		}
	}
	//갈 수 있는 방향이긴 한데 가려는 방향에 파이프가 이어져 있지 않은 경우가 있다.
	//오른쪽으로 내가 간다면 해당 파이프는 왼쪽으로 길이 나있어야함.
	private static boolean checkPosPipe(int dx, int dy, int nextBoard) {
		for(int[] d: d[nextBoard]) {
			if(d[0] == -dx && d[1] == -dy) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean checkRange(int x, int y) {
		return x>=0 && x<n && y>=0 && y<m;
	}
}
