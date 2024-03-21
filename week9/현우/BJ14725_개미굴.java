import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BJ14725_개미굴 {
	
	/**
	 * 116 ms
	 * 문자열 사전순 - TreeMap으로 정렬
	 * 노드 :
	 * 층수 저장
	 * 자식노드 TreeMap<문자열, 노드>로 저장
	 * 트리 구성 후 전위 순회 하면서 문자열 출력
	 */
	
	private static final String LINE = "--";
	
	private static final class Node {
		int floor; // 층수
		TreeMap<String, Node> childs;
		
		Node(int floor) {
			this.floor = floor;
			this.childs = new TreeMap<>();
		}
	}
	
	private static Node child;
	private static String str;
	private static StringBuilder sb;
	private static String[] lines;
	
	private static void push(Node node, StringTokenizer st) { // 트리에 추가
		str = st.nextToken(); // 문자열
		child = node.childs.get(str); // 문자열에 해당하는 자식 노드
		if (child == null) { // 해당 문자열이 TreeMap key중에 없으면
			child = new Node(node.floor + 1); // 노드 생성
			node.childs.put(str, child); // TreeMap에 <문자열, 노드> 추가
		}
		if (st.hasMoreTokens()) { // 문자열이 더 있으면
			push(child, st); // 자식 노드로 이동
		}
	}
	
	private static void dfs(Node node) {
		String line;
		
		line = lines[node.floor]; // 깊이를 나타낼 -- 선
		for (Entry<String, Node> entry : node.childs.entrySet()) { // TreeMap의 Entry 순회
			sb.append(line).append(entry.getKey()).append('\n'); // -- 선과 문자열 출력
			if (!entry.getValue().childs.isEmpty()) { // 문자열에 해당하는 노드에 자식이 있으면
				dfs(entry.getValue()); // 해당 노드로 이동
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, max, i;
		Node root;
		
		n = Integer.parseInt(br.readLine());
		root = new Node(0); // 개미굴 입구
		max = 0;
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			max = Math.max(max, Integer.parseInt(st.nextToken())); // 트리 깊이 저장
			push(root, st); // 트리에 추가
		}
		lines = new String[max]; // 층수에 따른 -- 선 저장
		lines[0] = "";
		for (i = 1; i < max; i++) {
			lines[i] = new StringBuilder(lines[i - 1]).append(LINE).toString();
		}
		sb = new StringBuilder(); // 출력 문자열
		dfs(root); // DFS로 전위 순회
		System.out.print(sb);
	}
}
