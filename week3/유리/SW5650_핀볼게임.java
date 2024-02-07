import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWE5650 {
	static int n, result;
	static int [][] data;
	static String [][] data2;
	static int [] nr = {-1,1,0,0};
	static int [] nc = {0,0,-1,1};
	static StringTokenizer str;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		for(int i = 1; i <= t; i++) {
			n = Integer.parseInt(br.readLine());
			data = new int[n][n];
			data2 = new String [5][2];
			for(int x = 0; x < n; x++) {
				str = new StringTokenizer(br.readLine());
				for(int y = 0; y <n; y++) {
					int tmp = Integer.parseInt(str.nextToken());
					data[x][y] = tmp;
					if(tmp >=6 && tmp <=10) {
						if(data2[tmp-6][0] == null) {
							data2[tmp-6][0] = ""+x+y;
						}else {
							data2[tmp-6][1] = ""+x+y;
						}
					}
				}
			}
			result = Integer.MIN_VALUE;
			for(int x = 0; x < n; x++) {
				for(int y = 0; y <n; y++) {
					if(data[x][y]== 0) {
						for(int z = 0; z <4; z++) {
							//System.out.print(x + " : " + y + " :" + z);
							//System.out.println("테스트 시작!");
							game(x, y, z);
							//System.out.println(" result : " + result);
						}
					}
				}
			}
			
			System.out.println("#"+i+" "+result);
		}
	}
	
	static void game(int x, int y, int z) {
		int count=0;
		int max = 0;
		int tmpx = x;
		int tmpy = y;
		do{
			//System.out.println(tmpx + " : " + tmpy + " :" + z + " : "+ max);
			if(tmpx >= n || tmpx  < 0 || tmpy >= n || tmpy< 0) {
				tmpx -=nr[z];
				tmpy-=nc[z];
				if(z == 0) {z=1;}
				else if(z == 1) {z=0;}
				else if(z == 2) {z=3;}
				else if(z == 3) {z=2;}
				max++;
			}else {	
				switch(data[tmpx][tmpy]) {
				case 0 :
					tmpx += nr[z];
					tmpy += nc[z];
					break;
				case 1 : 
					if(z == 0) {tmpx -= nr[z]; tmpy -= nc[z]; z=1;}
					else if(z == 1) {z=3; tmpx += nr[z]; tmpy += nc[z];}
					else if(z == 2) {z=0; tmpx += nr[z]; tmpy += nc[z];}
					else if(z == 3) {tmpx -= nr[z]; tmpy -= nc[z]; z=2;}
					max++;
					break;
				case 2 :
					//System.out.println("test2");
					if(z == 0) {z=3; tmpx += nr[z]; tmpy += nc[z];}
					else if(z == 1) {tmpx -= nr[z]; tmpy -= nc[z]; z=0;}
					else if(z == 2) {z=1; tmpx += nr[z]; tmpy += nc[z];}
					else if(z == 3) {tmpx -= nr[z]; tmpy -= nc[z]; z=2;}
					max++;
					break;
				case 3 : 
					if(z == 0) {z=2; tmpx += nr[z]; tmpy += nc[z];}
					else if(z == 1) {tmpx -= nr[z]; tmpy -= nc[z]; z=0;}
					else if(z == 2) {tmpx -= nr[z]; tmpy -= nc[z]; z=3;}
					else if(z == 3) {z=1; tmpx += nr[z]; tmpy += nc[z];}
					max++;
					break;
				case 4 : 
					if(z == 0) {tmpx -= nr[z]; tmpy -= nc[z]; z=1;}
					else if(z == 1) {z=2; tmpx += nr[z]; tmpy += nc[z];}
					else if(z == 2) {tmpx -= nr[z]; tmpy -= nc[z]; z=3;}
					else if(z == 3) {z=0; tmpx += nr[z]; tmpy += nc[z];}
					max++;
					break;
				case 5 : 
					if(z == 0) {tmpx -= nr[z]; tmpy -= nc[z]; z=1;}
					else if(z == 1) {tmpx -= nr[z]; tmpy -= nc[z]; z=0;}
					else if(z == 2) {tmpx -= nr[z]; tmpy -= nc[z]; z=3;}
					else if(z == 3) {tmpx -= nr[z]; tmpy -= nc[z]; z=2;}
					max++;
					break;
				case 6: 
				case 7: 
				case 8: 
				case 9: 
				case 10: 
					String [] tmpString = data2[data[tmpx][tmpy] -6][0].split("");
					int tmpx2 = Integer.parseInt(tmpString[0]);
					int tmpy2 = Integer.parseInt(tmpString[1]);
					//System.out.println("웜홀 " + tmpx2 + " : " + tmpy2);
					if(tmpx == tmpx2 && tmpy==tmpy2) {
						tmpString = data2[data[tmpx][tmpy] -6][1].split("");
						tmpx = Integer.parseInt(tmpString[0]);
						tmpy = Integer.parseInt(tmpString[1]);
					}else {
						tmpx = tmpx2;
						tmpy = tmpy2;
					}
					tmpx += nr[z];
					tmpy += nc[z];
					
					break;
				}
				
			}
			
			//System.out.println(tmpx + " : " + tmpy + " :" + z + " : " + max);
			//System.out.println("------------------------------");
			if((tmpx < n && tmpx  >= 0 && tmpy < n && tmpy>= 0 && data[tmpx][tmpy] == -1) || (tmpx == x && tmpy ==y)) {
				//System.out.println("끝났어용~~!!");
				break;
			}
			count ++;
		}while(count < 50);
		//}while(true);
		
		if(max > result) {
			result = max;
			//System.out.println("result : " + result + "----------------------------------------");
		}
	}
}
