import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/*
 * java
 * 88ms
 * 
 * - BFS
 * 
 * - 현재 화면의 이모티콘 수, 클립보드의 이모티콘 수, 그리고 현재까지의 시간을 기록하며 큐를 이용한 BFS
 * - 화면과 클립보드의 상태를 배열을 이용해 기록, 이미 같은 상태가 방문된 적이 있는지 확인하여 중복 탐색을 방지
 * - 현재 상태에서 가능한 3가지 연산(복사, 붙여넣기, 삭제)을 각각 수행하고, 그 결과를 큐에 추가
 */

public class BJ14226_이모티콘 {
	static int S, result = 0;
	static Deque<Emoticon> queue = new ArrayDeque<>();
	static boolean[][] visited;

	static class Emoticon {
		int screen, clipboard, cnt;

		public Emoticon(int screen, int clipboard, int cnt) {
			this.screen = screen;
			this.clipboard = clipboard;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = Integer.parseInt(br.readLine());
		visited = new boolean[S + 1][S + 1];

		queue.add(new Emoticon(1, 0, 0));
		visited[1][0] = true;

		while (!queue.isEmpty()) {
			Emoticon current = queue.poll();
			int cscreen = current.screen;
			int cclipboard = current.clipboard;
			int ccnt = current.cnt;

			if (cscreen == S) {
				System.out.println(ccnt);
				return;
			}
			if (!visited[cscreen][cscreen]) {
				visited[cscreen][cscreen] = true;
				queue.add(new Emoticon(cscreen, cscreen, ccnt + 1));
			}
			if (cscreen + cclipboard <= S && !visited[cscreen + cclipboard][cclipboard]) {
				visited[cscreen + cclipboard][cclipboard] = true;
				queue.add(new Emoticon(cscreen + cclipboard, cclipboard, ccnt + 1));
			}
			if (cscreen - 1 >= 0 && !visited[cscreen - 1][cclipboard]) {
				visited[cscreen - 1][cclipboard] = true;
				queue.add(new Emoticon(cscreen - 1, cclipboard, ccnt + 1));
			}
		}
	}
}