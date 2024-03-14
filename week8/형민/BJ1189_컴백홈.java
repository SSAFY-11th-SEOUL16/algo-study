import java.io.*;
import java.util.*;

public class BJ1189_컴백홈 {
    static int r, c, k;
    static char arr[][];
    static int dx[] = {0, 0, -1, 1};
    static int dy[] = {-1, 1, 0, 0};
    static int result = 0;
    static boolean visited[][];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new char[r][c];
        visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        visited[r - 1][0] = true;
        backtrack(r-1, 0, 1);
        System.out.println(result);
    }
    static void backtrack(int x, int y, int cnt) {
	//집에 도착
        if(x == 0 && y == c-1){
	    //거리가 K와 같을 때
            if(cnt == k) result++;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<0 || ny <0 || nx>=r || ny>=c) continue;
            if(visited[nx][ny]) continue;
            if(arr[nx][ny] == 'T') continue;

            visited[nx][ny] = true;
            backtrack(nx, ny, cnt + 1);
            visited[nx][ny] = false;
        }
    }
}
