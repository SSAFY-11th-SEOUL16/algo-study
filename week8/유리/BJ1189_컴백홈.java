import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int r,c,k, result = 0;
	static char [][] data;
	static boolean [][] check;
	static int [] nr = {-1,1,0,0};
	static int [] nc = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(str.nextToken());
		c = Integer.parseInt(str.nextToken());
		k = Integer.parseInt(str.nextToken());
		data = new char [r][c];
		check = new boolean [r][c];
		for(int i = 0; i < r; i++) {
			data[i] = br.readLine().toCharArray();
			for(int x = 0; x < c; x++) {
				if(data[i][x] == 'T') {
					check[i][x] = true;
				}
			}
		}
		
		check[r-1][0] = true;
		//check[0][c-1] = true;
		
		dfs(1,r-1,0);
		System.out.println(result);
	}
	private static void dfs(int count, int x, int y) {
		if(count == k) {
			//System.out.println("test " + x + "/" + y);
			if(x == 0 && y == c-1) {
				result++;
			}
			return;
		}
		
		for(int i = 0; i <4; i++) {
			int tmpX = x + nr[i];
			int tmpY = y + nc[i];
			//System.out.println("test2 " + x + "/" + y);
			if(tmpX >= 0 && tmpX < r && tmpY >= 0 && tmpY < c && !(check[tmpX][tmpY])) {
				//System.out.println("test3 " + x + "/" + y);
				
				check[tmpX][tmpY] = true;
				dfs(count+1, tmpX, tmpY);
				check[tmpX][tmpY] = false;
			}
		}
	}
}
