import java.util.HashSet;
import java.util.Set;

/*
 * Java
 * 
 * - 게임 룰에 따라 동전과 카드를 관리하고 최대 라운드 수를 계산함
 * - 카드 뭉치에서 초기 카드를 뽑고, 라운드별로 카드를 뽑아 쌍을 맞추거나 동전을 사용함
 * - 쌍을 맞추거나 동전을 사용하여 최대 라운드까지 진행함
 */

public class PG258707_n1카드게임 {
	private static Set<Integer> myCards = new HashSet<>(); // 카드 뭉치에 있는 카드를 저장하는 집합
	private static Set<Integer> tempCards = new HashSet<>();
	private static int pair; // 현재 라운드에서의 쌍의 수
	private static int round = 1;
	private static int myCoin;
	private static int n;

	public static int solution(int coin, int[] cards) {
		myCoin = coin;
		n = cards.length;
		round = 1;
		pair = 0;
		myCards = new HashSet<>();
		tempCards = new HashSet<>();

		for (int i = 0; i < n / 3; i++) { // 초기 카드 설정: 처음 n/3장의 카드를 가짐
			myCards.add(cards[i]);
		}

		for (int card : myCards) { // 쌍의 수 계산
			if (myCards.contains(n + 1 - card)) {
				pair++;
			}
		}
		pair /= 2;

		for (int i = n / 3; i < n; i += 2) { // 라운드 진행
			matchCard(cards[i]);
			matchCard(cards[i + 1]);

			if (pair < 1 && myCoin > 1) { // 쌍이 부족하고 동전이 더 있으면, 더 가질 수 있는 쌍을 찾음
				for (int card : tempCards) {
					if (tempCards.contains(n + 1 - card)) {
						pair++;
						myCoin -= 2;
						tempCards.remove(card);
						break;
					}
				}
			}

			if (pair < 1) { // 쌍이 부족하면 종료
				break;
			}
			round++; // 다음 라운드
			pair--;
		}

		return round; // 최대 라운드 반환
	}

	public static void matchCard(int card) { // 카드 매칭
		if (myCoin > 0 && myCards.contains(n + 1 - card)) { // 동전이 있고 카드 뭉치에 해당 카드의 짝이 있다면 쌍 추가
			myCoin--;
			pair++;
			return;
		}
		tempCards.add(card);
	}

	public static void main(String[] args) {
		System.out.println(solution(4, new int[] { 3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4 }));
		System.out.println(solution(4, new int[] { 1, 2, 3, 4, 5, 8, 6, 7, 9, 10, 11, 12 }));
		System.out.println(solution(4, new int[] { 5, 8, 1, 2, 9, 4, 12, 11, 3, 10, 6, 7 }));
		System.out.println(solution(4, new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 }));
	}
}