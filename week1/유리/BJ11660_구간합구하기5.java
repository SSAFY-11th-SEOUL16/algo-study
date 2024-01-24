import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tmpData = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(tmpData.nextToken());
		int m = Integer.parseInt(tmpData.nextToken());
		int [][] data = new int[n+1][n+1];
		int result;
		
		for(int i = 1; i <= n; i++) {
			 tmpData = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				data[i][j] = data[i-1][j]+ data[i][j-1] + Integer.parseInt(tmpData.nextToken())- data[i-1][j-1];
			}
		}
		
		for(int i = 0; i < m; i++) {
			tmpData = new StringTokenizer(br.readLine());
			result = 0;
			int x1 = Integer.parseInt(tmpData.nextToken());
			int y1 = Integer.parseInt(tmpData.nextToken());
			int x2 = Integer.parseInt(tmpData.nextToken());
			int y2 = Integer.parseInt(tmpData.nextToken());
			bw.write(data[x2][y2]+data[x1-1][y1-1]-data[x1-1][y2]-data[x2][y1-1] +"\n");
			bw.flush();
		}
		br.close();
		bw.close();
	}
}
