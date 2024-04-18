import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
- 220 ms

- 에라토스테네스의 체

- 입력된 자연수를 소수와 소수가 아닌 수로 체크
- 투 포인터를 사용하여 연속된 소수의 합을 구함
- 왼쪽과 오른쪽 포인터를 이동하며 합을 조절하여 가능한 경우의 수 계산
 */

public class BJ1644_소수의_연속합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] check = new boolean[N + 1];	// 각 수의 소수 여부를 저장
		ArrayList<Integer> prime = new ArrayList<>();	// 소수를 저장

		check[0] = check[1] = true;	// 0과 1은 소수가 아니므로 true로 체크
		for (int i = 2; i <= Math.sqrt(N); i++) {	// 에라토스테네스의 체 사용
			for (int j = i * i; j <= N; j += i) {
				if (!check[j]) {
					check[j] = true;
				}
			}
		}

		for (int i = 2; i <= N; i++) {	// 소수인 수를 리스트에 저장
			if (!check[i]) {
				prime.add(i);
			}
		}

		int sum = 2, left = 0, right = 0, answer = 0;
		// 투 포인터를 사용하여 연속된 소수의 합을 찾음
		while (left < prime.size() && right < prime.size()) {
			if (sum == N) {	// 합이 N과 같으면 경우의 수 증가
				answer++;
				sum -= prime.get(left);
				left++;
			} else if (sum > N) {	// 합이 N보다 크면 왼쪽 포인터 이동
				sum -= prime.get(left);
				left++;
			} else {	// 합이 N보다 작으면 오른쪽 포인터 이동
				right++;
				if (right >= prime.size()) {
					break;
				}
				sum += prime.get(right);
			}
		}
		System.out.println(answer);

	}
}