import java.util.Arrays;
import java.util.HashSet;

/*
 * java
 * dfs
 * 
 * 정규 표현식을 사용하기 위하여 *를 .으로 변환
 * dfs를 통하여 불량유버 개수만큼 조합을 생성
 * 조합된 아이디를 정렬하고 문자열로 변환
 * HashSet에 추가하여 중복 제거
 */

class PG_64064_불량_사용자 {
	private static HashSet<String> hashSet;
	private static boolean[] visited; // 방문 배열

	public static int solution(String[] user_id, String[] banned_id) {
		hashSet = new HashSet<>();
		visited = new boolean[user_id.length];

		for (int i = 0; i < banned_id.length; i++) {
			banned_id[i] = banned_id[i].replace("*", "."); // 정규 표현식을 사용하기 위한 변환
		}

		dfs(0, "", user_id, banned_id);
		return hashSet.size();
	}

	public static void dfs(int depth, String str, String[] user_id, String[] banned_id) {
		if (depth == banned_id.length) {
			String[] tmp = str.split("\\ ");
			Arrays.sort(tmp);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < tmp.length; i++) {
				sb.append(tmp[i]);
			}
			hashSet.add(sb.toString()); // HashSet에 추가하여 중복 제거
			return;
		}

		for (int i = 0; i < user_id.length; i++) {
			if (visited[i] || !user_id[i].matches(banned_id[depth])) { // 이미 방문했거나, 불량 사용자 패턴과 매치되지 않는 경우는 스킵
				continue;
			} else {
				visited[i] = true;
				dfs(depth + 1, str + " " + user_id[i], user_id, banned_id);
				visited[i] = false;
			}
		}
	}
}