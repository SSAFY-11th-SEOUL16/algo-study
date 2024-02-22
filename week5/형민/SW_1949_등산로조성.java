import java.io.*;
import java.util.*;
 
class SW_1949_등산로조성 {
    static int n, k, result;
    static int arr[][];
    static boolean visited[][];
    static int dx[] = {0, 0, -1, 1};
    static int dy[] = {-1, 1, 0, 0};
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
 
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
 
            arr = new int[n][n];
            visited = new boolean[n][n];
 
            int maxHeight = 0;
            for (int j = 0; j <n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < n; k++) {
                    arr[j][k] = Integer.parseInt(st.nextToken());
                    maxHeight = Math.max(maxHeight, arr[j][k]);
                }
            }
 
            result = 1;
            for (int j = 0; j <n; j++) {
                for (int k = 0; k < n; k++) {
                    if(arr[j][k] == maxHeight) {
                        visited[j][k] = true;
                        backtrack(j, k, 1, 0);
                        visited[j][k] = false;
                    }
                }
            }
 
            sb.append("#" + (i + 1) + " " + result + "\n");
        }
 
        System.out.println(sb.toString());
    }
 
    static void backtrack(int x, int y, int streetCnt, int cutCnt) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
 
            //범위 초과
            if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
 
            if(visited[nx][ny]) continue;
 
            //깎아야 하는 산의 크기
            int cutSize = arr[nx][ny] - arr[x][y] + 1;
 
            //이미 깎은 경우
            if(cutSize > 0 && cutCnt > 0) continue;
 
            //가려는 곳을 깎아도 못 가는 경우
            if(cutSize > k) continue;
 
            visited[nx][ny] = true;
            if(cutSize > 0) {
                arr[nx][ny] -= cutSize;
                backtrack(nx, ny, streetCnt + 1, cutCnt + cutSize);
                arr[nx][ny] += cutSize;
            }
            else backtrack(nx, ny, streetCnt + 1, cutCnt);
            visited[nx][ny] = false;
        }
 
        result = Math.max(result, streetCnt);
    }
}
