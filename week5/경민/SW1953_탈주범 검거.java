import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {

	static class Node {
		int x;
		int y;
		int time;

		public Node(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		int[] dx = { 0, 0, -1, 1 };
		int[] dy = { 1, -1, 0, 0 };

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken()); // 흐른 시간

			int[][] board = new int[N][M];
			boolean[][] visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			Deque<Node> q = new ArrayDeque<>();
			q.offer(new Node(R, C, 1));
			visited[R][C] = true;
			int count = 1;
			while (!q.isEmpty()) {
				Node now = q.poll();
				if (now.time == L)
					break;
		
				for (int i = 0; i < 4; i++) {
					
					// 지나갈 수 없는 방향이면 continue;
					if(board[now.x][now.y] == 2 && (i==0 || i==1) ) continue;
					if(board[now.x][now.y] == 3 && (i==2 || i==3) ) continue;
					if(board[now.x][now.y] == 4 && (i==1 || i==3) ) continue;
					if(board[now.x][now.y] == 5 && (i==1 || i==2) ) continue;
					if(board[now.x][now.y] == 6 && (i==0 || i==2) ) continue;
					if(board[now.x][now.y] == 7 && (i==0 || i==3) ) continue;
			
					int nx = now.x + dx[i];
					int ny = now.y + dy[i];
					
					// 지나갈 수 있는 방향 및 방문하지 않는 곳이면
					if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && board[nx][ny] != 0) {
						int tmp = board[nx][ny];
						
						if (i == 0 && (tmp == 1 || tmp == 3 || tmp == 6 || tmp == 7)) {
							count++;
							visited[nx][ny] = true;
							q.offer(new Node(nx, ny, now.time + 1));
						} else if (i == 1 && (tmp == 1 || tmp == 3 || tmp == 4 || tmp == 5)) {
							count++;
							visited[nx][ny] = true;
							q.offer(new Node(nx, ny, now.time + 1));
						} else if (i == 2 && (tmp == 1 || tmp == 2 || tmp == 5 || tmp == 6)) {
							count++;
							visited[nx][ny] = true;
							q.offer(new Node(nx, ny, now.time + 1));
						} else if (i == 3 && (tmp == 1 || tmp == 2 || tmp == 4 || tmp == 7)) {
							count++;
							visited[nx][ny] = true;
							q.offer(new Node(nx, ny, now.time + 1));
						}

					}

				}
			}

			sb.append('#').append(t).append(' ').append(count).append('\n');

		}
		System.out.println(sb);
	}
}