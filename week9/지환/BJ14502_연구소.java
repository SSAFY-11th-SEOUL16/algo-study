import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


//Java(316ms)
public class BJ14502_연구소 {
	
	static int n, m;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static List<int[]> candidates = new ArrayList<>();
	static int safeArea = 0;
	static List<int[]> virus = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 0) {
					safeArea++;
					candidates.add(new int[]{i,j});
				} else if(board[i][j] == 2) {
					virus.add(new int[]{i,j});
				}
			}
		}
		
		//비어있는 칸 중에(보드에 값이 0인 값들 중) 3개 선택
		int ans = 0;
		for(int i=0; i<safeArea-2; i++) {
			for(int j=i+1; j<safeArea-1; j++) {
				for(int k=j+1; k<safeArea; k++) {
					board[candidates.get(i)[0]][candidates.get(i)[1]] = 1;
					board[candidates.get(j)[0]][candidates.get(j)[1]] = 1;
					board[candidates.get(k)[0]][candidates.get(k)[1]] = 1;
					ans = Math.max(ans, safeArea - 3 - bfs(board));
					board[candidates.get(i)[0]][candidates.get(i)[1]] = 0;
					board[candidates.get(j)[0]][candidates.get(j)[1]] = 0;
					board[candidates.get(k)[0]][candidates.get(k)[1]] = 0;
				}
			}
		}
		
		System.out.println(ans);
	}
	
	//바이러스들에 대해 bfs진행
	public static int bfs(int[][] board) {
		
		Queue<int[]> q = new LinkedList<>(virus);
		boolean[][] visited = new boolean[n][m];
		int cnt = 0;
		while(!q.isEmpty()) {
			int[] tmp = q.remove();
			for(int i=0; i<4; i++) {
				int nx = tmp[0] + dx[i];
				int ny = tmp[1] + dy[i];
				if(checkRange(nx, ny) && !visited[nx][ny] && board[nx][ny] == 0) {
					visited[nx][ny] = true;
					q.add(new int[] {nx,ny});
					cnt++;
				}
			}
		}

		return cnt;
	}
	
	public static boolean checkRange(int x, int y) {
		return x>=0 && x<n && y>=0 && y<m;
	}
}
