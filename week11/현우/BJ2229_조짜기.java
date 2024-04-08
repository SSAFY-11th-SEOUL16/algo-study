import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2229_조짜기 {
	
	/**
	 * 80 ms
	 * DP
	 * dp[i] : i 를 i - 1과의 조에
	 * 포함하는 i 까지의 최대 점수
	 * 차이가 0일 경우 조를 나눔
	 * 조를 나눴을때 점수와
	 * 안 나눴을때의 점수 비교
	 */
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, diff, prevDiff, num, prevNum, i;
		int[] dp;
		
		n = Integer.parseInt(br.readLine());
		if (n == 1) { // 학생이 1 명인 경우
			System.out.print("0");
			return;
		}
		dp = new int[n];
		st = new StringTokenizer(br.readLine());
		prevDiff = -Integer.parseInt(st.nextToken()) + (prevNum = Integer.parseInt(st.nextToken()));
		dp[1] = Math.abs(prevDiff); // 처음 두 명은 항상 같은 조에 포함
		for (i = 2; i < n; i++) {
			num = Integer.parseInt(st.nextToken());
			diff = num - prevNum;
			if (prevDiff == 0) { // 이전의 diff가 0이었을 경우
				dp[i] = dp[i - 2] + Math.abs(diff); // 조를 분리
			} else if (diff == 0) { // 현재 diff가 0인 경우
				dp[i] = dp[i - 1]; // 점수에 영향이 없음
			} else if (prevDiff * diff > 0) { // 증가-증가 혹은 감소-감소
				dp[i] = Math.max(dp[i - 1] + Math.abs(diff), dp[i - 2] + Math.abs(diff)); // 조를 안 나눴을 때와 나눴을 때 점수 비교
			} else { // 증가-감소 혹은 감소-증가
				dp[i] = Math.max(dp[i - 1], dp[i - 2] + Math.abs(diff)); // 조를 안 나눴을 때와 나눴을 때 점수 비교
			} // prevDiff가 더 큰 경우 dp[i - 1]이 선택되고 diff가 더 큰 경우 dp[i - 2] + Math.abs(diff)가 선택됨
			prevDiff = diff;
			prevNum = num;
		}
		System.out.print(dp[n - 1]);
	}
}
