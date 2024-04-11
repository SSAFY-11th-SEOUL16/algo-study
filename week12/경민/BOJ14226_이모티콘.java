import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static boolean[][] visited = new boolean[2001][2001]; // [count][clipCount] 방문체크

	/*
	 * 1. 클립보드 값을 현재 이모티콘 값으로 변경 => 클립보다 값이 현재 이모티콘 값이 아닌 경우 복사가능 
	 * 2. 클립보드 값을 현재 이모티콘 개수에 더하기
	 *  3. 이모티콘 개수에서 -1
	 */

	static class Node {
		int count;
		int clipCount;
		int time;

		public Node(int count, int clipCount, int time) {
			this.count = count;
			this.clipCount = clipCount;
			this.time = time;
		}
	}

	static int bfs() {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(1, 0, 0));
		visited[1][0] = true;
		while(!q.isEmpty()) {
			Node node = q.poll();
			
			if(node.count > n) continue;
			
			if(node.count == n) 
				return node.time;
			
			// 1. 
			if(node.clipCount != node.count)
				q.offer(new Node(node.count, node.count, node.time + 1));
			// 2.
			if(!visited[node.count + node.clipCount][node.clipCount]) {
				visited[node.count + node.clipCount][node.clipCount] = true;
				q.offer(new Node(node.count + node.clipCount, node.clipCount, node.time + 1));
			}
			 // 3. 
			 if(node.count > 1 && !visited[node.count - 1][node.clipCount]) { 
				visited[node.count - 1][node.clipCount] = true;
				 q.offer(new Node(node.count -1 , node.clipCount, node.time + 1));
			 }
		}
		
		return 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		System.out.println(bfs());

	}

}