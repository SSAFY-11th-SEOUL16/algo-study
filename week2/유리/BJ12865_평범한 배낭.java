import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int [] weight;
	static int [] value;
	static int dp[][];
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
		N = Integer.parseInt(str.nextToken());
		K = Integer.parseInt(str.nextToken());
		dp = new int[N+1][K+1];
		weight = new int [N+1];
		value = new int [N+1];
		for(int i = 1; i <= N; i++) {
			str = new StringTokenizer(br.readLine());
			weight[i] = Integer.parseInt(str.nextToken());
			value[i] = Integer.parseInt(str.nextToken());
		}
		
		for(int i = 1; i <= N; i++) {
			for(int y = 1; y <= K; y++) {
				dp[i][y] = dp[i-1][y];
				if(y - weight[i] >=0) {
					dp[i][y] = Math.max(dp[i-1][y], dp[i-1][y-weight[i]]+value[i]);
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}
}
