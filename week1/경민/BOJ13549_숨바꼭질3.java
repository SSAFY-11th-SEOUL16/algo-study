import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long N, K;
	static long result = Integer.MAX_VALUE;

	public void solution(long second, long X) {
		if (second > result) {
			return;
		} else if (result == 0) {
			return;
		} else if (X == K) {
			result = Math.min(result, second);
		} else if (X > K) {
			long tmp = X - K;
			solution(second + tmp, X - tmp);
		} else if (X <= 0) {
			long tmp = K - X;
			solution(second + tmp, X - tmp);
		} else {
			solution(second, X *= 2);
			solution(second + 1, X + 1);
			solution(second + 1, X - 1);
		}
	}

	public static void main(String[] args) throws IOException {
		Main M = new Main();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		if (N == K) {
			System.out.println(0);
		} else {
			if (N == 0) {
				M.solution(1, 1);
			} else {
				M.solution(0, N);
			}
			System.out.println(result);
		}
	}
}