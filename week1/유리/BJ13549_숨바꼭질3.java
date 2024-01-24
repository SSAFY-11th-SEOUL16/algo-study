import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static boolean[] visted;
	static int max = 100000;
	static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tmp = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tmp.nextToken());
		K = Integer.parseInt(tmp.nextToken());
		
		visted = new boolean[max+1];
		bfs();
		System.out.println(result);
		
	}
	public static void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(N, 0));
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			visted[node.n] = true;
			if(node.n == K) {
				if(result > node.result) {
					result = node.result;
				}
			}
			
			if(node.n*2 <= max && visted[node.n*2]== false) {
				q.offer(new Node(node.n*2, node.result));
			}
			if(node.n+1 <= max && visted[node.n+1]== false) {
				q.offer(new Node(node.n+1, node.result+1));
			}
			if(node.n-1 >=0 && visted[node.n-1]== false) {
				q.offer(new Node(node.n-1, node.result+1));
			}
		}
	}
	public static class Node{
		int n;
		int result;
		
		public Node(int n, int result) {
			super();
			this.n = n;
			this.result = result;
		}
		
	}
}
