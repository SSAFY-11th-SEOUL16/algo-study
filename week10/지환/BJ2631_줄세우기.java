import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ2631_줄세우기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n+1];
		for(int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[n+1];
		Arrays.fill(dp, 1);
		for(int i=2; i<=n; i++) {
			for(int j=1; j<i; j++) {
				if(arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
		int max = 0;
		for(int i=1; i<=n; i++) {
			max = Math.max(max, dp[i]);
		}
		System.out.println(n - max);
	}
}
