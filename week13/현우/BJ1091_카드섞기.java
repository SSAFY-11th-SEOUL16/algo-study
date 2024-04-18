import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1091_카드섞기 {
	
	/**
	 * 108 ms
	 * 구현
	 * P가 0 1 2 반복이면 섞지 않음
	 * 카드를 섞으면서 P를 만족하는지 확인
	 * 초기 세팅과 동치이면 사이클이 발생하므로
	 * P 만족 불가능
	 */
	
	private static final String IMPOSSIBLE = "-1";
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1, st2;
		
		int n, ans, i;
		int[] p, s, prev, cards, temp;
		
		n = Integer.parseInt(br.readLine());
		p = new int[n];
		s = new int[n];
		prev = new int[n];
		st1 = new StringTokenizer(br.readLine());
		st2 = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			p[i] = Integer.parseInt(st1.nextToken());
			s[i] = Integer.parseInt(st2.nextToken());
			prev[i] = i; // 초기 카드
		}
		for (i = 0; i < n; i++) { // P가 0 1 2 반복인지 확인
			if (p[i] != i % 3) {
				break;
			}
		}
		if (i == n) {
			System.out.print("0"); // 섞지 않음
			return;
		}
		cards = new int[n];
		for (ans = 1;; temp = prev, prev = cards, cards = temp, ans++) {
			for (i = 0; i < n; i++) { // 카드 섞기
				cards[s[i]] = prev[i];
			}
			for (i = 0; i < n; i++) { // P를 만족하는지 확인
				if (p[cards[i]] != i % 3) {
					break;
				}
			}
			if (i == n) { // 정답 출력
				System.out.print(ans);
				return;
			}
			for (i = 0; i < n; i++) { // 초기 세팅과 동치인지 확인
				if (cards[i] % 3 != i % 3) {
					break;
				}
			}
			if (i == n) { // P 만족 불가능
				System.out.print(IMPOSSIBLE);
				return;
			}
		}
	}
}
