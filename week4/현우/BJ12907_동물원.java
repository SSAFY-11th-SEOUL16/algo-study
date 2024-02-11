import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ12907_동물원 {
	/*
	 * 76 ms
	 * 
	 * (입력되는 수 전부 +1)
	 * N 개의 숫자로
	 * 1 2 3 4 5
	 * 1 2 3
	 * 와 같이 1부터 연속된 숫자로 이루어진 수열이 최대 2 개 만들어져야 함
	 * 같은 숫자는 2 개 이하로 입력 되면서
	 * max1(5) + max2(3) == N(8)이 성립
	 * 가능한 조합의 수 = 2의 max2제곱, max1 > max2이면 2 배
	 */
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, max1, max2, num, i;
		int[] cnt;
		
		n = Integer.parseInt(br.readLine());
		cnt = new int[n + 1]; // 숫자가 입력된 횟수 저장
		max1 = 0; // 1 개 이상 입력된 숫자 중 최대값
		max2 = 0; // 2 개 입력된 숫자 중 최대값
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			num = Integer.parseInt(st.nextToken()) + 1; // 계산 편의를 위해 입력되는 숫자 +1
			if (num > n || ++cnt[num] == 3) { // N 보다 큰 숫자 또는 같은 숫자 3개 이상 입력 시
				System.out.print("0"); // 조합 불가능
				return;
			} else if (cnt[num] == 1) { // 처음 입력되는 숫자면
				max1 = Math.max(max1, num); // max1 갱신
			} else { // 두 번째 입력되는 숫자면
				max2 = Math.max(max2, num); // max2 갱신
			}
		}
		if (max1 + max2 == n) {
			// 가능한 조합 수 = 2의 max2제곱, max1 > max2이면 2 배
			System.out.print((int) Math.pow(2, max2 + (max1 == max2 ? 0 : 1)));
		} else { // max1 + max2가 N이 아니면
			System.out.print("0"); // 조합 불가능
		}
	}
}
