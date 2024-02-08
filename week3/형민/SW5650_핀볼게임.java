import java.io.*;
import java.util.*;

class Solution {
    static int n;
    static int arr[][];
    static int result;
    //상 하 좌 우
  	static int rx[] = {-1, 1, 0, 0};
  	static int ry[] = {0, 0, -1, 1};
  	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
	    for (int i = 0; i < t; i++) {
	        result = Integer.MIN_VALUE;
	        
	        //게임판 입력
	        n = Integer.parseInt(br.readLine());
	        arr = new int[n][n];
	
	        for (int j = 0; j < n; j++) {
	        	String str = br.readLine().trim();
	            st = new StringTokenizer(str);
	            for (int k = 0; k < n; k++) {
	                arr[j][k] = Integer.parseInt(st.nextToken());
	            }
	        }
	
	        //출발할 수 있는 위치에서 출발
	        for (int j = 0; j < n; j++) {
	            for (int k = 0; k < n; k++) {
	                if (arr[j][k] != 0) continue;
	                //상,하,좌,우 방향 선택
	                for (int h = 0; h < 4; h++) {
	                    int nx = j;
	                    int ny = k;
	                    int di = h;
	                    int score = 0;
	                    System.out.println(nx + " " + ny);
	                    while(true) {
	                    	//다음 칸 지정
	                    	nx = nx + rx[di]; // -1 1
	                	    ny = ny + ry[di];
	                	    boolean scoreFlag = false; //점수 증가 플래그
//	                	    System.out.println(nx + " " + ny);
	                	    //벽을 만난 경우
	                	    if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
	                	        scoreFlag = true;
	                	        nx -= rx[di]; ny -= ry[di]; //되돌아오기
	                	        if (di % 2 == 1) di -= 1; 
	                	        else di += 1;
	                	    }
	                	    
	                	    //게임종료
	                	    if((nx == j && ny == k) || arr[nx][ny] == -1) {
	                	    	break;
	                	    }
	                	    
	                	    if (arr[nx][ny] == 1) {
	                	        scoreFlag = true;
	                	        switch (di) {
	                	            case 0:
	                	                nx -= rx[di]; ny -= ry[di];
	                	                di = 1; break;
	                	            case 1:
	                	                di = 3; break;
	                	            case 2:
	                	                di = 0; break;
	                	            case 3:
	                	            	nx -= rx[di]; ny -= ry[di];
	                	                di = 2; break;
	                	        }
	                	    } else if (arr[nx][ny] == 2) {
	                	        scoreFlag = true;
	                	        switch (di) {
	                	            case 0:
	                	                di = 3; break;
	                	            case 1:
	                	            	nx -= rx[di]; ny -= ry[di];
	                	                di = 0; break;
	                	            case 2:
	                	                di = 1; break;
	                	            case 3:
	                	            	nx -= rx[di]; ny -= ry[di];
	                	                di = 2; break;
	                	        }
	                	    } else if (arr[nx][ny] == 3) {
	                	        scoreFlag = true;
	                	        switch (di) {
	                	            case 0:
	                	                di = 2; break;
	                	            case 1:
	                	            	nx -= rx[di]; ny -= ry[di];
	                	                di = 0; break;
	                	            case 2:
	                	            	nx -= rx[di]; ny -= ry[di];
	                	                di = 3; break;
	                	            case 3:
	                	                di = 1; break;
	                	        }
	                	    } else if (arr[nx][ny] == 4) {
	                	        scoreFlag = true;
	                	        switch (di) {
	                	            case 0:
	                	            	nx -= rx[di]; ny -= ry[di];
	                	                di = 1; break;
	                	            case 1:
	                	                di = 2; break;
	                	            case 2:
	                	            	nx -= rx[di]; ny -= ry[di];
	                	                di = 3; break;
	                	            case 3:
	                	                di = 0; break;
	                	        }
	                	    } else if (arr[nx][ny] == 5) {
	                	        scoreFlag = true;
	                	        nx -= rx[di]; ny -= ry[di];
	                	        if (di % 2 == 1) di -= 1;
	                	        else di += 1;
	                	    } else if(6  <= arr[nx][ny] && arr[nx][ny] <= 10){ //웜홀을 만난 경우
	                	        int worm = arr[nx][ny];
	                	
	                	        boolean flag = false;
	                	        for (int z = 0; z < n; z++) {
	                	            for (int u = 0; u < n; u++) {
	                	                if(z == nx && u == ny) continue; //현재 위치 웜홀은 패스
	                	                if(arr[z][u] == worm) {
	                	                	nx = z;
	                	                    ny = u;
	                	                    flag = true;
	                	                    break;
	                	                }
	                	            }
	                	            if(flag) break;
	                	        }
	                	    }
	                	
	                	    if(scoreFlag) score++;
	                    }
	                    
	                    result = Math.max(result, score);
	                }
	            }
	        }
	    }
	
	    System.out.println(result);
    }

}

