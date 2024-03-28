import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14267_회사문화1 {
	
	/**
	 * 420 ms
	 * DP
	 * 직원별 칭찬 카운트
	 * 직속 상사의 번호는 자신의 번호보다 작으므로
	 * 칭찬 = 원래 자신 칭찬 + 직속 상사 칭찬
	 */
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, i;
		int[] dp;
		String str;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		str = br.readLine(); // 직속 상사 정보 저장
		dp = new int[n + 1]; // 칭찬
		for (i = 0; i < m; i++) { // m회 칭찬
			st = new StringTokenizer(br.readLine());
			dp[Integer.parseInt(st.nextToken())] += Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(str); // 직속 상사 정보 사용
		st.nextToken(); // -1 무시
		sb.append(dp[1]).append(' '); // 1 번은 상사가 없으므로 출력
		for (i = 2; i <= n; i++) { // 칭찬 += 직속 상사 칭찬
			sb.append(dp[i] += dp[Integer.parseInt(st.nextToken())]).append(' ');
		}
		System.out.print(sb);
	}
}
