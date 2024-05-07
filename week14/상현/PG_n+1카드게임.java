import java.util.ArrayList;
import java.util.List;

/**
 * 구현
 * 제일 오래 가기 위해서는 우선 가지고 있던 카드에서 n + 1이 될 수 있다면 다 내어줌
 * 그 다음 가지고 있던 카드에서 불가능 할 경우 가지고 있는 카드들과 버린 카드 중에 코인 1개를 써서 n + 1 만들 수 있는지 확인
 * 코인 1개로도 안된다면 버린 카드들 중에 n + 1을 만들 수 있는 경우를 확인
 * 2개로도 안된다면 라운드 진행 불가
 */
class Solution {
    public int solution(int coin, int[] cards) {
        int answer = game(cards.length, coin, cards);;
        game(cards.length, coin, cards);

        return answer;
    }

    private static int game(int n, int coin, int[] cards) {
        int result = 1;

        int cardIdx = n / 3;
        int target = n + 1;
        boolean[] isGetCard = new boolean[n + 1];
        boolean[] trashCard = new boolean[n + 1];
    }
}
