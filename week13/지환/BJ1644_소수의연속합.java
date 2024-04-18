import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

//Java8, 196ms
//N까지의 소수를 구한 후 배열의 체크
//2,3,5,7 이 있다면 dp를 통해 누적합을 구한다. 2,5,10,17
//투포인터로 두개의 포인터의 차가 n이 되는 개수를 구한다.
public class BJ1644_소수의연속합 {
	static boolean[] nums;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		nums = new boolean[n+1];
		Arrays.fill(nums, true);
		findPrime(n);
		
		int[] dp = new int[n+1];
		
		int last = 0;
		for(int i=2; i<=n; i++) {
			if(!nums[i]) continue;
			dp[i] = dp[last] + i;
			last=i;
		}
		
		int sum = dp[n];
		int r = 2;
		int l = 1;
		int cnt = 0;
		while(r<=n && l<r) {
			if(dp[r] - dp[l] > n) l++;
			else if(dp[r] - dp[l] < n) r++;
			else {
				r++;
				l++;
				cnt++;
			}
			while(l<=n && !nums[l]) l++;
			while(r<=n && !nums[r]) r++;
		}
		System.out.println(cnt);
	}
	
	
	static void findPrime(int n) {
		for(int i=2; i*i<=n; i++) {
			if(!nums[i]) continue;
			for(int j=i*i; j<=n; j+=i) {
				nums[j] = false;
			}
		}
	}

}
