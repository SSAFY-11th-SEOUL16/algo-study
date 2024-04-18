import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
- 524 ms

- 구현

- N개의 카드와 플레이어 정보를 입력으로 받아와서 처리하는 문제
- 주어진 섞는 방법과 최종 카드 배분 정보를 바탕으로 카드를 섞는 최소 횟수를 구함

- 배열 P : 최종 카드 배분 정보, 배열 S : 섞는 방법
- 카드를 섞고 난 후에 배열 P에 해당하는 카드 분배가 완료되었는지 확인하는 isSorted 구현
- 카드 섞는 과정에서 중복된 상태가 발생하는지 확인하기 위해 HashSet을 사용하여 문자열로 상태 저장
- 섞는 과정에서 카드가 계속 같은 상태로 반복되는 경우를 체크하고, -1을 출력함
- 모든 과정을 반복하여 최종적으로 카드를 배분할 수 있으면 섞는 횟수를 출력함
 */

public class BJ1091_카드섞기 {
	static int N;
	static int[] P, S;
	static int[] card, temp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		P = new int[N];
		S = new int[N];
		card = new int[N];
		temp = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			P[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
			card[i] = i; // 초기 카드 배열을 순서로 초기화
		}

		if (isSorted(card)) { // 목표 상태에 있는지 확인
			System.out.println(0);
			return;
		}

		HashSet<String> check = new HashSet<>(); // 중복 상태를 확인하기 위해 HashSet을 사용
		check.add(makeString(card));

		int count = 0;
		while (true) {
			for (int i = 0; i < N; i++) {
				temp[S[i]] = card[i]; // S 배열에 따라 카드를 섞음
			}
			System.arraycopy(temp, 0, card, 0, N); // 섞은 결과를 card 배열에 복사
			count++;

			if (isSorted(card)) { // 목표 상태에 있는지 확인
				System.out.println(count);
				return;
			}

			String str = makeString(card);
			if (!check.add(str)) { // 중복 상태를 체크
				System.out.println(-1); // 만약 해당 상태가 이미 존재한다면 -1
				return;
			}
		}
	}

	// 카드가 목표 플레이어에게 올바르게 배분되었는지 확인
	static boolean isSorted(int[] card) {
		for (int i = 0; i < N; i++) {
			if (P[card[i]] != i % 3) {
				return false;
			}
		}
		return true;
	}

	// Hashset에 String 형태로 저장하기 위해 현재 카드 배열의 상태를 문자열로 변환
	static String makeString(int[] array) {
		StringBuilder sb = new StringBuilder();
		for (int value : array) {
			sb.append(value).append(',');
		}
		return sb.toString();
	}
}