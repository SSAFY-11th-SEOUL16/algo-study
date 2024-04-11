import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ3190_뱀 {
	static int[] dx = {0,1,0,-1}; //R, D, L, U
	static int[] dy = {1,0,-1,0};

	static int n;
    //뱀의 좌표들을 모두 저장해놓는 큐.
	static Queue<int[]> q = new LinkedList<>();
    //nxn배열, -1은 사과, 1은 현재 뱀의 위치
	static int[][] board;
    //(초, 회전방향) 저장 배열 
	static int[] rotate = new int[10055];
	static int time = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		board = new int[n][n];
		int k = Integer.parseInt(br.readLine());
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			board[r-1][c-1] = -1;
		}
		int l = Integer.parseInt(br.readLine());
		for(int i=0; i<l; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			if(d=='L') rotate[idx] = -1;
			else if(d=='D') rotate[idx] = 1;
		}
		board[0][0]=1;
		q.add(new int[]{0,0});
		dfs(0,0,0);
		System.out.println(time);
	}

	public static void dfs(int x, int y, int d) {
        //해당 초에 방향 선택
		d=(d+rotate[time]+4)%4;
		int nx = x + dx[d];
		int ny = y + dy[d];
		time++;
        //벽이나 자기 자신 만나면 종료
		if(!check(nx,ny) || board[nx][ny] == 1) {
			return;
		}
        //사과를 만나면 뱀 길이 증가. 해당 위치는 머리가 된다.
		else if(board[nx][ny]==-1) {
			board[nx][ny]=1;
			q.add(new int[]{nx,ny});
			dfs(nx, ny, d);
        //아무것도 없다면 머리 위치, 꼬리 위치 변경
        //기존에 꼬리 제거(큐에서 remove)
		} else if (board[nx][ny]==0) {
			board[nx][ny]=1;
			q.add(new int[]{nx,ny});
			int[] remove = q.remove();
			board[remove[0]][remove[1]]=0;
			dfs(nx, ny, d);
		}
	}

	public static boolean check(int x, int y) {
		return x>=0 && x<n && y>=0 && y<n;
	}
}
