import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, result = 0;
	static int [] data, dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		
		n = Integer.parseInt(br.readLine());
		data = new int [n];
		dp = new int [n+1];
		str = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			data[i] = Integer.parseInt(str.nextToken());
		}
		
		for(int x = 1; x <= n; x++) {
			for(int y = 1; y < x; y++) {
				dp[x] = Math.max(dp[x], dp[y-1]+cal(y, x));
			}
		}
		System.out.println(dp[n]);
	}
	
	static int cal(int startNum, int endNum) {
		int maxNum = Integer.MIN_VALUE;
		int minNum = Integer.MAX_VALUE;
		for(int i = startNum-1; i <= endNum-1; i++) {
			minNum = Math.min(minNum, data[i]);
			maxNum = Math.max(maxNum, data[i]);
		}
		
		return maxNum - minNum;
	}
}
