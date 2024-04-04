import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
		StringTokenizer str2;
		
		int N = Integer.parseInt(str.nextToken());
		int M = Integer.parseInt(str.nextToken());
		int result = Integer.MAX_VALUE;
		int []memoryData = new int[N];
		int []costData = new int[N];		
		int [][]resultData = new int [N][100001];
		
		str = new StringTokenizer(br.readLine());
		str2 = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			memoryData[i] =Integer.parseInt(str.nextToken());
			costData[i] = Integer.parseInt(str2.nextToken());
		}
		for(int i = 0; i < N; i++) {
			int cost = costData[i];
			int memory = memoryData[i];
			for(int x = 0; x <= 10000; x++) {
				if(i == 0) {
					if(x >= cost) {
						resultData[i][x] = memory; 
					}
				}else {
					if(x >= cost) {
						resultData[i][x] = Math.max(resultData[i-1][x-cost]+memory, resultData[i-1][x]);
					}else {
						resultData[i][x] = resultData[i-1][x];
					}
				}
				
				if(resultData[i][x] >= M) {result = Math.min(result, x);}
			}
		}
		
		System.out.println(result);	
	}
}
