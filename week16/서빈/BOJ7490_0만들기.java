import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * java
 * 264 ms
 * 
 * DFS
 * 
 * - 수열을 조합하여 결과가 0이 되는 모든 경우를 찾음
 * - 숫자 사이에 +, -, 공백을 삽입하여 다양한 수식을 생성하고 이를 DFS로 탐색
 * - 수식 계산은 문자열 파싱 후 각 요소를 계산하는 방식으로 처리
 * - 결과 리스트를 사전 순으로 정렬 후 출력
 */

public class BOJ7490_0만들기 {
	static int N;
	static StringBuilder sb = new StringBuilder();
	static List<String> answerList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			answerList = new ArrayList<>();
			dfs(1, "1");
			Collections.sort(answerList); // 정렬
			for (String s : answerList) {
				System.out.println(s);
			}
			System.out.println();
		}
	}

	public static void dfs(int depth, String str) {
		if (depth == N) {
			String express = str.replaceAll(" ", ""); // 계산의 편의를 위하여 공백 제거하여 실제 계산할 문자열 생성
			if (calculate(express)) // 계산 결과가 0인 경우
				answerList.add(str); // 결과 리스트에 추가
			return;
		}
		dfs(depth + 1, str + "+" + (depth + 1)); // + 연산자를 사용
		dfs(depth + 1, str + "-" + (depth + 1)); // - 연산자를 사용
		dfs(depth + 1, str + " " + (depth + 1)); // 공백 (숫자 연결)을 사용
	}

	public static boolean calculate(String str) { // 문자열 수식을 계산
		StringTokenizer st = new StringTokenizer(str, "-|+", true); // 연산자를 기준으로 토큰화
		int sum = Integer.parseInt(st.nextToken()); // 첫 번째 수를 초기 합계로 설정
		while (st.hasMoreElements()) {
			String tmp = st.nextToken(); // 다음 토큰(연산자)을 읽음
			if (tmp.equals("+")) {
				sum += Integer.parseInt(st.nextToken());
			} else {
				sum -= Integer.parseInt(st.nextToken());
			}
		}
		if (sum == 0) // 합계가 0인 경우
			return true;
		return false; // 그 이외
	}
}