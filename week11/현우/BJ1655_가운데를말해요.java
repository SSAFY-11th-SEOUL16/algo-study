import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1655_가운데를말해요 {
	
	/**
	 * 288 ms
	 * 세그먼트 트리
	 * -10000 ~ 10000 숫자의
	 * 등장 횟수 카운트 저장
	 * 구간합 세그 트리 구성
	 * 입력 받은 수 추가로 트리 업데이트
	 * 트리에서 가운데 숫자 서치
	 */
	
	private static final int START = -10000;
	private static final int END = 10000;
	
	private static int[] tree;
	
	private static void update(int node, int start, int end, int num) {
		int mid;
		
		tree[node]++; // 구간합 1 증가
		if (start == end) { // 리프 노드 도달
			return;
		}
		mid = (start + end) >> 1; // 범위 중앙 값
		if (num > mid) { // 숫자가 중앙 값보다 크면
			update(node << 1 | 1, mid + 1, end, num); // 오른쪽 자식 노드로 이동
		} else { // 숫자가 중앙 값보다 작거나 같으면
			update(node << 1, start, mid, num); // 왼쪽 자식 노드로 이동
		}
	}
	
	private static int search(int node, int start, int end, int idx) {
		int mid;
		
		if (start == end) { // 리프 노드 도달
			return start; // 숫자 반환
		}
		mid = (start + end) >> 1; // 범위 중앙 값
		if (tree[node << 1] < idx) { // 찾는 인덱스가 왼쪽 구간합 보다 크면
			return search(node << 1 | 1, mid + 1, end, idx - tree[node << 1]); // 오른쪽 자식 노드에서 탐색
		} // 오른쪽 자식 노드 탐색 시 찾아야 할 인덱스는 왼쪽 구간합만큼 빼줌
		return search(node << 1, start, mid, idx); // 왼쪽 자식 노드에서 탐색
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n, i;
		
		tree = new int[(END - START) << 2]; // 세그 트리
		n = Integer.parseInt(br.readLine());
		for (i = 0; i < n; i++) {
			update(1, START, END, Integer.parseInt(br.readLine())); // 입력 받은 수 추가로 트리 업데이트
			sb.append(search(1, START, END, (i >> 1) + 1)).append('\n'); // 가운데 숫자 찾기
		}
		System.out.print(sb);
	}
}
