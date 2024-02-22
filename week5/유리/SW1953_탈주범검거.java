import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int n,m,r,c,l, result;
	static int [][] data;
	static int [][] data2;
	static boolean [][] check;
	static int [] nr = {-1,1,0,0};
	static int [] nc = {0,0,-1,1};	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		
		int t = Integer.parseInt(br.readLine());
		for(int i = 1; i <= t; i++) {
			str = new StringTokenizer(br.readLine());
			n = Integer.parseInt(str.nextToken());
			m = Integer.parseInt(str.nextToken());
			r = Integer.parseInt(str.nextToken());
			c = Integer.parseInt(str.nextToken());
			l = Integer.parseInt(str.nextToken());
			result = 0;
			data = new int[n][m];
			data2 = new int[n][m];
			check = new boolean[n][m];
			for(int x = 0; x < n; x++) {
				str = new StringTokenizer(br.readLine());
				for(int y = 0; y <m; y++) {
					data[x][y] = Integer.parseInt(str.nextToken());
					data2[x][y] = l;
				}
			}
			data2[r][c] =1;
			cal(1,r,c);
			int test = 0;
			/**
			for(int x2 = 0; x2 < n; x2++) {
				for(int y2 = 0; y2 <m; y2++) {
					//System.out.print(check[x2][y2]+"/"+data[x2][y2]+"\t");
					if(check[x2][y2]) {
						////System.out.println(x2 +"/"+y2);
						test++;
					}
				}
				//System.out.println();
			}
			**/
			System.out.println("#"+i+" "+result);
			//System.out.println("#"+i+" "+test);
		}
	}
	
	static void cal(int count, int x, int y) {
		if(count == l+1) {return;}
		
		if(!check[x][y]) {
			result++;
			check[x][y]=true;
		}
		/**
		for(int x2 = 0; x2 < n; x2++) {
			for(int y2 = 0; y2 <m; y2++) {
				//System.out.print(check[x2][y2]+"/"+data[x2][y2]+"\t");
			}
			//System.out.println();
		}
		**/
		////System.out.println(count +"/"+result+ "/"+x+"/"+y+"---------------------------------------");
		switch(data[x][y]) {
		case 1 : 
			for(int i = 0; i <4; i++) {
				int tmpX = x+nr[i];
				int tmpY = y+nc[i];
				if(tmpX==23 && tmpY==14) {
					//System.out.println("test6 : "+data[tmpX][tmpY] + "/"+tmpX +"/"+ tmpY);
				}
				if(tmpX >= 0 && tmpX < n && tmpY >=0 && tmpY < m  && data[tmpX][tmpY] > 0 && (data2[tmpX][tmpY] > count || check[tmpX][tmpY] == false)) {
					if(i == 0 && (data[tmpX][tmpY] == 3 || data[tmpX][tmpY] == 4 || data[tmpX][tmpY] == 7)) {continue;}
					if(i == 1 && (data[tmpX][tmpY] == 3 || data[tmpX][tmpY] == 5 || data[tmpX][tmpY] == 6)) {continue;}
					if(i == 2 && (data[tmpX][tmpY] == 2 || data[tmpX][tmpY] == 6 || data[tmpX][tmpY] == 7)) {continue;}
					if(i == 3 && (data[tmpX][tmpY] == 2 || data[tmpX][tmpY] == 4 || data[tmpX][tmpY] == 5)) {continue;}
					////System.out.println("1번 : "+i+"/"+tmpX + " / " + tmpY + "/" + data[tmpX][tmpY] + "/"+count);
					data2[tmpX][tmpY] = count;
					cal(count+1, tmpX, tmpY);
				}
			}
			break;
		case 2 :
			for(int i = 0; i <2; i++) {
				int tmpX = x+nr[i];
				int tmpY = y+nc[i];
				if(tmpX==23 && tmpY==14) {
					//System.out.println("test1=5 : "+data[tmpX][tmpY] + "/"+tmpX +"/"+ tmpY);
				}
				if(tmpX >= 0 && tmpX < n && tmpY >=0 && tmpY < m  && data[tmpX][tmpY] !=0 && data[tmpX][tmpY] !=3 && (data2[tmpX][tmpY] > count || check[tmpX][tmpY] == false)) {
					if(i == 0 && (data[tmpX][tmpY] == 4 || data[tmpX][tmpY] == 7)) {continue;}
					if(i == 1 && (data[tmpX][tmpY] == 5 || data[tmpX][tmpY] == 6)) {continue;}
					data2[tmpX][tmpY] = count;
					cal(count+1, tmpX, tmpY);
				}
			}
			break;
		case 3 :
			for(int i = 2; i <4; i++) {
				int tmpX = x+nr[i];
				int tmpY = y+nc[i];
				if(tmpX==23 && tmpY==14) {
					//System.out.println("test4 : "+data[tmpX][tmpY] + "/"+tmpX +"/"+ tmpY);
				}
				if(tmpX >= 0 && tmpX < n && tmpY >=0 && tmpY < m && data[tmpX][tmpY] != 0 && data[tmpX][tmpY] != 2  && (data2[tmpX][tmpY] > count || check[tmpX][tmpY] == false)) {
					if(i == 2 && (data[tmpX][tmpY] == 6 || data[tmpX][tmpY] == 7)) {continue;}
					if(i == 3 && (data[tmpX][tmpY] == 4 || data[tmpX][tmpY] == 5)) {continue;}
					data2[tmpX][tmpY] = count;
					cal(count+1, tmpX, tmpY);
				}
			}
			break;
		case 4 :
			for(int i = 0; i <4; i+=3) {
				int tmpX = x+nr[i];
				int tmpY = y+nc[i];
				if(tmpX==23 && tmpY==14) {
					//System.out.println("test3 : "+data[tmpX][tmpY] + "/"+tmpX +"/"+ tmpY);
				}
				if(tmpX >= 0 && tmpX < n && tmpY >=0 && tmpY < m && data[tmpX][tmpY] != 0 && data[tmpX][tmpY] != 4  && (data2[tmpX][tmpY] > count || check[tmpX][tmpY] == false)) {
					if(i == 0 && (data[tmpX][tmpY] == 3 || data[tmpX][tmpY] == 7)) {continue;}
					if(i == 3 && (data[tmpX][tmpY] == 2 || data[tmpX][tmpY] == 5)) {continue;}
					data2[tmpX][tmpY] = count;
					cal(count+1, tmpX, tmpY);
				}
			}
			break;
		case 5 :
			for(int i = 1; i <4; i+=2) {
				int tmpX = x+nr[i];
				int tmpY = y+nc[i];
				if(tmpX==23 && tmpY==14) {
					//System.out.println("test2 : "+data[tmpX][tmpY] + "/"+tmpX +"/"+ tmpY);
				}
				if(tmpX >= 0 && tmpX < n && tmpY >=0 && tmpY < m && data[tmpX][tmpY] != 0 && data[tmpX][tmpY] != 5  && (data2[tmpX][tmpY] > count || check[tmpX][tmpY] == false)) {
					if(i == 1 && (data[tmpX][tmpY] == 3 || data[tmpX][tmpY] == 6)) {continue;}
					if(i == 3 && (data[tmpX][tmpY] == 2 || data[tmpX][tmpY] == 4)) {continue;}
					data2[tmpX][tmpY] = count;
					cal(count+1, tmpX, tmpY);
				}
			}
			break;
		case 6 :
			for(int i = 1; i <3; i++) {
				int tmpX = x+nr[i];
				int tmpY = y+nc[i];
				if(tmpX==23 && tmpY==14) {
					//System.out.println("test1 : "+data[tmpX][tmpY] + "/"+tmpX +"/"+ tmpY);
				}
				if(tmpX >= 0 && tmpX < n && tmpY >=0 && tmpY < m && data[tmpX][tmpY] != 0 && data[tmpX][tmpY] != 6  && (data2[tmpX][tmpY] > count || check[tmpX][tmpY] == false)) {
					if(i == 1 && (data[tmpX][tmpY] == 3 || data[tmpX][tmpY] == 5)) {continue;}
					if(i == 2 && (data[tmpX][tmpY] == 2 || data[tmpX][tmpY] == 7)) {continue;}
					data2[tmpX][tmpY] = count;
					cal(count+1, tmpX, tmpY);
				}
			}
			break;
		case 7 :
			for(int i = 0; i <3; i+=2) {
				int tmpX = x+nr[i];
				int tmpY = y+nc[i];
				if(tmpX==23 && tmpY==14) {
					//System.out.println(data[tmpX][tmpY] + "/"+tmpX +"/"+ tmpY);
				}
				if(tmpX >= 0 && tmpX < n && tmpY >=0 && tmpY < m && data[tmpX][tmpY] != 0 && data[tmpX][tmpY] != 7  && (data2[tmpX][tmpY] > count || check[tmpX][tmpY] == false)) {
					if(i == 0 && (data[tmpX][tmpY] == 3 || data[tmpX][tmpY] == 4)){continue;}
					if(i == 2 && (data[tmpX][tmpY] == 2 || data[tmpX][tmpY] == 6)) {continue;}
					//System.out.println("test : "+i+"/"+data[tmpX][tmpY] + "/"+tmpX +"/"+ tmpY);
					data2[tmpX][tmpY] = count;
					cal(count+1, tmpX, tmpY);
				}
			}
			break;
		}
	}
}
