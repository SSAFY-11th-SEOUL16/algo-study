import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * JAVA
 * 108 ms
 * 
 * DP
 * 
 * 조별로 나누어 가장 점수 차이가 큰 조를 만들어 그 차이의 합이 최대가 되도록 하는 방법
 * 
 * DP 배열을 초기화
 *   - i번째 학생부터 시작하여 N번째 학생까지 조를 구성했을 때 얻을 수 있는 최대 점수 차이의 합
 * DP 배열 채우기 
 *   - 역순으로 확인하면서 각 학생을 시작점으로 할 때 얻을 수 있는 최대 점수 차이의 합을 DP 배열에 저장
 */
public class BJ2229_조_짜기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] dp = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = N - 1; i >= 0; i--) {
			int min = arr[i];
			int max = arr[i];

			for (int j = i; j < N; j++) {
				max = Math.max(max, arr[j]);
				min = Math.min(min, arr[j]);
				
				dp[i] = Math.max(dp[i], dp[j + 1] + max - min);
			}	
		}
		System.out.println(dp[0]);
	}
}