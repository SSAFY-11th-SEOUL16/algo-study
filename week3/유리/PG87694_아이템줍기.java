class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        boolean [][] data = new boolean[50][50];
        for(int i = 0; i < rectangle.length; i++){
            for(int x = rectangle[i][0]; x < rectangle[i][2]; x++){
                for(int y = rectangle[i][1]; y < rectangle[i][3]; y++){
                    data[x][y] = true;
                } 
            }  
        }
        
        int [] nc = {-1,1,0,0};
        int [] nr = {0,0,-1,1};
        int [] check = {1,0,3,2};
        int direc = -1;
        
        while(characterX!=itemX || characterY!=itemY){
            int tmpx;
            int tmpy;
            for(int i = 0; i<4;){
                if(direc != -1 && check[direc] == i){continue;}
                tmpx = characterX+nc[i];
                tmpy = characterY+nr[i];
                if(tmpx >=0 && tmpx < 50 && tmpy >=0 && tmpy <50 && data[tmpx][tmpy]){
                    answer++;
                    while(data[tmpx][tmpy]){
                        tmpx +=nc[i];
                        tmpy +=nr[i];
                        answer++;
                    }
                    direc = i;
                    characterX = tmpx - nc[i];
                    characterY = tmpy - nr[i];
                    answer--;
                    break;
                }
            }
        }
        
        
        return answer;
    }
}
