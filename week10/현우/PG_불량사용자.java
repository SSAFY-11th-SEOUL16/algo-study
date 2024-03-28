import java.util.ArrayList;

public class PG_불량사용자 {
	
	/**
	 * 11.05ms
	 * 가능한 banned, user 아이디 쌍에 간선 추가
	 * user 인덱스는 비트로 저장
	 * banned 인덱스 기준으로 DFS
	 * user 인덱스를 표시하면서 탐색
	 * 방문되지 않은 조합에서 answer + 1
	 */
	
	private static final char WILDCARD = '*';
	
	private static int answer, bannedNum;
	private static boolean[] visited;
	private static ArrayList<ArrayList<Integer>> adj;
	
	private boolean isConnected(String userId, String bannedId) {
		int len, i;
		
		if ((len = userId.length()) != bannedId.length()) { // 아이디 길이가 다르면
			return false; // 연결 불가
		}
		for (i = 0; i < len; i++) { // 문자 하나씩 비교
			if (userId.charAt(i) != bannedId.charAt(i) && bannedId.charAt(i) != WILDCARD) {
				return false;
			}
		}
		return true;
	}
	
	private void dfs(int bit, int depth) {
		if (depth == bannedNum) {
			if (!visited[bit]) { // 방문하지 않은 조합
				answer++;
				visited[bit] = true;
			}
			return;
		}
		for (int idx : adj.get(depth)) { // 간선 탐색
			if ((bit & idx) != 0) { // 앞에서 사용한 user idx
				continue;
			}
			dfs(bit | idx, depth + 1); // idx 사용 처리 후 다음 banned로 이동
		}
	}

	public int solution(String[] user_id, String[] banned_id) {
		int userNum, bit, i, j;
		
		userNum = user_id.length;
		bannedNum = banned_id.length;
		adj = new ArrayList<>(bannedNum);
		for (i = 0; i < bannedNum; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0, bit = 1; i < userNum; i++, bit <<= 1) {
			for (j = 0; j < bannedNum; j++) {
				if (isConnected(user_id[i], banned_id[j])) { // 가능한 쌍이면
					adj.get(j).add(bit); // 간선 추가
				}
			}
		}
		answer = 0;
		visited = new boolean[1 << userNum]; // 정답 조합 방문 여부
		dfs(0, 0);
		return answer;
    }
}