package algorithm.programmers;

import java.util.*;

class Solution_불량사용자 {
    String[] user_id;
    String[] banned_id;
    boolean[] selected_id;
    List<Integer>[] canBeBannedId;
    
    int answer;
    
    public void perm(int idx, int bit){
        if(idx == banned_id.length){
            if(!selected_id[bit]){
                selected_id[bit] = true;
                answer++;
            }                
            return;
        }
        
        for(int i : canBeBannedId[idx]){
            int checkBit = (1 << i);
            if((bit & checkBit) == checkBit) continue;
            bit |= checkBit;
            perm(idx+1, bit);
            bit ^= checkBit;
        }
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        /*
            2^24 ~= 16,000,000 (x)
            8! ~= 40,320 (o)
        */
        answer = 0;
        
        this.user_id = user_id;
        this.banned_id = banned_id;
        
        selected_id = new boolean[1 << user_id.length];
        
        canBeBannedId = new List[banned_id.length];
        
        // banned_id에 포함될 수 있는 user_id 저장
        for(int i=0; i<banned_id.length; i++){
            canBeBannedId[i] = new ArrayList<Integer>();
            String banId = banned_id[i];
            for(int j=0; j<user_id.length; j++){
                String userId = user_id[j];
                if(banId.length() == userId.length()){
                    boolean isValid = true;
                    for(int k=0; k<banId.length(); k++){
                        char c = banId.charAt(k);
                        if(c == '*') continue;
                        char cu = userId.charAt(k);
                        if(c != cu){
                            isValid = false;
                            break;
                        }
                    }
                    if(isValid){
                        canBeBannedId[i].add(j);
                    }
                }
            }
        }
        
        perm(0,0); // ban idx, bit
        
        return answer;
    }
}