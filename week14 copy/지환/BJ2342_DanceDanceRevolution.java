import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//Java8 336ms
//dp[i][j][k]는 i번째에 왼발이 j 오른발이 k 일때 최소의 힘이다.
public class BJ2342_DanceDanceRevolution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		List<Integer> commands = new ArrayList<>();
		int len = 0;
		commands.add(0);
		while(true) {
			int n = Integer.parseInt(st.nextToken());
			if(n==0) break;
			commands.add(n);
			len++;
		}
		
		int[][][] dp = new int[len+1][5][5];
		for(int i=0; i<=len; i++) {
			for(int j=0; j<5; j++) {
				for(int k=0; k<5; k++) {
					dp[i][j][k] = len*3+1;
				}
			}
		}
		dp[0][0][0] = 0;
		for(int i=1; i<=len; i++) {
			int target = commands.get(i);
			for(int j=0; j<5; j++) {
				for(int k=0; k<5; k++) {
					if(dp[i-1][j][k] == len*3+1) continue;
					dp[i][target][k] = Math.min(dp[i-1][j][k] + move(j, target), dp[i][target][k]);
					dp[i][j][target] = Math.min(dp[i-1][j][k] + move(k, target), dp[i][j][target]);
				}
			}
		}
		int lastTarget = commands.get(len);
		
		int ans = Integer.MAX_VALUE;
		
		for(int i=0; i<5; i++) {
			ans = Math.min(dp[len][lastTarget][i], ans);
			ans = Math.min(dp[len][i][lastTarget], ans);
		}
		System.out.println(ans);
	}
	
	public static int move(int now, int target) {
		if(now==target) return 1;
		if(now==0) return 2;
		else if(Math.abs(target - now) == 2) return 4;
		else return 3;
	}
	
}
