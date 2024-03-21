import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
 * java
 * 192 ms
 * 트리
 * 
 * 먹이 정보를 트리 구조를 구성
 * 새로운 먹이 정보가 들어올 때마다 해당 먹이가 이미 트리에 존재하는지 확인
 * 존재하지 않으면 새 노드를 추가하여 트리를 확장
 */

public class BJ14725_개미굴 {
	static int N;

	static class Node {
		HashMap<String, Node> child = new HashMap<>(); // 현재 노드의 자식 노드 저장
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		Node root = new Node(); // 개미굴의 시작점

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			Node cur = root;

			for (int j = 0; j < size; j++) {
				String s = st.nextToken();

				if (!cur.child.containsKey(s)) { // 해당 이름을 키로 하는 자식 노드가 없다
					cur.child.put(s, new Node()); // 새로운 노드를 생성하여 HashMap에 추가
				}
				cur = cur.child.get(s); // 다음 먹이를 현재 노드의 자식 노드로 처리
			}
		}
		print(root, "");
	}

	public static void print(Node root, String bar) {
		Object[] key = root.child.keySet().toArray(); // 현재 노드의 모든 자식 노드의 이름을 배열로 추출
		Arrays.sort(key);

		for (Object s : key) {
			System.out.println(bar + s);
			print(root.child.get(s), bar + "--");
		}
	}
}