import java.util.*;

//금지된 아이디로 가능한 유저 아이디를 재귀를 통해 체크
//visited 비트를 통해 가능한 유저 경우 계산(10011 이면 1번, 4번, 5번이 금지된 경우와 매치)
//중복된 경우가 있을 수 있으므로 Set배열에 저장
//Set의 크기를 구한다.

class PG64064_불량사용자 {
    int answer = 0;
    int userLen, bannedLen;
    String[] user_id, banned_id;
    Set<Integer> set = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        userLen = user_id.length;
        bannedLen = banned_id.length;
        this.user_id = user_id;
        this.banned_id = banned_id;
        dfs(0, 0);
        return set.size();
    }
    
    public void dfs(int idx, int visited) {
        if(idx == bannedLen) {
            set.add(visited);
            return;
        }
        
        for(int i=0; i<userLen; i++) {
            if((visited&(1<<i))!=(1<<i) && match(user_id[i], banned_id[idx])) {
                dfs(idx+1, visited|(1<<i));
            }
        }
    }
    //사용자 아이디와 금지 아이디가 동일한지 확인
    public boolean match(String user, String banned) {
        if(user.length() != banned.length()) return false;
        for(int i=0; i<user.length(); i++) {
            if(user.charAt(i) != banned.charAt(i) && banned.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }
}
