import java.util.Arrays;
import java.util.HashSet;

/**
 * 순열
 * 순열에서 중복 제거를 위해 HastSet 사용
 * 사용자 ID들을 벤 리스트의 사이즈 만큼 순열을 만들어줌 
 * 만들어진 순열과 벤 리스트의 i번째를 비교하여 일치 비교
 * 일치하다면 sort를 통해 정렬 후 각 id를 합쳐 하나의 문자열로 만든 후 set에 add
 * set의 사이즈를 return
 */
class Solution {
    private static HashSet<String> set = new HashSet<>();
    private static boolean[] visit;

    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        visit = new boolean[user_id.length];
        func(0, new String[banned_id.length], user_id, banned_id);
        return set.size();
    }

    private static void func(int L, String[] list, String[] user_id, String[] banned_id) {
        if (L == banned_id.length) {
            String[] tmp = Arrays.copyOf(list, list.length);

            //만들어진 순열과 벤 리스트의 i번째를 비교하여 일치 비교
            for (int i = 0; i < banned_id.length; i++) {
                //자리수가 다르다면 return
                if (tmp[i].length() != banned_id[i].length()) return;
                for (int j = 0; j < banned_id[i].length(); j++) {
                    if (banned_id[i].charAt(j) == '*') continue;
                    //j번 자리가 다르다면 불가능한 경우이므로 return
                    if (tmp[i].charAt(j) != banned_id[i].charAt(j)) {
                        return;
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            Arrays.sort(tmp);

            for (int i = 0; i < tmp.length; i++) {
                sb.append(tmp[i]);
            }
            set.add(sb.toString());
            return;
        }

        //순열 생성
        for (int i = 0; i < user_id.length; i++) {
            if (!visit[i]) {
                visit[i] = true;
                list[L] = user_id[i];
                func(L + 1, list, user_id, banned_id);
                visit[i] = false;
            }
        }
    }

}