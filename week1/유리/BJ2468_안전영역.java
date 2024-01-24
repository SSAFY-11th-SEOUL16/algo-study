import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int [][] data;
	static boolean [][] data2;
	static int [] nx = {-1 , 0, 1, 0};
	static int [] ny = {0 , -1, 0, 1};
	static int max = 1;
	static int min = 100;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tmp;
		
		N = Integer.parseInt(br.readLine());
		data = new int [N][N];
		int result = 1;
		
		for(int i = 0; i < N; i++) {
			tmp =  new StringTokenizer(br.readLine());
			for(int y = 0; y < N; y++) {
				data[i][y] = Integer.parseInt(tmp.nextToken());
				if(min > data[i][y]) {min = data[i][y];}
				if(max < data[i][y]) {max = data[i][y];}
			}
		}
		
		for(int i = 0; i < max; i++) {
			data2 = new boolean[N][N];
			int tmp2 = 0;
			for(int x = 0; x < N; x++) {
				for(int y = 0; y < N; y++) {
					if((!data2[x][y]) && data[x][y] > i) {
						tmp2 += dfs(x,y,i);
					}
				}
			}
			if(tmp2 > result) {result = tmp2;}
		}
		
		bw.write(result + "\n");
		bw.flush();
		br.close();
		bw.close();
	}
	static int dfs(int x, int y, int i) {
		data2[x][y] = true;
		for(int z=0; z<4;z++) {
			int tmpx =x+nx[z];
			int tmpy=y+ny[z];
			if(tmpx >= N || tmpx < 0 || tmpy >= N || tmpy < 0) {continue;}
			if(data2[tmpx][tmpy]) {continue;}
			if(data[tmpx][tmpy] > i) {dfs(tmpx, tmpy, i);}
		}
		return 1;
	}
}
