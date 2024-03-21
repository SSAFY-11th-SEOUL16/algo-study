import java.io.*;
import java.util.*;


public class BJ14502_연구소 {
	static int n,m;
	static int arr[][];
	static ArrayList<int[]> al = new ArrayList<>();
	static boolean visited[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int blockSize = 0; //벽 개수
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				//바이러스 위치 추가
				if(arr[i][j] == 2) {
					al.add(new int[] {i, j});
				}
				if(arr[i][j] == 1)	blockSize++; //벽 개수
				
			}
		}
		
		blockSize+=3;
		int result = 0;
		
		for(int i=0;i<n*m;i++) {
			int r1 = i/m;
			int c1 = i%m;
			
			//빈 칸이 아닌 경우
			if(arr[r1][c1] != 0) continue;
			arr[r1][c1] = 1;
			
			for(int j=i+1;j<n*m;j++) {
				int r2 = j/m;
				int c2 = j%m;
				//빈 칸이 아닌 경우
				if(arr[r2][c2] != 0) continue;
				arr[r2][c2] = 1;
				
				for(int k=j+1;k<n*m;k++) {
					int r3 = k/m;
					int c3 = k%m;
					
					//빈 칸이 아닌 경우
					if(arr[r3][c3] != 0) continue;
				
					arr[r3][c3] = 1;
					visited = new boolean[n][m];
					//바이러스 이동
					for(int[] xy : al) {
						if(visited[xy[0]][xy[1]]) continue;
						bfs(xy[0], xy[1]);
					}
					
					//안전영역 개수
					int safeAreaCnt = safeAreaCnt() - blockSize;
					result = Integer.max(result, safeAreaCnt);
					
					//원복
					arr[r3][c3] = 0;
				}
				//원복
				arr[r2][c2] = 0;
			}
			//원복
			arr[r1][c1] = 0;
		}
		System.out.println(result);
	}
	
	static int dx[] = {0,0,-1,1};
	static int dy[] = {-1,1,0,0};
	static void bfs(int x, int y) {
		Deque<int[]> q = new ArrayDeque<>();
		q.add(new int[] {x, y});
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int cur[] = q.pollFirst();
			
			for(int i=0;i<4;i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
				if(visited[nx][ny]) continue;
				if(arr[nx][ny] == 1) continue;
				
				q.addLast(new int[] {nx,ny});
				visited[nx][ny] = true;
			}
		}
	}
	
	//안전영역 개수 반환
	static int safeAreaCnt() {
		int cnt=0;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(visited[i][j] == false) {
					cnt++;
				}
			}
		}
		
		return cnt;
	}
}

