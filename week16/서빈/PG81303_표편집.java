import java.util.Stack;
import java.util.StringTokenizer;

/*
 * java
 * 
 * - 각 명령어에 따라 해당 노드를 이동, 삭제, 복구하는 메소드를 Node 클래스에 정의함
 * - 명령어 처리를 위해 Stack을 사용하여 삭제된 노드들을 관리
 * - 최종적으로 모든 노드의 상태를 돌며 삭제된 노드는 'X', 그렇지 않은 노드는 'O'로 결과 문자열을 생성함
 */

class PG81303_표편집 {
	public static class Node {
		Node prev = null; // 이전 노드
		Node next = null; // 다음 노드
		boolean removed; // 노드가 삭제되었는지의 상태를 나타냄

		public Node movePrev(int movement) { // 주어진 칸 수만큼 이전 노드로 이동
			Node node = this;
			for (int i = 0; i < movement; i++) {
				node = node.prev;
			}

			return node;
		}

		public Node moveNext(int movement) { // 주어진 칸 수만큼 다음 노드로 이동
			Node node = this;
			for (int i = 0; i < movement; i++) {
				node = node.next;
			}

			return node;
		}

		public Node delete() { // 현재 노드를 삭제하고 이전 또는 다음 노드를 선택
			this.removed = true;
			Node prev = this.prev;
			Node next = this.next;

			if (prev != null) {
				prev.next = next;
			}

			if (next != null) {
				next.prev = prev;
				return next;
			}

			return prev;
		}

		public void restore() { // 삭제된 노드를 복구
			this.removed = false;
			Node prev = this.prev;
			Node next = this.next;

			if (prev != null) {
				prev.next = this;
			}

			if (next != null) {
				next.prev = this;
			}
		}
	}

	public static String solution(int n, int k, String[] cmd) {
		Stack<Node> deletedNodes = new Stack<>();
		Node[] nodes = new Node[n];

		for (int i = 0; i < n; i++) { // 모든 노드 초기화 및 양방향 연결
			nodes[i] = new Node();

			if (i == 0) {
				continue;
			}

			nodes[i - 1].next = nodes[i];
			nodes[i].prev = nodes[i - 1];
		}

		Node current = nodes[k];

		for (String command : cmd) {
			StringTokenizer st = new StringTokenizer(command);
			char operation = st.nextToken().charAt(0);

			switch (operation) {
			case 'U':
				int move = Integer.parseInt(st.nextToken());
				current = current.movePrev(move);
				break;
			case 'D':
				move = Integer.parseInt(st.nextToken());
				current = current.moveNext(move);
				break;
			case 'C':
				deletedNodes.add(current);
				current = current.delete();
				break;
			case 'Z':
				Node node = deletedNodes.pop();
				node.restore();
				break;
			}
		}

		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < n; i++) {
			if (nodes[i].removed) {
				answer.append("X");
				continue;
			}
			answer.append("O");
		}
		return answer.toString();
	}

	public static void main(String[] args) {
		System.out.println(solution(8, 2, new String[] { "D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z" }));
		System.out.println(
				solution(8, 2, new String[] { "D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C" }));
	}
}