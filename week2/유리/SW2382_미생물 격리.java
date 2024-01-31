import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	static int N, M, K;
	static long [][] classRoom;
	static long [][] classRoom2;
	static int [] nc = {0,-1, 1, 0, 0};
	static int [] nr = {0,0, 0, -1, 1};
	static long result;
	static int test = 1;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		long T = Integer.parseInt(br.readLine());
		for(int i = 1; i <= T; i++) {
			str= new StringTokenizer(br.readLine());
			N= Integer.parseInt(str.nextToken());
			M= Integer.parseInt(str.nextToken());
			K= Integer.parseInt(str.nextToken());
			
			result = 0;
			classRoom = new long [N][N];
			classRoom2 = new long [N][N];
			
			for(int x = 0; x < K; x++) {
				str= new StringTokenizer(br.readLine());
				int tmpx = Integer.parseInt(str.nextToken());
				int tmpy = Integer.parseInt(str.nextToken());
				classRoom[tmpx][tmpy] = Integer.parseInt(str.nextToken());
				classRoom2[tmpx][tmpy] = Integer.parseInt(str.nextToken());			
			}
			
			
			move(0);
			
			System.out.println("#"+i + " "+result);
		}
	}
	static void move(int count) {
		if(count == M) {
			for(int x = 0; x < N; x++) {
				for(int y = 0; y < N; y++) {
					result+=classRoom[x][y];
				}
			}
			return;
		}
		
		
		long [][] classRoom3 = new long [N][N];
		long [][] classRoom4 = new long [N][N];
		long [][] classRoom5 = new long [N][N];
		int countK = 0;
		boolean check = false;
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				if(classRoom[x][y] != 0) {
					countK ++;
					int ncx = x+nc[(int) classRoom2[x][y]];
					int nry = y+nr[(int)classRoom2[x][y]];
					
					if(ncx == 0 || nry == 0 || ncx == N-1 || nry == N-1) {
						classRoom[x][y] = (classRoom[x][y] * 10 ) / 2 /10 ;
						if(classRoom2[x][y] == 1) {
							classRoom2[x][y] = 2;
						}
						else if(classRoom2[x][y] == 2) {
							classRoom2[x][y] = 1;
						}
						else if(classRoom2[x][y] == 3) {
							classRoom2[x][y] = 4;
						}
						else if(classRoom2[x][y] == 4) {
							classRoom2[x][y] = 3;
						}
					}
					
					if(classRoom3[ncx][nry] == 0) {
						classRoom3[ncx][nry] = classRoom[x][y];
						classRoom4[ncx][nry] = classRoom2[x][y];
						classRoom5[ncx][nry] = classRoom[x][y];
						
					}else {
						if(classRoom5[ncx][nry] > classRoom[x][y]) {
							classRoom3[ncx][nry] += classRoom[x][y];
						}else {
							classRoom3[ncx][nry] += classRoom[x][y];
							classRoom4[ncx][nry] = classRoom2[x][y];
							classRoom5[ncx][nry] = classRoom[x][y];
						}
					}
					
					classRoom[x][y] = 0;
					classRoom2[x][y] = 0;
				}
				if(K== countK) {
					check = true;
					break;
				}
			}
			if(check) {
				break;
			}
		}
		classRoom = classRoom3;
		classRoom2 = classRoom4;
		move(count+1);
	}
}
