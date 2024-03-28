import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

//Java8(76ms)
//결국 구해야하는 건 움직여야하는 '사람의 수'
//최장 증가하는 수열을 구하고 전체 인원수에서 빼주면 움직여야하는 '사람의 수'가 구해진다.
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
