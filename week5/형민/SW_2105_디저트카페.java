import java.io.*;
import java.util.*;
 
class SW_2105_디저트카페 {
    static int n, result, startX, startY;
    static int arr[][];
    static boolean visited[];
    static int dx[] = {1, 1, -1, -1};
    static int dy[] = {-1, 1, 1, -1};
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
 
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n][n];
            visited = new boolean[101];
 
            for (int j = 0; j <n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < n; k++) {
                    arr[j][k] = Integer.parseInt(st.nextToken());
                }
            }
 
            result = 0;
            for (int j = 0; j <n; j++) {
                for (int k = 0; k < n; k++) {
                    startX = j;
                    startY = k;
                    backtrack(j,k, 0, 0);
                }
            }
 
            if(result == 0) sb.append("#" + (i + 1) + " -1" + "\n");
            else sb.append("#" + (i + 1) + " " + result + "\n");
        }
 
        System.out.println(sb.toString());
    }
 
    static void backtrack(int x, int y, int di, int cnt) {
        if(startX == x && startY == y && cnt != 0) {
            result = Math.max(result, cnt);
            return;
        }
 
        int len = Math.min(di + 1, 3);
 
        //시작점인 경우 방향은 한 방향
        if(startX == x && startY == y && cnt == 0) {
            len = di;
        }
 
        for (int i = di; i <= len; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
 
            if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
            if(visited[arr[nx][ny]]) continue;
 
            visited[arr[nx][ny]] = true;
            backtrack(nx, ny, i, cnt + 1);
            visited[arr[nx][ny]] = false;
        }
    }
}
