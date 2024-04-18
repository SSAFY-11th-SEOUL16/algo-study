import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1644_소수의연속합 {
	
	/**
	 * 120 ms
	 * 투 포인터
	 * 소수 테이블 생성 -> 에라토스테네스의 체
	 * left = 0, right = 0 부터 투 포인터
	 * 연속 합이 N 보다 작은 경우 right++
	 * 크거나 같은 경우 left++
	 * 연속 합이 N일 때 카운트
	 * right가 끝에 도달하면 종료
	 */
	
	private static final int SIZE = 283147; // 4000000 이하의 소수
	
	private static int n, idx;
	
	private static int[] getPrimes() {
		int prev, i, j;
		int[] primes;
		boolean[] notPrime;
		
		primes = new int[SIZE];
		primes[0] = 2; // 첫 번째 소수
		prev = 2;
		idx = 1;
		notPrime = new boolean[n + 1];
		for (i = 3; i <= n; i += 2) { // 에라토스테네스의 체
			if (notPrime[i]) { // 소수가 아닐 경우
				continue;
			}
			if (prev + i > n) { // 최근 두 소수 합이 N을 초과
				if ((n & 1) == 1 && !notPrime[n]) { // N이 소수이면
					primes[idx++] = n; // 마지막 소수로 추가
				}
				break; // 종료
			}
			primes[idx++] = prev = i; // 소수로 추가
			for (j = i << 1; j <= n; j += i) { // 배수 처리
				notPrime[j] = true;
			}
		}
		return primes;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int left, right, sum, cnt;
		int[] primes;
		
		n = Integer.parseInt(br.readLine());
		primes = getPrimes(); // 소수 테이블 생성
		cnt = 0;
		sum = 0;
		for (left = 0, right = 0;; sum -= primes[left++]) { // 투 포인터, left 1 증가
			while (sum < n) { // 합이 N 보다 작으면
				if (right == idx) { // right가 마지막에 도달
					System.out.print(cnt);
					return; // 종료
				}
				sum += primes[right++]; // right 1 증가
			}
			if (sum == n) { // 합이 N이면
				cnt++; // 카운트
			}
		}
	}
}
