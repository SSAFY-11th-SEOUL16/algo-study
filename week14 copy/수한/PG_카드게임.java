package algorithm.programmers;

public class Kakao_2024_겨울인턴십_카드게임 {
    class Solution {
        public int solution(int coin, int[] cards) {
            int initIdx = cards.length / 3;

            boolean[] initCards = new boolean[cards.length];

            for (int i = 0; i < initIdx; i++) {
                initCards[cards[i]-1] = true;
            }

            // 합이 n+1이 되는 짝 개수 구하기
            // 짝의 개수만큼 라운드 진행 가능
            int numPairs = 0;
            for (int i = 0; i < initCards.length; i++) {
                int j = (cards.length - 1) - i;
                if(initCards[i] && initCards[j]){
                    numPairs++;
                    initCards[i] = initCards[j] = false;
                }
            }

            int curIdx = initIdx;
            // 초기 짝을 통한 라운드 진행 후의 index
            int idx = curIdx + (1 + numPairs) * 2;

            // 라운드 진행하면서 뽑을 수 있는 카드 목록
            boolean[] validCards = new boolean[cards.length];

            while(curIdx != idx && idx <= cards.length){
                numPairs = 0;
                for (int i = curIdx; i < idx; i++) {
                    int num = cards[i]-1;
                    validCards[num] = true;
                    int apoNum = (cards.length - 1) - num;
                    // coin 1개로 사기
                    if(initCards[apoNum] && coin > 0){
                        validCards[num] = false;
                        initCards[apoNum] = false;
                        numPairs++;
                        coin--;
                    }
                }
                if(numPairs == 0 && coin >= 2){
                    // coin 2개로 사기
                    for (int i = initIdx; i < idx; i++) {
                        int num = cards[i]-1;
                        int apoNum = (cards.length - 1) - num;
                        if(validCards[num] && validCards[apoNum]){
                            validCards[num] = false;
                            validCards[apoNum] = false;
                            numPairs++;
                            coin-=2;
                            break;
                        }
                    }
                }
                curIdx = idx;
                idx += numPairs * 2;
            }
            return (idx-initIdx) / 2;
        }
    }

    public static void main(String[] args) {
    }
}