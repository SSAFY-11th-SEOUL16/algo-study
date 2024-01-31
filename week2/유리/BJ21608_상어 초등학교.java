import java.util.*;
import java.io.*;

public class Main {
	    static int N;
    static int[][] map;
    static int[][] like_list;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        like_list = new int[N*N+1][4];
        for (int i = 0; i < N*N; i++){
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++){
                like_list[number][j] = Integer.parseInt(st.nextToken());
            }
            Node node = calc_node(number);
            map[node.r][node.c] = number;
        }
        sol();
    }
    public static void sol(){
        int ans = 0;
        int tmp = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                int num = map[i][j];
                int[] like = like_list[num];
                for (int k = 0; k < 4; k++){
                    int newR = i + dr[k];
                    int newC = j + dc[k];
                    if (0 <= newR && newR < N && 0 <= newC && newC < N){
                        if (map[newR][newC] == like[0] || map[newR][newC] == like[1] || map[newR][newC] == like[2] || map[newR][newC] == like[3]){
                            tmp++;
                        }
                    }
                }
                if (tmp == 1) ans += 1;
                else if (tmp == 2) ans += 10;
                else if (tmp == 3) ans += 100;
                else if (tmp == 4) ans += 1000;
                tmp = 0;
            }
        }

        System.out.println(ans);
    }
    public static int[] calc_like(int r, int c, int number){
        int likes = 0;
        int empty = 0;
        int[] like = like_list[number];
        for (int i = 0; i < 4; i++){
            int newR = r + dr[i];
            int newC = c + dc[i];
            if (0 <= newR && newR < N && 0 <= newC && newC < N){
                if (map[newR][newC] == like[0] || map[newR][newC] == like[1] || map[newR][newC] == like[2] || map[newR][newC] == like[3]){
                    likes++;
                }
                else if (map[newR][newC] == 0) empty++;
            }
        }
        return new int[]{likes, empty};
    }
    public static Node calc_node(int number){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int r = 0; r < N; r++){
            for (int c = 0; c < N; c++){
                if (map[r][c] == 0){
                    int[] ans = calc_like(r, c, number);
                    int likes = ans[0];
                    int empty = ans[1];
                    pq.add(new Node(r, c, likes, empty));
                }
            }
        }
        return pq.poll();
    }
    public static class Node implements Comparable<Node>{
        int r, c, likes, empty;

        public Node(int r, int c, int like, int empty) {
            this.r = r;
            this.c = c;
            this.likes = like;
            this.empty = empty;
        }

        @Override
        public int compareTo(Node o) {
            if (this.likes == o.likes){
                if (this.empty == o.empty){
                    if (this.r == o.r) return this.c - o.c;
                    else return this.r - o.r;
                }
                else return o.empty - this.empty;
            }
            else return o.likes - this.likes;
        }
    }
}
