package week3.지환;

import java.util.*;

class PG87694_아이템줍기 {
    int[][] graph = new int[101][101];
    int[][] visited = new int[101][101];
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY){
        check(rectangle);
        int ans = bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
        return ans/2;
    }
    
    public int bfs(int cx, int cy, int ix, int iy) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{cx, cy, 0});
        visited[cx][cy] = 1;
        while(!q.isEmpty()){
            int[] a = q.poll();
            int x = a[0], y = a[1], cnt = a[2];
            if(x==ix && y==iy){
                return cnt;
            } 
            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx>=0 && nx<101 && ny>=0 && ny<101) {
                    if(graph[nx][ny] == 1 && visited[nx][ny] == 0){
                        visited[nx][ny] = 1;
                        q.offer(new int[]{nx,ny,cnt+1});
                    }
                }
            }
        }
        return 0;
    }
    
    public void check(int[][] rectangle) {
        for(int[] rec : rectangle){
            int min_x = rec[0], min_y = rec[1], max_x = rec[2], max_y = rec[3];
            for(int i = min_x*2; i<= max_x*2; i++) {
                for(int j = min_y*2; j<= max_y*2; j++) {
                    graph[i][j] = 1;
                }
            }
        }
        
        for(int[] rec : rectangle){
            int min_x = rec[0], min_y = rec[1], max_x = rec[2], max_y = rec[3];
            for(int i = min_x*2+1; i< max_x*2; i++) {
                for(int j = min_y*2+1; j< max_y*2; j++) {
                    graph[i][j] = 0;
                }
            }
        }
    }
    
}
