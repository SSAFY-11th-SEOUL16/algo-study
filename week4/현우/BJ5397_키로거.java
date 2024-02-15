import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ5397_키로거 {
	/*
	 * 388 ms
	 * 
	 * 원형 LinkedList
	 * 노드 번호 = 입력 문자열(str)에서의 인덱스
	 * 초기 노드 번호 = len
	 * 왼쪽 노드 배열 left[], 오른쪽 노드 배열 right[]
	 * '<': 왼쪽 노드로 이동(curr != len)
	 * '>': 오른쪽 노드로 이동(right[curr] != len)
	 * '-': 현재 노드 삭제(curr != len)
	 * 	right[왼쪽 노드] = 오른쪽 노드, left[오른쪽 노드] = 왼쪽 노드
	 * 그 외: 현재 노드 오른쪽에 i 노드 삽입
	 * 	right[현재 노드] = i, left[i] = 현재 노드
	 * 	right[i] = 오른쪽 노드, left[오른쪽 노드] = i
	 * right[len]부터 right를 따라가면서 len 전까지 str[노드 번호] 출력
	 */
	
	private static int[] left, right;
	
	private static void solution(BufferedReader br, StringBuilder sb) throws IOException {
		int len, curr, prev, next, i;
		char[] str;

		str = br.readLine().toCharArray();
		len = str.length;
		left[len] = len; // 초기 노드 번호 len
		right[len] = len;
		curr = len; // 현재(curr) 노드 len으로 시작
		for (i = 0; i < len; i++) {
			switch (str[i]) {
			case '-': // 현재 노드 삭제
				if (curr != len) { // 초기 노드가 아닐때만
					prev = left[curr];
					next = right[curr];
					right[prev] = next; // 왼쪽(prev) 노드와 오른쪽(next) 노드 연결
					left[next] = prev;
					curr = prev; // curr 왼쪽(prev)으로 한 칸 이동
				}
				break;
			case '<': // 왼쪽 이동
				if (curr != len) { // 초기 노드가 아니면
					curr = left[curr]; // curr 왼쪽으로 한 칸 이동
				}
				break;
			case '>': // 오른쪽 이동
				if (right[curr] != len) { // 오른쪽 노드가 초기 노드가 아니면
					curr = right[curr]; // curr 오른쪽으로 한 칸 이동
				}
				break;
			default: // i 노드 삽입
				next = right[curr];
				right[curr] = i; // 현재(curr) 노드와 i 노드 연결
				left[i] = curr;
				right[i] = next; // 오른쪽(next) 노드와 i 노드 연결
				left[next] = i;
				curr = i; // 현재 노드 i로 설정
			}
		}
		// 초기(len) 노드 오른쪽부터 right 따라가면서 len으로 돌아오기 전까지 출력
		for (curr = right[len]; curr != len; curr = right[curr]) {
			sb.append(str[curr]);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t, testCase;

		left = new int[1000001]; // 문자열 최대 길이 1,000,000
		right = new int[1000001]; // 초기 노드 len 자리(+ 1)
		t = Integer.parseInt(br.readLine());
		for (testCase = 0; testCase < t; testCase++) {
			solution(br, sb);
			sb.append('\n');
		}
		System.out.print(sb);
	}
}
