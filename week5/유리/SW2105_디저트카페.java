import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int [] nr = {-1,1,0,0};
	static int [] nc = {0,0,-1,1};
	static int n, result;
	static int [][] data;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		int t = Integer.parseInt(br.readLine());
		for(int i = 1; i<=t; i++) {
			n = Integer.parseInt(br.readLine());
			data = new int[n][n];
			result = 0;
			for(int x = 0; x <n; x++) {
				str = new StringTokenizer(br.readLine());
				for(int y = 0; y <n; y++) {
					data[x][y] = Integer.parseInt(str.nextToken());
				}
			}
			
			for(int x = 0; x <n-2; x++) {
				for(int y = 1; y <= n-2; y++) {
					//System.out.println(x+"/"+y+"-----------------------------------");
					cal(x,y);
					//System.out.println("x : "+x+" y : "+y+" result : " + result);
				}
				//System.out.println("--------------------------------------------");
			}
			if(result == 0) {
				System.out.println("#"+i+" -1");
			}else {
				System.out.println("#"+i+" "+result);
			}
		}
	}
	
	static void cal(int x, int y) {
		//System.out.println("처음선 작업!");
		String tmp = "/"+data[x][y]+"/";
		int tmpResult = 0;
		for(int k = 1; k <= n-2; k++) {
			int tmpX = x+k;
			int tmpY = y+k;
			if(tmpX < 0 || tmpX > n-1 || tmpY <0 || tmpY > n-1 || tmp.contains("/"+data[tmpX][tmpY]+"/")) {
				//System.out.println(tmpX + "/" + tmpY + "/ return"+"/"+x+"/"+y+"/"+ tmpResult);
				return;
			}else {
				//System.out.println(tmpX + "/" + tmpY + "/*" + tmp + "*/" + data[tmpX][tmpY]+"/"+x+"/"+y +"/"+ tmpResult);
				cal2(tmpX,tmpY,tmp+=+data[tmpX][tmpY]+"/", x, y, ++tmpResult);
			}
		}
	}
	
	static void cal2(int x, int y, String tmp,  int arrX, int arrY, int tmpResult) {
		//System.out.println("두번째선 작업!");
		for(int k = 1; k <= n-2; k++) {
			int tmpX = x+k;
			int tmpY = y-k;
			if(tmpX < 0 || tmpX > n-1 || tmpY <0 || tmpY > n-1 || tmp.contains("/"+data[tmpX][tmpY]+"/")) {
				//System.out.println(tmpX + "/" + tmpY + "/ return"+" /"+arrX+"/"+arrY+"/"+ tmpResult);
				return;
			}else {
				//System.out.println(tmpX + "/" + tmpY + "/*" + tmp + "*/" + data[tmpX][tmpY]+"/"+arrX+"/"+arrY+"/"+ tmpResult);
				cal3(tmpX,tmpY,tmp+=data[tmpX][tmpY]+"/", arrX, arrY, ++tmpResult);
			}
		}
	}
	
	static void cal3(int x, int y, String tmp, int arrX, int arrY, int tmpResult) {
		//System.out.println("세번째선 작업!");
		for(int k = 1; k <= n-2; k++) {
			int tmpX = x-k;
			int tmpY = y-k;
			if(tmpX < 0 || tmpX > n-1 || tmpY <0 || tmpY > n-1 || tmp.contains("/"+data[tmpX][tmpY]+"/")) {
				//System.out.println(tmpX + "/" + tmpY + "/ return"+"/"+arrX+"/"+arrY+"/"+ tmpResult);
				return;
			}else {
				//System.out.println(tmpX + "/" + tmpY + "/*" + tmp + "*/" + data[tmpX][tmpY]+"/"+arrX+"/"+arrY+"/"+ tmpResult);
				cal4(tmpX,tmpY,tmp+=data[tmpX][tmpY]+"/", arrX, arrY, ++tmpResult);
			}
		}
	}
	
	static void cal4(int x, int y, String tmp, int arrX, int arrY, int tmpResult) {
		//System.out.println("마지막선 작업!");
		for(int k = 1; k <= n-2; k++) {
			int tmpX = x-k;
			int tmpY = y+k;
			if(tmpX < 0 || tmpX > n-1 || tmpY <0 || tmpY > n-1) {
				//System.out.println(tmpX + "/" + tmpY + "/ return"+"/"+arrX+"/"+arrY+"/"+ tmpResult);
				return;
			}else {
				//System.out.println(tmpX + "/" + tmpY + "/*" + tmp + "*/" + data[tmpX][tmpY] +"/"+arrX+"/"+arrY+"/"+ tmpResult);
				//System.out.println("--------------------------------------------");
				if(tmpX == arrX && tmpY == arrY) {
					//System.out.println(tmpResult);
					//System.out.println("돌고돌아 도착 : "+ tmpResult);
					if(result<++tmpResult) {
						result = tmpResult;
					}
					return;
				}
				if(tmp.contains("/"+data[tmpX][tmpY]+"/")) {return;}
				tmp+=data[tmpX][tmpY]+"/";
				tmpResult++;
			}
		}
	}
}
