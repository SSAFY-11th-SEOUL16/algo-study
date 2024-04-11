import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


//Java8, 84ms
public class BJ14226_이모티콘 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int s = Integer.parseInt(br.readLine());
		Queue<int[]> q = new LinkedList<>();
		//(화면,클립보드) 방문처리 배열 생성
		boolean[][] visited = new boolean[1055][1055];
		q.add(new int[] {1,0});
		visited[1][0] = true;
		int cnt = 0;
		boolean flag = false;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0; i<size; i++) {
				int[] tmp = q.remove();
				int screen = tmp[0];
				int clip = tmp[1];
				//s개의 이모티콘 만들면 종료
				if(screen == s) {
					flag = true;
					break;
				}
				//화면에 있는 이모티콘을 모두 복사해서 클립보드 저장
				if(!visited[screen][screen]) {
					q.add(new int[] {screen,screen});
					visited[screen][screen] = true;
				}
				//클립보드에 있는 모든 이모티콘 화면에 붙여넣기
				if(screen+clip<=1000 && !visited[screen+clip][clip]) {
					q.add(new int[] {screen+clip, clip});
					visited[screen+clip][clip] = true;
				}
				//화면에 있는 이모티콘 중 하나 삭제
				if(screen>1 && !visited[screen-1][clip]) {
					q.add(new int[] {screen-1, clip});
					visited[screen-1][clip] = true;
				}
			}
			if(flag) break;
			cnt++;
		}
		System.out.println(cnt);
	}
}
