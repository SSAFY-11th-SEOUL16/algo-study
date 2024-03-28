import java.util.Arrays;
import java.util.HashSet;

class Solution {
	static HashSet<String> result = new HashSet<>();
	static boolean [] check;
    public int solution(String[] user_id, String[] banned_id) {
        check = new boolean[user_id.length];
		
		for(int i = 0; i < banned_id.length; i++) {
			banned_id[i] = banned_id[i].replace("*", ".");
		}
		
		dfs(0,"", user_id, banned_id);
		
		int answer = result.size();
        return answer;
    }
    static void dfs(int count, String setData, String[] user_id, String[] banned_id) {
		if(count == banned_id.length) {
			String [] data = setData.split(" ");
			Arrays.sort(data);
			
			String resultString = "";
			for(int i = 0; i < data.length; i++) {
				resultString+=data[i];
			}
			
			result.add(resultString);
			return;
		}
		
		for(int i =  0; i < user_id.length; i++) {
			if(check[i] || !user_id[i].matches(banned_id[count])) {continue;}
			check[i] = true;
			dfs(count+1, setData+user_id[i]+" ",user_id, banned_id);
			check[i] = false;
		}
	}
}
