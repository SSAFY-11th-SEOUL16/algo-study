import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int n,w,h,result, count;
	static int [][] data;
	static int [][] tmp;
	static int [] locList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		
		int t = Integer.parseInt(br.readLine());
		for(int i = 1; i <= t; i++) {
			str = new StringTokenizer(br.readLine());
			n = Integer.parseInt(str.nextToken());
			w = Integer.parseInt(str.nextToken());
			h = Integer.parseInt(str.nextToken());
			
			data = new int [h][w];
			tmp = new int [h][w];
			locList = new int [n];
			
			count = 0;
			for(int x = 0; x < h; x++) {
				str = new StringTokenizer(br.readLine());
				for(int y = 0; y < w; y++) {
					//System.out.println(x+"/"+y);
					data[x][y] = Integer.parseInt(str.nextToken());
					if(data[x][y] != 0) {
						count++;
					}
				}
			}
			result = count;
			cal(0);
			System.out.println("#"+i+" "+result);
		}
	}
	
	static void cal(int count) {
		int tmpResult = 0;
		if(count == n) {
			for(int x = 0; x < h; x++) {
				for(int y = 0; y < w; y++) {
					tmp[x][y] = data[x][y];
				}
			}
			/**
			locList[0] = 3;
			locList[1] = 2;
			locList[2] = 1;
			locList[3] = 1;
			**/
			for(int i = 0; i < n; i++) {
				for(int x = 0; x < h; x++) {
					//System.out.println(tmp[x][locList[i]]);
					if(tmp[x][locList[i]] != 0) {
						//System.out.println("0이 아닙니다!!!!!!");
						if(tmp[x][locList[i]] == 1) {
							tmp[x][locList[i]] = 0;
						}else {
							int power = tmp[x][locList[i]];
							tmp[x][locList[i]] = 0;
							//System.out.println("블록 제거"+x+"/"+locList[i]+"/"+power);
							removeBrick(x, locList[i], power);
						}
						break;
					}
				}
				//블록 정리
				for(int y = 0; y < w; y++) {
					for(int x = h-1; x >= 0; x--) {
						if(tmp[x][y] == 0) {
							int tmpX = x -1;
							boolean check = true;;
							while(tmpX >= 0) {
								if(tmp[tmpX][y] != 0) {
									tmp[x][y] = tmp[tmpX][y];
									tmp[tmpX][y] = 0;
									check = false;
									break;
								}
								tmpX--;
							}
							if(check) {
								break;
							}
							else if(i+1 == n){
								tmpResult++;
								if(tmpResult > result) {
									return;
								}
							}
						}
						else if(i+1 == n){
							tmpResult++;
							if(tmpResult > result) {
								return;
							}
						}
						
					}
				}
				/**
				for(int x = 0; x < h; x++) {
					for(int y = 0; y < w; y++) {
						System.out.print(tmp[x][y]+"\t");
					}
					System.out.println();
				}
				System.out.println("-------------------------------");
				**/
			}
			
			if(tmpResult < result) {
				result = tmpResult;
			}
			
			return;
		}
		
		/**/
		for(int y = 0; y <w; y++) {
			if(result != 0) {
				locList[count] = y;
				cal(count+1);
			}
		}
		/**/
	}
	
	static void removeBrick(int x, int y, int power) {
		int tmpPower;
		for(int i = 1; i < power; i++) {
			if(x-i >= 0) {
				//System.out.println(x-i+"/"+y);
				if(tmp[x-i][y] > 1) {
					tmpPower = tmp[x-i][y];
					tmp[x-i][y] = 0;
					removeBrick(x-i, y, tmpPower);
				}else if(tmp[x-i][y] == 1) {
					tmp[x-i][y] = 0;
				}
			}
			
			if(x+i < h) {
				//System.out.println(x+i+"/"+y);
				if(tmp[x+i][y] > 1) {
					tmpPower = tmp[x+i][y];
					tmp[x+i][y] = 0;
					removeBrick(x+i, y, tmpPower);
				}else if(tmp[x+i][y] == 1) {
					tmp[x+i][y] = 0;
				}
			}
			
			if(y-i >= 0) {
				if(tmp[x][y-i] > 1) {
					tmpPower = tmp[x][y-i];
					tmp[x][y-i] = 0;
					removeBrick(x, y-i, tmpPower);
				}else if(tmp[x][y-i] == 1) {
					tmp[x][y-i] = 0;
				}
			}
			
			if(y+i < w) {
				if(tmp[x][y+i] > 1) {
					tmpPower = tmp[x][y+i];
					tmp[x][y+i] = 0;
					removeBrick(x, y+i, tmpPower);
				}else if(tmp[x][y+i] == 1) {
					tmp[x][y+i] = 0;
				}
			}
		}
	}
}
