import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Java8 232ms
//크루스칼 알고리즘으로 MST를 구한 후 남은 랜선들을 리턴하면 된다.
public class BJ1414_불우이웃돕기 {
	
	static List<Edge> edges = new ArrayList<>(); 
	static int[] parents;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		parents = new int[n];
		for(int i=0; i<n; i++) {
			parents[i] = i;
		}
		int sum = 0;
		for(int i=0; i<n; i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j=0; j<n; j++) {
				if(tmp[j] == '0') continue;
				//대문자의 경우 27부터 저장(65-38=27)
				if(Character.isUpperCase(tmp[j])) {
					sum += tmp[j]-38;
					edges.add(new Edge(i, j, tmp[j]-38));
				//소문자의 경우 1부터 저장
				} else {
					sum +=  tmp[j]-'a'+1;
					edges.add(new Edge(i, j, tmp[j]-'a'+1));
				}
			}
		}
		
		
		Collections.sort(edges, (o1, o2) -> o1.len - o2.len);
		int min = 0;
		for(Edge edge: edges) {
			int p1 = findParent(edge.start);
			int p2 = findParent(edge.end);
			if(p1 != p2) {
				min += edge.len;
				union(p1, p2);
			}
		}
		boolean connect = true;
		//루트를 제외한 parent가 자기 자신이라면 연결되어있지 않은것
		for(int i=1; i<n; i++) {
			if(parents[i] == i) connect=false;
		}
		System.out.println(connect ? sum-min : -1);
	}
	
	public static void union(int p1, int p2) {
		if(p1 <= p2) {
			parents[p2] = p1;
		} else {
			parents[p1] = p2;
		}
	}
	
	public static int findParent(int n) {
		if(parents[n] == n) return n;
		return findParent(parents[n]);
	}

	static class Edge {
		int start;
		int end;
		int len;
		
		Edge(int start, int end, int len) {
			this.start = start;
			this.end = end;
			this.len = len;
		}
	}
}
