import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Java8(448ms)
public class BJ2056_작업 {
	//특정 작업의 선행작업은 이미 작업이 끝난 상태
	//현재 작업이 완료된 시간 = 선행 작업이 완료된 시간 + 현재 작업이 걸리는 시간
	//dp로 해결 가능
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n+1];
		int ans = 0;
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			int preCnt = Integer.parseInt(st.nextToken());
			dp[i] = time;
			for(int j=0; j<preCnt; j++) {
				int prev = Integer.parseInt(st.nextToken());
				dp[i] = Math.max(dp[i], dp[prev] + time);
			}
			ans = Math.max(dp[i], ans);
		}
		System.out.println(ans);
	}
}
