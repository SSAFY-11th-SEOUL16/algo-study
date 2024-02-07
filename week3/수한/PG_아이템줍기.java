package algorithm.programmers;
import java.util.*;

class Item{
    int x,y;
    int dist;
    public Item(int x, int y, int dist){
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

public class Solution_아이템줍기 {        
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        /*
            최대 50 X 50 
            
            경계부분 1, 안쪽 부분 2, 칠해지지 않은 부분 0
            
            만약 현재 직사각형 경계를 칠하다가 그 부분이 이미 2이었다면 경계로 칠하지 않음
            안쪽 부분은 항상 2로 채움
            
            각 격자간 거리가 1이면 경계를 뛰어넘는 경우가 발생
            격자 거리를 2배씩 늘려 계산
        */
        
        int[][] map = new int[102][102];
        
        for(int[] rect : rectangle){
            int x1 = rect[0]*2;
            int y1 = rect[1]*2;
            int x2 = rect[2]*2;
            int y2 = rect[3]*2;
            
            for(int i=x1; i<=x2; i++){
                for(int j=y1; j<=y2; j++){
                    if(i == x1 || i == x2 || j == y1 || j == y2){
                        // 경계 부분                        
                        map[i][j] = map[i][j] != 2 ? 1 : map[i][j];
                    }else{
                        // 안쪽 부분
                        map[i][j] = 2;                        
                    }
                }
            }
        }
        
        int[] dx = {1,0,-1,0};
        int[] dy = {0,1,0,-1}; 
    
        int answer = 0;

        Queue<Item> q = new ArrayDeque<>();
        q.add(new Item(characterX*2,characterY*2,0));
        itemX *= 2;
        itemY *= 2;
        
        boolean[][] visited = new boolean[102][102];
        while(!q.isEmpty()){
        	Item item = q.poll();
            visited[item.x][item.y] = true;

            if(item.x == itemX && item.y == itemY){
                answer = item.dist;                
                break;
            }
            
            for(int i=0; i<4; i++){
                int nx = item.x + dx[i];
                int ny = item.y + dy[i];
                
                if(map[nx][ny] == 1 && !visited[nx][ny]) q.add(new Item(nx,ny,item.dist+1));
            }
        }
        
        return answer / 2;
    }
}