import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    static int [][] maxValueLoc;
    static int [][] data;
    static boolean [][] check;
    static int [] nr = {-1,1,0,0};
    static int [] nc = {0,0,-1,1};
    static int n, k, maxCount,result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str;
         
        int t = Integer.parseInt(br.readLine());
        for(int i = 1; i <=t; i++) {
            str = new StringTokenizer(br.readLine());
            n = Integer.parseInt(str.nextToken());
            k = Integer.parseInt(str.nextToken());
            data = new int[n][n];
            check = new boolean[n][n];
            int max = 0;
            maxCount = 0;
            result = 0;
            maxValueLoc = new int [5][2];
            for(int x = 0; x < n; x++) {
                str = new StringTokenizer(br.readLine());
                for(int y = 0; y < n; y++) {
                    data[x][y] = Integer.parseInt(str.nextToken());
                    if(data[x][y] > max) {
                        max = data[x][y];
                        maxValueLoc[0][0] = x;
                        maxValueLoc[0][1] = y;
                        maxCount = 1;
                    }
                    else if(data[x][y] == max) {
                        maxValueLoc[maxCount][0] = x;
                        maxValueLoc[maxCount][1] = y;
                        maxCount++;
                    }
                }
            }
             
            /**
            System.out.println(maxCount);
            for(int i2 = 0; i2 < 5; i2++) {
                System.out.println(maxValueLoc[i2][0] + "/"+maxValueLoc[i2][1]);
            }
            **/
            for(int x = 0; x < maxCount; x++) {
                check[maxValueLoc[x][0]][maxValueLoc[x][1]] = true;
                cal(1,maxValueLoc[x][0],maxValueLoc[x][1], max, false);
                check[maxValueLoc[x][0]][maxValueLoc[x][1]] = false;
            }
            System.out.println("#"+i+" "+result);
        }
    }
     
    static void cal(int count, int x, int y, int tmpValue, boolean use) {
        //System.out.println(count + "/" + x+ "/"+y+"/"+tmpValue+"/"+use+"/"+result);
        boolean check2 = true;
        for(int i = 0; i < 4; i++) {
            int tmpX = x + nr[i];
            int tmpY = y + nc[i];
             
            if(tmpX >=0 && tmpX < n && tmpY >=0 && tmpY <n && !(check[tmpX][tmpY])) {
                if(tmpValue > data[tmpX][tmpY]) {
                    check2 = false;
                    check[tmpX][tmpY] = true;
                    cal(count+1, tmpX, tmpY, data[tmpX][tmpY], use);
                    check[tmpX][tmpY] = false;
                }else {
                    if(use) {
                        continue;
                    }else {
                        if( tmpValue <= data[tmpX][tmpY]-k) {
                            continue;
                        }
                        else {
                            check2=false;
                            check[tmpX][tmpY] = true;
                            cal(count+1, tmpX, tmpY, tmpValue-1, true);
                            check[tmpX][tmpY] = false;
                        }
                    }
                }
            }
        }
         
        if(check2 && result < count) {
            result = count;
        }
    }
}
