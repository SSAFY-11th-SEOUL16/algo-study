import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class BJ14226_이모티콘 {
	
	/**
	 * 80 ms
	 * BFS
	 * 복사, 붙여넣기, 삭제
	 * 방문하지 않은 화면, 클립보드 위치 큐에 삽입
	 * 화면이 S에 도달하면 거리 출력
	 */
	
	private static int bfs(int dest) { // BFS
		int display, clipboard, dist;
		int[] curr;
		boolean[][] visited;
		ArrayDeque<int[]> q;
		
		visited = new boolean[dest][dest]; // 방문 체크
		q = new ArrayDeque<>();
		q.addLast(new int[] {1, 0, 0}); // 화면, 클립보드, 시간
		while (!q.isEmpty()) {
			curr = q.pollFirst();
			display = curr[0];
			clipboard = curr[1];
			dist = curr[2] + 1;
			if (display + clipboard == dest) { // S에 도달
				return dist;
			}
			if (!visited[display][display]) { // 복사
				visited[display][display] = true;
				q.addLast(new int[] {display, display, dist});
			}
			if (display + clipboard < dest && !visited[display + clipboard][clipboard]) { // 붙여넣기
				visited[display + clipboard][clipboard] = true;
				q.addLast(new int[] {display + clipboard, clipboard, dist});
			}
			if (display > 1 && !visited[display - 1][clipboard]) { // 삭제
				visited[display - 1][clipboard] = true;
				q.addLast(new int[] {display - 1, clipboard, dist});
			}
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print(bfs(Integer.parseInt(br.readLine())));
	}
}
