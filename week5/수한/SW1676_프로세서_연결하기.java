import java.util.Scanner;
import java.util.*;

class Solution
{
    public static int[][] map;
    public static int[][] cell;
    public static int N;
    public static int answer;
    public static int maxCell;
    public static int curMaxCell;
    public static int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    
    public static void dfs(int c, int total, int count){
        if(count > curMaxCell){
        	answer = total;
            curMaxCell = count;
        }else if(count == curMaxCell){
            answer = answer > total ? total : answer;
        }
        
    	if(c == maxCell) return;
        
        int x = cell[c][0];
        int y = cell[c][1];
        for(int i=0; i<4; i++){
            boolean flag = false;
            int nx = x;
            int ny = y;
            int len = 0;
            nx += dirs[i][0];
            ny += dirs[i][1];
            while(nx >= 0 && nx < N && ny >= 0 && ny < N){
                if(map[nx][ny] == 1) {
                    flag = true;
                    break;
                }
                map[nx][ny] = 1;
                nx += dirs[i][0];
                ny += dirs[i][1];
                len++;
            }
            if(!flag) dfs(c+1,total+len,count+1);
            nx -= dirs[i][0];
            ny -= dirs[i][1];
            while((dirs[i][0] == 0 || nx != x) && (ny != y || dirs[i][1] == 0)){
                map[nx][ny] = 0;
                nx -= dirs[i][0];
                ny -= dirs[i][1];
            }
            if(flag) dfs(c+1,total,count);
        }
	}
    
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
        {
	        answer = 0;
			N = sc.nextInt();
            map = new int[N][N]; 
            cell = new int[N*N][2];
            maxCell=0;
            curMaxCell = 0;
            for(int i=0; i<N; i++){
	            for(int j=0; j<N; j++){
                    map[i][j] = sc.nextInt();
                    if(map[i][j]==1 &&  i !=0 && i != N-1 && j !=0 && j != N-1 ) cell[maxCell++] = new int[]{i,j};
    	        }
            }
            
            dfs(0,0,0);
            
            System.out.println("#"+test_case+" "+answer);
        }
	}
}