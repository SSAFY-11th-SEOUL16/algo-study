import java.util.*;

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
