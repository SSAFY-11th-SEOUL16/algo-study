//카드를 2장씩 뽑고, 우선순위에 따라 가장 코인을 적게 쓰는 경우를 선택
//위에 방식을 그리디하게 계속 반복
//우선순위는 1.가장 처음 가지고 있던 것들로만 제출 2.가지고 있던것 1개 뽑았던거 1개 3. 뽑았던거 2개
class PG258797_n1카드게임 {
    public int solution(int coin, int[] cards) {
        int answer = 0;
        int len = cards.length;
        
        int pos = 0;
        boolean[] now = new boolean[len+1];
        boolean[] free = new boolean[len+1];
        for(int i=0; i<len/3; i++) {
            now[cards[i]] = true;
            free[cards[i]] = true;
        }
        
        int round = 1;
        
        for(int i=len/3; i<len; i+=2) {
            now[cards[i]] = true;
            now[cards[i+1]] = true;
            
            int minCost = 3;
            int out = -1;
            boolean check = false;
            for(int j=1; j<=len/2; j++) {
                if(!now[j] || !now[len+1-j]) continue;
                
                int cost = (free[j] ? 0 : 1) + (free[len+1-j] ? 0 : 1);
                
                if(cost < minCost) {
                    minCost = cost;
                    out = j;
                    check = true;
                }
            }
            coin -= minCost;
            if(check && coin>=0) {
                round++;
                now[out] = false;
                now[len+1-out] = false;
            } else break;
        }
        
        return round;
    }
    
}
