import java.io.*;
import java.util.*;
 
public class SW_1953_탈주범검거 {
    static int n, m, r, c, l, result;
    static int arr[][];
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
         
        int t = Integer.parseInt(br.readLine());
        for(int i=0;i<t;i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
            result = 0;
             
            visited = new boolean[n][m];
            arr = new int[n][m];
             
            for(int j=0;j<n;j++) {
                st = new StringTokenizer(br.readLine());
                for(int k=0;k<m;k++) {
                    arr[j][k] = Integer.parseInt(st.nextToken());
                }
            }
             
            bfs(r, c);
            sb.append("#" + (i+1) + " " + result + "\n");
        }
        System.out.println(sb.toString());
    }
     
    static boolean possibleDi[] = new boolean[4]; //상, 하, 좌 , 우
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};
    static boolean visited[][];
    static void bfs(int x, int y) {
        Deque<int[]> q = new ArrayDeque<int[]>();
        q.add(new int[] {x,y});
         
        l--;
        result++;
        visited[x][y] = true;
         
        while(!q.isEmpty() && l > 0) {
            l--;
            int size= q.size();
            for(int j=0;j<size;j++) {
                int[] cur = q.pollFirst();
                 
                for(int i=0;i<4;i++) {
                    possibleDi[i] = false;
                }
                 
                switch(arr[cur[0]][cur[1]]) {
                    case 1:
                        possibleDi[0] = true; possibleDi[1] = true; possibleDi[2] = true; possibleDi[3] = true;
                        break;
                    case 2:
                        possibleDi[0] = true; possibleDi[1] = true;
                        break;
                    case 3:
                        possibleDi[2] = true; possibleDi[3] = true;
                        break;
                    case 4:
                        possibleDi[0] = true; possibleDi[3] = true;
                        break;
                    case 5:
                        possibleDi[1] = true; possibleDi[3] = true;
                        break;
                    case 6:
                        possibleDi[1] = true; possibleDi[2] = true;
                        break;
                    case 7:
                        possibleDi[0] = true; possibleDi[2] = true;
                        break;
                }
                 
                for(int i=0;i<4;i++) {
                    if(!possibleDi[i]) continue;
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];
         
                    if(nx<0 || ny<0 || nx >= n || ny >= m) continue; //범위초과
                    if(arr[nx][ny] == 0) continue; //벽
                    if(visited[nx][ny]) continue; //이미 방문
                     
                    //갈 수 없는 파이프
                    if(i == 0 && (arr[nx][ny] == 3 || arr[nx][ny] == 4 || arr[nx][ny] == 7)) continue;
                    else if(i == 1 && (arr[nx][ny] == 3 || arr[nx][ny] == 5 || arr[nx][ny] == 6)) continue;
                    else if(i == 2 && (arr[nx][ny] == 2 || arr[nx][ny] == 6 || arr[nx][ny] == 7)) continue;
                    else if(i == 3 && (arr[nx][ny] == 2 || arr[nx][ny] == 4 || arr[nx][ny] == 5)) continue;
                     
                    result++;
                    visited[nx][ny] = true;
                    q.addLast(new int[] {nx, ny});
                }
            }
             
        }
         
    }
 
}
