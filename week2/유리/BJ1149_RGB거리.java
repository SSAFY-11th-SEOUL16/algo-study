import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int minValue = Integer.MAX_VALUE;
	static int [][] data;
	static boolean [][] check;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		data = new int [N][3];
		check = new boolean[N][3];
		StringTokenizer str;
		for(int i = 0; i < N; i++) {
			str = new StringTokenizer(br.readLine());
			for(int x = 0; x < 3; x++) {
				if(i == 0) {
					data[i][x] = Integer.parseInt(str.nextToken());
				}
				else {
					if(x == 0) {
						data[i][0] = Math.min(data[i-1][1], data[i-1][2]) + Integer.parseInt(str.nextToken());
					}else if(x == 1) {
						data[i][1] = Math.min(data[i-1][0], data[i-1][2]) + Integer.parseInt(str.nextToken());
					}else {
						data[i][2] = Math.min(data[i-1][0], data[i-1][1]) + Integer.parseInt(str.nextToken());
					}
				}
			}
		}
		System.out.println(Math.min(Math.min(data[N-1][0], data[N-1][1]),data[N-1][2]));
	}
}
