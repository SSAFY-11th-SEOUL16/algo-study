import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static class Node{
		int clipboard, total, time;
		public Node(int clipboard, int total, int time) {
			this.clipboard = clipboard;
			this.total = total;
			this.time = time;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int s = Integer.parseInt(br.readLine());
		boolean [][] check = new boolean[1001][1001];
		
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(0, 1, 0));
		check[0][1] =true;
		
		while(!q.isEmpty()) {
			Node tmp = q.poll();
			if(tmp.total == s) {
				System.out.println(tmp.time);
				break;
			}
			
			q.offer(new Node(tmp.total, tmp.total, tmp.time+1));
		
			if(tmp.clipboard != 0 && tmp.total+tmp.clipboard <= s && !check[tmp.clipboard][tmp.total+tmp.clipboard]) {
				q.offer(new Node(tmp.clipboard, tmp.total+tmp.clipboard, tmp.time+1));
				check[tmp.clipboard][tmp.total+tmp.clipboard] = true;
			}
			
			if(tmp.total >= 1 && !check[tmp.clipboard][tmp.total-1]) {
				q.offer(new Node(tmp.clipboard, tmp.total-1, tmp.time+1));
				check[tmp.clipboard][tmp.total -1] = true;
			}
		}
		
	}
}
