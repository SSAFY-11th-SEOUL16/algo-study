import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

//Java8(124ms)
public class BJ14725_개미굴 {

	//root노드 생성
	//Node 클래스는 문자값과 자식노드 TreeMap으로 설정
	//TreeMap으로 한 이유는 존재하는지 확인을 key값으로 확인하는데 logN에 해결가능, 정렬보장
	static Node root = new Node("");
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int dep = Integer.parseInt(st.nextToken());
			Node tmp = root;
			for(int j=1; j<=dep; j++) {
				String value = st.nextToken();
				//만약 문자가 자식노드에 존재한다면 해당 자식 노드로 내려간다.
				//존재하지 않는다면 새로운 노드를 생성한다.
				if(tmp.child.containsKey(value)) {
					tmp = tmp.child.get(value);
				} else {
					Node node = new Node(value);
					tmp.child.put(value, node);
					tmp = node;
				}
			}
		}
		print();
	}
	
	public static void print() {
		StringBuilder res = new StringBuilder();
		dfs(0, root, res, new StringBuilder());
		System.out.println(res);
	}
	
	//우선순위가 높은 자식부터 순회하면서 결과 저장
	public static void dfs(int dep, Node tmp, StringBuilder res, StringBuilder prefix) {
		for(String value: tmp.child.keySet()) {
			res.append(prefix).append(value).append("\n");
			prefix.append("--");
			dfs(dep+1, tmp.child.get(value), res, prefix);
			prefix.delete(dep, dep+2);
		}
	}
	
	static class Node{
		String value;
		TreeMap<String, Node> child = new TreeMap<>();
		Node(String value) {
			this.value = value;
		}
	}
}
