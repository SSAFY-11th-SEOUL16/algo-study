import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    static boolean[] visited;
    static Set<String> set = new HashSet<>();

    public static void dfs(int banId, String combi, String[] banned_id, String[] user_id) {
        if (banId == banned_id.length) {
            String[] arr = combi.split(" ");
            Arrays.sort(arr); // 조합의 중복여부 판단을 위해

            String str = "";
            for (String s : arr)
                str += s;
            set.add(str);
            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            if (!visited[i] && user_id[i].matches(banned_id[banId])) {
                visited[i] = true;
                dfs(banId + 1, combi + " " +  user_id[i], banned_id, user_id);
                visited[i] = false;
            }
        }
    }

    public static int solution(String[] user_id, String[] banned_id) {
        visited = new boolean[user_id.length];

        // 특정 문자열을 탐색할 때, . 은 모든 문자로 대체될 수 있으므로 * 을 . 으로 변경
        for (int i = 0; i < banned_id.length; i++)
            banned_id[i] = banned_id[i].replace('*', '.');

        dfs(0, "", banned_id, user_id); // 조합

        return set.size();
    }
}