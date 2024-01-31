import java.util.*;
import java.io.*;

class Main {
    static int n; //셀의 개수
    static int cur; //현재 학생 번호
    static int arr[][]; //앉아있는 학생 자리
    static int manArr[][]; //만족도
    static PriorityQueue<Node> seatPq; //갈 수 있는 자리
    static int likeArr[]; //좋아하는 학생 4명 번호
    static HashMap<Integer, int[]> map = new HashMap<>(); //학생마다 좋아하는 학생 4명 번호 Map

    static class Node implements Comparable<Node>{
        int x, y;
        int emptyCnt; //빈 칸 수
        int likeCnt; //좋아하는 인접 학생 수

        public Node(int x, int y, int emptyCnt, int likeCnt) {
            this.x = x;
            this.y = y;
            this.emptyCnt = emptyCnt;
            this.likeCnt = likeCnt;
        }

        @Override
        public int compareTo(Node o) {
            if(o.likeCnt == this.likeCnt){
                if(o.emptyCnt == this.emptyCnt){
                    if(o.x == this.x){
                        return this.y - o.y;
                    } else return this.x - o.x;
                } else {
                    return o.emptyCnt - this.emptyCnt;
                }
            } else {
                return o.likeCnt - this.likeCnt;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        manArr = new int[n][n];

        for (int i = 0; i < n * n; i++) {
            st = new StringTokenizer(br.readLine());
            cur = Integer.parseInt(st.nextToken());

            seatPq = new PriorityQueue<>();
            likeArr = new int[4];
            for (int j = 0; j < 4; j++) {
                likeArr[j] = Integer.parseInt(st.nextToken());
            }
            map.put(cur, likeArr);

            check();
            Node curNode = seatPq.poll();
            arr[curNode.x][curNode.y] = cur;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    int nx = i + rx[k];
                    int ny = j + ry[k];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue; //범위 초과
                    if(arr[nx][ny] == 0) continue; //빈 칸

                    likeArr = map.get(arr[i][j]);
                    for (int z = 0; z < 4; z++) {
                        if(likeArr[z] == arr[nx][ny]) {
                            if(manArr[i][j] == 0) manArr[i][j]++;
                            else manArr[i][j] *= 10;
                        }
                    }
                }
            }
        }

        long sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += manArr[i][j];
            }
        }
        System.out.println(sum);
    }

    static int[] rx = {-1, 1, 0, 0};
    static int[] ry = {0, 0, -1, 1};

    //갈 수 있는 좌석 우선순위 큐에 저장
    static void check() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(arr[i][j] != 0) continue; //비어있지 않은 칸

                int emptyCnt = 0; //빈 칸 수
                int likeCnt = 0; //좋아하는 인접 학생 수

                //인접한 빈 칸 수, 좋아하는 인접 학생 수 확인
                for (int k = 0; k < 4; k++) {
                    int nx = i + rx[k];
                    int ny = j + ry[k];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue; //범위 초과

                    //인접한 빈 칸 수
                    if(arr[nx][ny] == 0) {
                        emptyCnt++;
                        continue; //빈 칸
                    }

                    //좋아하는 인접 학생 수
                    for (int l = 0; l < 4; l++) {
                        if(likeArr[l] == arr[nx][ny]) {
                            likeCnt++;
                        }
                    }
                }

                seatPq.add(new Node(i, j, emptyCnt, likeCnt));
            }
        }
    }
}
