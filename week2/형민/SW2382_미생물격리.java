import java.util.*;
import java.io.*;

class Main {
    static int n; //셀의 개수
    static int m; //격리 시간
    static int k; //미생물 군집의 개수
    static Node[][] arr;

    static class Node {
        int di; //방향 (상: 1, 하: 2, 좌: 3, 우: 4)
        int size; //개수

        public Node(int di, int size) {
            this.di = di;
            this.size = size;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            arr = new Node[n][n];
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int size = Integer.parseInt(st.nextToken());
                int di = Integer.parseInt(st.nextToken());

                arr[x][y] = new Node(di, size);
            }

            for (int j = 0; j < m; j++) move();

            long result = 0;
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < n; l++) {
                    if (arr[j][l] != null) {
                        result += arr[j][l].size;
                    }
                }
            }

            sb.append("#" + (i + 1) + " " + result + "\n");
        }
        System.out.println(sb);
    }

    static int[] rx = {0, -1, 1, 0, 0};
    static int[] ry = {0, 0, 0, -1, 1};

    //미생물 이동
    static void move() {
        Node[][] moveArr = new Node[n][n];
        int[][] sizeArr = new int[n][n]; //영역별 미생물 개수

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] != null) {
                    Node curNode = arr[i][j];
                    int nx = i + rx[curNode.di];
                    int ny = j + ry[curNode.di];

                    //약품 영역
                    if (nx <= 0 || ny <= 0 || nx >= n - 1 || ny >= n - 1) {
                        curNode.size /= 2;
                        if (curNode.size == 0) continue;
                        if (curNode.di % 2 == 0) curNode.di -= 1;
                        else curNode.di += 1;
                    }

                    //미생물 합치기
                    if (moveArr[nx][ny] != null) {
                        //현재 미생물의 군집이 더 큰 경우
                        if (moveArr[nx][ny].size < curNode.size) {
                            moveArr[nx][ny].di = curNode.di;
                            moveArr[nx][ny].size = curNode.size;
                        }
                    } else moveArr[nx][ny] = curNode;

                    sizeArr[nx][ny] += curNode.size;
                }
            }
        }

        //영역별 미생물 개수 갱신
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(moveArr[i][j] != null) {
                    moveArr[i][j].size = sizeArr[i][j];
                }
            }
        }

        arr = moveArr;
    }
}
