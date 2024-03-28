import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Java8(440ms)
//head배열에 직속 상관을 저장
//dp배열에 칭찬 수치 저장. dp[n] = dp[직속상관] + n번째 직원가 받은 칭찬
public class BJ14267_회사문화1 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] heads = new int[n+1];
		for(int i=1; i<=n; i++) {
			heads[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[n+1];
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			dp[num] += w;
		}

		for(int i=2; i<=n; i++) {
			dp[i] += dp[heads[i]];
		}

		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=n; i++) {
			sb.append(dp[i]).append(" ");
		}
		System.out.println(sb);
	}
}
