import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW2111_보호필름 {
	static int[][] board;
	static int[][] copy;
	static int res;
	static int d,w,k;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int test = Integer.parseInt(br.readLine());
        for(int t=1; t<=test; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            board = new int[d][w];
            copy = new int[d][w];
            res = Integer.MAX_VALUE;
            for(int i=0; i<d; i++) {
            	st = new StringTokenizer(br.readLine(), " ");
            	for(int j=0; j<w; j++) {
            		board[i][j] = Integer.parseInt(st.nextToken());
            		copy[i][j] = board[i][j];
            	}
            }
            dfs(0, 0);
            System.out.println("#" + t + " " + res);
        }
	}
	
	public static void dfs(int idx, int cnt) {
		if(cnt > res) return;
		if(idx==d) {
			if(check()) res = Math.min(res, cnt);
			return;
		}
		dfs(idx+1, cnt);
		for(int i=0; i<w; i++) {
			board[idx][i] = 0;
		}
		dfs(idx+1, cnt+1);
		for(int i=0; i<w; i++) {
			board[idx][i] = 1;
		}
		dfs(idx+1, cnt+1);
		for(int i=0; i<w; i++) {
			board[idx][i] = copy[idx][i];
		}
	}
	
	public static boolean check() {
		for(int i=0; i<w; i++) {
			int maxCnt = 0;
			int cnt = 1;
			for(int j=1; j<d; j++) {
				if(board[j][i] == board[j-1][i]) cnt++;
				else {
					maxCnt = Math.max(cnt, maxCnt);
					cnt=1;
				};
			}
			maxCnt = Math.max(cnt, maxCnt);
			if(maxCnt<k) return false;
		}
		return true;
	}
}
