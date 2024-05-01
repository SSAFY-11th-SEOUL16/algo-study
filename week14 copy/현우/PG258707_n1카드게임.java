/**
 * 0.06 ms
 * 구현
 * 처음 카드 + 2 장은 항상 볼 수 있음
 * 처음 카드에 있는 페어마다 2 장씩 더 볼 수 있음
 * 카드 한 장씩 뽑으면서 탐색
 * 처음 카드 + 처음 카드 X -> 1 코인 페어
 * 처음 카드 X + 처음 카드 X -> 2 코인 페어
 * 코인으로 페어를 소모하면 볼 수 있는 카드 2 장씩 증가
 * */
public class PG258707_n1카드게임 {
    public int solution(int coin, int[] cards) {
        int i;
        int len;
        int num;
        int end;
        int size;
        int pair1;
        int pair2;
        int[] cnt;
        
        len = cards.length;
        num = len + 1;
        cnt = new int[num];
        size = len / 3; // 처음 카드 개수
        end = size + 2; // 처음 카드 + 2 장은 항상 볼 수 있음
        for (i = 0; i < size; i++) { // 처음 카드 탐색
            if (cnt[cards[i]] == 0) { // 페어가 아직 뽑히지 않음
                cnt[num - cards[i]]--; // 탐색한 카드의 페어 음수 표시
            } else { // 페어가 존재
                end += 2; // 볼 수 있는 카드 2 장 증가
            }
        }
        pair1 = 0; // 1 코인 페어
        pair2 = 0; // 2 코인 페어
        for (;; i++) { // 카드 뽑기
            if (i >= end) { // 현재 볼 수 있는 카드 초과
                if (coin > 0 && pair1 != 0) { // 코인 1 개 이상 존재, 1 코인 페어 존재
                    pair1--; // 1 코인 페어 소모
                    coin--; // 코인 1 개 소모
                    end += 2; // 볼 수 있는 카드 2 장 증가
                } else if (coin > 1 && pair2 != 0) { // 코인 2 개 이상 존재, 2 코인 페어 존재
                    pair2--; // 2 코인 페어 소모
                    coin -= 2; // 코인 2 개 소모
                    end += 2; // 볼 수 있는 카드 2 장 증가
                } else { // 카드 증가 불가능
                    return end - size >> 1; // 턴 반환
                }
                if (end > len) { // 최대 턴까지 확보
                    return (num - size >> 1) + 1; // 최대 턴 반환
                }
            }
            if (cnt[cards[i]] == 0) { // 페어가 아직 뽑히지 않음
                cnt[num - cards[i]]++; // 탐색한 카드의 페어 양수 표시
            } else if (cnt[cards[i]] == 1) { // 페어 표시가 양수
                pair2++; // 페어가 처음 카드가 아니므로 2 코인 페어
            } else { // 페어 표시가 음수
                pair1++; // 페어가 처음 카드에 포함되어 있으므로 1 코인 페어
            }
        }
    }
}