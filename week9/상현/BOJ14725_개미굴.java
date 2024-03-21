import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
/**
 * 트라이(?)
 * 128ms
 * 풀이 참고
 * 트라이라는 자료구조를 이용,  
 * 트라이 - 문자열을 저장하고 효율적으로 탐색하기 위한 트리 형태의 자료구조이며 Map을 활용
 * 해당 문제에서는 문자가 아닌 문자열이 key값으로 지정되기 때문에 String으로 지정
 * print를 하기 위해 재귀 활용 
 */
public class BOJ14725_개미굴 {
	private static StringBuilder sb = new StringBuilder();
	private static class Node {
		Map<String, Node> node = new HashMap<>();
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		Node root = new Node();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int depth = Integer.parseInt(st.nextToken());
			Node cur = root;
			for (int j = 0; j < depth; j++) {
				String s = st.nextToken();
				cur.node.putIfAbsent(s, new Node());
				cur = cur.node.get(s);
			}
		}
		
		print(root, new StringBuilder(), 0);
		System.out.println(sb);
	}
	
	private static void print(Node n, StringBuilder sb2, int depth) {
		Object[] keys = n.node.keySet().toArray();
		Arrays.sort(keys);
		
		for (Object object : keys) {
			sb.append(sb2).append((String) object).append("\n");
			print(n.node.get((String) object), sb2.append("--"), depth + 1);
			sb2.delete(depth * 2, depth * 2 + 2);
		}
	}


}
