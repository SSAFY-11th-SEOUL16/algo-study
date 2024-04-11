import java.io.*;
import java.util.*;

public class BOJ14226_이모티콘 {
    static int s;
    static boolean visited[][];

    static class Node {
        int v, clip, time; //화면의 이모티콘 수, 클립보드의 이모티콘 수, 걸린 시간

        public Node(int v, int clip, int time) {
            this.v = v;
            this.time = time;
            this.clip = clip;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = Integer.parseInt(br.readLine()); //구하려는 이모티콘 수
        visited = new boolean[s*2][s*2];
        bfs();
    }

    static void bfs() {
        Deque<Node> q = new ArrayDeque<>();
        q.addFirst(new Node(1,1, 1));
        visited[1][1] = true;

        while(!q.isEmpty()) {
            Node cur = q.pollFirst();

            //원하는 이모티콘 개수를 만족한 경우
            if(cur.v == s) {
                System.out.println(cur.time);
                break;
            }

            //원하는 이모티콘보다 현재 이모티콘의 수가 적을 경우
            if(cur.v < s) {
                //클립보드에 저장
                if(cur.v > cur.clip) {
                    if(!visited[cur.v][cur.v]) {
                        visited[cur.v][cur.v] = true;
                        q.addLast(new Node(cur.v, cur.v, cur.time + 1));
                    }
                }
                //화면에 추가
                if(!visited[cur.v + cur.clip][cur.clip]) {
                    visited[cur.v + cur.clip][cur.clip] = true;
                    q.addLast(new Node(cur.v + cur.clip, cur.clip, cur.time + 1));
                }
            }

            //원하는 이모티콘보다 현재 이모티콘의 수가 많을 경우
            if(1 < cur.v) {
                //화면의 이모티콘 1개 삭제
                if(visited[cur.v - 1][cur.clip]) continue;
                visited[cur.v - 1][cur.clip] = true;
                q.addLast(new Node(cur.v - 1, cur.clip, cur.time + 1));
            }
        }
    }
}
