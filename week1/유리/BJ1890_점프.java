import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tmp;
		
		int N = Integer.parseInt(br.readLine());
		int [][] data = new int [N][N];
		long [][] data2 = new long [N][N];
		data2[0][0] = 1;
		
		for(int i = 0; i < N; i++) {
			tmp =  new StringTokenizer(br.readLine());
			for(int y = 0; y < N; y++) {
				data[i][y] = Integer.parseInt(tmp.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int y = 0; y < N; y++) {
				if(data2[i][y] >= 1) {
					int next = data[i][y];
					if(next == 0) {break;}
					if(i+next < N) {data2[i+next][y]+=data2[i][y];}
					if(y+next < N) {data2[i][y+next]+=data2[i][y];}
				}
			}
		}
		
		bw.write(data2[N-1][N-1] + "\n");
		bw.flush();
		br.close();
		bw.close();
	}
}
