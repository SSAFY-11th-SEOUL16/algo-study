import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int n,m,c, result, tmpResult2_1;
	static int [][]data;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		
		int t = Integer.parseInt(br.readLine());
		for(int i = 1; i <=t; i++) {
			str = new StringTokenizer(br.readLine());
			n = Integer.parseInt(str.nextToken());
			m = Integer.parseInt(str.nextToken());
			c = Integer.parseInt(str.nextToken());
			result = 0;
			data = new int [n][n];
			int firstX = 0;
			int firstY = 0;
			
			for(int x = 0; x < n; x++) {
				str = new StringTokenizer(br.readLine());
				for(int y = 0; y < n; y++) {
					data[x][y] = Integer.parseInt(str.nextToken());
				}
			}
			
			for(int z = 0; z <2; z++) {
				tmpResult2_1=0;
				int tmpX=0, tmpY=0, tmpResult=0;
				for(int x = 0; x < n; x++) {
					if(result != 0 && n/2 < m && x == firstX) {continue;}
                    if(result != 0 && x == firstX && n/2 == m && firstY != 0 && firstY != m) {continue;}
					//if(result != 0 && x == firstX) {continue;}
					int tmpResult2 = 0;
					int tmpResult3 = 0;
					boolean check3 = true;
					if(result != 0 && x == firstX && 0 == firstY) {
						for(int y = 0+m; y <m+m; y++) {
							tmpResult2+=(data[x][0+y]*data[x][0+y]);
							tmpResult3+=data[x][0+y];
						}
						
						if(tmpResult3  > c) {	
							getHoney(0,x,0+m,0,0);
							check3 = false;
						}
					}
					else {
						for(int y = 0; y <m; y++) {
							tmpResult2+=(data[x][0+y]*data[x][0+y]);
							tmpResult3+=data[x][0+y];
						}
						
						if(tmpResult3  > c) {	
							getHoney(0,x,0,0,0);
							check3 = false;
						}
					}
					
					if(tmpResult2 > tmpResult && check3) {
						tmpX = x;
						tmpY =0;
						tmpResult = tmpResult2;
					}
					if(tmpResult2_1 > tmpResult && !check3) {
						tmpX = x;
						tmpY =0;
						tmpResult = tmpResult2_1;
					}
					
					for(int y = 1; y <= n-m; y++) {
						if(result!=0 && firstY+m-1 >= y && x == firstX) {continue;}
						check3 = true;
						tmpResult2 = tmpResult2 - (data[x][y-1]*data[x][y-1]) + (data[x][y+m-1]*data[x][y+m-1]);
						tmpResult3 = tmpResult3 - data[x][y-1] + data[x][y+m-1];

						if(tmpResult3  > c) {		
							getHoney(0,x,y,0,0);
							check3 = false;
						}
						
						if(tmpResult2 > tmpResult && check3) {
							tmpX = x;
							tmpY =y;
							tmpResult = tmpResult2;
						}
						
						if(tmpResult2_1 > tmpResult && !check3) {
							tmpX = x;
							tmpY =y;
							tmpResult = tmpResult2_1;
						}
					}
				}
				
				if(z == 0) {
					firstX = tmpX;
					firstY=tmpY;
					result+=tmpResult;
				}else {
					result+=tmpResult;
				}
			}
			
			System.out.println("#"+i+" "+result);
		}
	}
	static void getHoney(int count, int StartX, int StartY, int getHoneyResult, int getHoneyResult2) {
		if(getHoneyResult > c) {return;}
		if(count == m) {
			if(tmpResult2_1 < getHoneyResult2) {
				tmpResult2_1 = getHoneyResult2;
			}
			return;
		}
		

		for(int y = StartY; y <=StartY; y++ ) {
			getHoney(count+1, StartX, y+1, getHoneyResult+data[StartX][y], getHoneyResult2+(data[StartX][y]*data[StartX][y]));
			getHoney(count+1, StartX, y+1, getHoneyResult, getHoneyResult2);
		}
	}
}
