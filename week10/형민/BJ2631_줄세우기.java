import java.io.*;
import java.util.*;

public class BJ2631_줄세우기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int max = 0;
		
		int arr[] = new int[n];
		int dp[] = new int[n];
		
		for(int i=0;i<n;i++) {
			int v = Integer.parseInt(br.readLine());
			arr[i] = v;
		}
		
		for(int i=0;i<n;i++) {
			dp[i] = 1;
			
			//오름차순 개수 갱신
			for(int j=0;j<i;j++) {
				if(arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(n - max);
	}

}

