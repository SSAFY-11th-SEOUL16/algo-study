import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int max = 1;
		int [] data = new int[n];
		int [] dp = new int[n];
		for(int i = 0; i < n; i++) {
			data[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = 0; i < n; i++) {
			dp[i] = 1;
			for(int x = 0; x < n; x++) {
				if(data[i] > data[x]) {
					dp[i] = Math.max(dp[i], dp[x]+1);
				}
			}
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(n-max);
	}
}
