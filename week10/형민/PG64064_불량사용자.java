import java.io.*;
import java.util.*;

class PG64064_불량사용자 {
	int userIdlen = 0, bannedIdLen = 0;
	boolean visited[];
	HashSet<String> set = new HashSet<>();

	public int solution(String[] user_id, String[] banned_id) {
		int answer = 0;
		userIdlen = user_id.length; //사용자 아이디 수
		bannedIdLen = banned_id.length; //블량 사용자 아이디 수
		visited = new boolean[userIdlen];

		comb(0, user_id, banned_id);

		return set.size();
	}

	public void comb(int cnt, String[] user_id, String[] banned_id) {
		//불량 사용자 아이디를 모두 찾았을 때
		if (cnt == bannedIdLen) {
			String str = "";
			for (int i = 0; i < userIdlen; i++) {
				if (visited[i]) {
					str += user_id[i] + " ";
				}
			}

			set.add(str);
			return;
		}

		String cur = banned_id[cnt];
		for (int i = 0; i < userIdlen; i++) {
			if (visited[i])	continue;
			String findId = user_id[i];

			//가능한 문자열인지 확인
			if (cur.length() != findId.length()) continue;

			boolean flag = false;
			for (int j = 0; j < findId.length(); j++) {
				if (cur.charAt(j) != findId.charAt(j)) {
					if (cur.charAt(j) != '*') {
						flag = true;
						break;
					}
				}
			}

			if (flag) continue;
			
			visited[i] = true;
			comb(cnt + 1, user_id, banned_id);
			visited[i] = false;
		}
	}
}
