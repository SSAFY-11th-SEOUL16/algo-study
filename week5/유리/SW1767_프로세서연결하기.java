import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    static int n, result, countCell, resultCount;
    static int [][] data;
    static boolean [][] check;
    static int [] nr = {-1,1,0,0};
    static int [] nc = {0,0,-1,1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str;
         
        int t = Integer.parseInt(br.readLine());
        for(int i = 1; i <= t; i++) {
            n = Integer.parseInt(br.readLine());
            data = new int [12][2];
            check = new boolean[n][n];
            result = Integer.MAX_VALUE;
            countCell = 0;
            resultCount=0;
            for(int x = 0; x < n; x++) {
                str = new StringTokenizer(br.readLine());
                for(int y = 0; y < n; y++) {
                    int tmp = Integer.parseInt(str.nextToken());
                    if(tmp == 1) {
                        check[x][y] = true;
                        if(x == 0 || y == 0 || x == n-1 || y == n-1) {
                            continue;
                        }
                        data[countCell][0] = x;
                        data[countCell++][1] = y;
                    }
                }
            }
             
            if(countCell == 0) {
                System.out.println("#"+i+" "+0);
            }
            else {
                cal(0,0,0);
                System.out.println("#"+i+" "+result);
            }
        }
    }
     
    static void cal(int tmpResult, int startNum, int tmpCount) {
        //System.out.println(tmpResult + "/" +startNum + "/" + tmpCount + "/" + result + "/" + resultCount);
        if(startNum ==  countCell) {    
            if(tmpResult > 0 && (result > tmpResult && resultCount <= tmpCount) || resultCount < tmpCount) {
                result = tmpResult;
                resultCount = tmpCount;
            }
            return;
        }
         
        for(int x = 0; x < 5; x++) {
            boolean check2;
            int tmp = 0;
            int tmp2;;
            if(x == 0) {
                check2 = true;
                tmp2=0;
                for(int y = data[startNum][0]-1; y >=0; y--) {
                    if(check[y][data[startNum][1]]) {
                        tmp2 = y+1;
                        check2=false;
                        break;
                    }else {
                        check[y][data[startNum][1]] = true;
                        tmp++;
                    }
                }
                 
                if(check2) {
                    cal(tmpResult+tmp, startNum+1, tmpCount+1);
                }
                for(int y = tmp2; y < data[startNum][0]; y++) {
                    check[y][data[startNum][1]] = false;
                }
            }else if(x == 1) {
                check2 = true;
                tmp2 = n-1;
                for(int y = data[startNum][0]+1; y <=n-1; y++) {
                    if(check[y][data[startNum][1]]) {
                        check2=false;
                        tmp2 = y-1;
                        break;
                    }else {
                        check[y][data[startNum][1]] = true;
                        tmp++;
                    }
                }
                 
                if(check2) {
                    cal(tmpResult+tmp, startNum+1, tmpCount+1);
                }
                for(int y = tmp2; y > data[startNum][0]; y--) {
                    check[y][data[startNum][1]] = false;
                }
            }else if(x == 2) {
                tmp2=0;
                check2 = true;
                for(int y = data[startNum][1]-1; y >=0; y--) {
                    if(check[data[startNum][0]][y]) {
                        check2=false;
                        tmp2=y+1;
                        break;
                    }else {
                        check[data[startNum][0]][y] = true;
                        tmp++;
                    }
                }
                 
                if(check2) {
                    cal(tmpResult+tmp, startNum+1, tmpCount+1);
                }
                for(int y = tmp2; y <=data[startNum][1]-1; y++) {
                    check[data[startNum][0]][y] = false;
                }
            }else if(x == 3) {
                tmp2=n-1;
                check2 = true;
                for(int y = data[startNum][1]+1; y <=n-1; y++) {
                    if(check[data[startNum][0]][y]) {
                        check2=false;
                        tmp2=y-1;
                        break;
                    }else {
                        check[data[startNum][0]][y] = true;
                        tmp++;
                    }
                }
                 
                if(check2) {
                    cal(tmpResult+tmp, startNum+1, tmpCount+1);
                }
                for(int y = tmp2; y >data[startNum][1]; y--) {
                    check[data[startNum][0]][y] = false;
                }
            }else{
                cal(tmpResult, startNum+1, tmpCount);
            }
             
        }
    }
}
