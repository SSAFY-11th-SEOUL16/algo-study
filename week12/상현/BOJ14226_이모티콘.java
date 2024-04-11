import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BFS 
 * 최소값을 찾아야 하므로 BFS 사용
 * visit[i][j] = 클립보드에 i개가 존재하며 화면에는 j개가 존재 하는 경우
 * 문제에서 나온 3가지 연산을 각각 수행하며 Q에 넣어준 후 가장 빠르게 S개의 이모티콘을 만들 경우를 확인
 * now[0] = 클립보드에 존재하는 이모티콘의 개수
 * now[1] = 화면에 현재 존재하는 이모티콘의 개수
 * now[2] = 현재 시간 
 */

public class BOJ14226_이모티콘 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int S = Integer.parseInt(st.nextToken());

        System.out.println(bfs(S));
    }

    private static int bfs(int s) {
        Queue<int[]> Q = new ArrayDeque<>();

        boolean[][] visit = new boolean[1001][1001];

        Q.offer(new int[] {0,1,0});
        visit[0][1] = true;
        while(!Q.isEmpty()) {
            int[] now = Q.poll();

            if (now[1] == s) {
                return now[2];
            }

            //화면에 붙여넣기 하는 경우 Q에 추가
            Q.add(new int[] {now[1], now[1], now[2] + 1});

            //클립보드에 붙여넣기 하는 경우
            //화면에 존재하는 이모티콘의 개수 + 클립보드에 있는 이모티콘의 개수가 S개를 넘지 않고 i개 클립,j개 화면 경우를 방문 x, 클립에 개수가 0개가 아닐때
            if (now[0] + now[1] <= s && !visit[now[0]][now[0] + now[1]] && now[0] != 0) {
                visit[now[0]][now[0] + now[1]] = true;
                Q.add(new int[] {now[0], now[0] + now[1], now[2] + 1});
            }

            //화면에 최소 1개 이상의 이모티콘이 존재하며 한개를 삭제했을 경우를 방문하지 않았는지 확인
            if (now[1] > 0 && !visit[now[0]][now[1] - 1]) {
                visit[now[0]][now[1] - 1] = true;
                Q.add(new int[] {now[0], now[1] - 1, now[2] + 1});
            }
        }

        return 0;
    }
}
