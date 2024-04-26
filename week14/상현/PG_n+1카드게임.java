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
        List<Integer> nowCards = new ArrayList<>();
        int targetCnt = 0;
        for (int i = 0; i < cardIdx; i++) {
            isGetCard[cards[i]] = true;
        }
        for (int i = 0; i < cardIdx; i++) {
            if (isGetCard[target - cards[i]] && isGetCard[cards[i]]) {
                isGetCard[target - cards[i]] = false;
                isGetCard[cards[i]] = false;
                targetCnt++;
            }
        }

        while (cardIdx < n) {
            int card1 = cards[cardIdx++], card2 = cards[cardIdx++];
            trashCard[card1] = trashCard[card2] = true;

            boolean isGo = false;
            //기존에 얻은 카드들 중에 만들 수 있는 경우가 존재하면 그냥 진행
            if (targetCnt > 0) {
                targetCnt -= 1;
                isGo = true;
            }
            //가지고 있는 카드들과 코인 1개를 써서 버린 카드들 중에 n + 1을 만들 수 있는지 확인
            if (!isGo) {
                if (coin >= 1) {
                    for (int i = 1; i <= n / 2 ; i++) {
                        if (isGetCard[i] && trashCard[target - i] && !isGo) {
                            isGetCard[i] = trashCard[target - i] = false;
                            coin -= 1;
                            isGo = true;
                        }
                        if (isGetCard[target - i] && trashCard[i] && !isGo) {
                            isGetCard[target - i] = trashCard[i] = false;
                            coin -= 1;
                            isGo = true;
                        }
                    }
                }
            }

            //코인 2개를 써서 버린 카드들 중에 n + 1을 만들 수 있는지 확인
            if (!isGo) {
                if (coin >= 2) {
                    for (int i = 1; i <= n / 2 ; i++) {
                        if (trashCard[i] && trashCard[target - i] && !isGo) {
                            trashCard[target - i] = trashCard[i] = false;
                            coin -= 2;
                            isGo = true;
                        }
                    }
                }
            }

            if (isGo) {
                result++;
                continue;
            }

            break;
        }
        return result;
    }
}
