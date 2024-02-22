import java.io.*;
import java.util.*;

class SW_1767_프로세서연결하기 {
    static int n;
    static int arr[][];
    static ArrayList<int []> al = new ArrayList<>();
    static int maxLineCnt = 1, maxCoreCnt = 1;
    static int rx[] = {-1, 1, 0, 0};
    static int ry[] = {0, 0, -1, 1};
    static boolean visited[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n][n];
            visited = new boolean[n][n];
            maxCoreCnt = 1;
            maxLineCnt = Integer.MAX_VALUE;
            al.clear();

            for (int j = 0; j <n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < n; k++) {
                    arr[j][k] = Integer.parseInt(st.nextToken());
                    if(arr[j][k] == 1) al.add(new int[]{j, k});
                }
            }

            backtrack(-1, 0, 0);
            sb.append("#" + (i + 1) + " " + maxLineCnt + "\n");
        }

        System.out.println(sb.toString());
    }
    static void backtrack(int x, int lineCnt, int coreCnt) {
        //코어를 연결해도 의미가 없는 경우
        if(al.size() - (x + 1)  + coreCnt < maxCoreCnt) {
            return;
        }

        //마지막 코어까지 확인한 경우
        if(x == al.size() - 1) {
            if(maxCoreCnt < coreCnt) {
                maxCoreCnt = coreCnt;
                maxLineCnt = lineCnt;
            } else if(maxCoreCnt == coreCnt) {
                maxLineCnt = Integer.min(maxLineCnt, lineCnt);
            }

            return;
        }

        for (int i = x + 1; i < al.size(); i++) {
            int[] cur = al.get(i);

            //연결 불가능한 경우
            if(visited[cur[0]][cur[1]]) {
                backtrack(i, lineCnt, coreCnt);
                continue;
            }

            visited[cur[0]][cur[1]] = true;
            //가장자리에 위치한 경우
            if(cur[0] == 0 || cur[0] == n-1 || cur[1] == 0 || cur[1] == n-1) {
                backtrack(i, lineCnt, coreCnt + 1);
            } else {
                for (int j = 0; j < 5; j++) {
                    boolean flag = false;

                    //전선을 일부로 안 까는 경우
                    if(j==4) {
                        visited[cur[0]][cur[1]] = false;
                        backtrack(i, lineCnt, coreCnt);
                        visited[cur[0]][cur[1]] = true;

                        continue;
                    }

                    //전선 깔 수 있는지 확인
                    int nx = cur[0], ny = cur[1];
                    while(true) {
                        nx += rx[j];
                        ny += ry[j];

                        if(nx < 0 || ny < 0 || nx >= n || ny >= n) break;
                        if(visited[nx][ny]) {
                            flag = true;
                            break;
                        }
                    }

                    //전선 깔 수 있는 경우
                    if(!flag) {
                        nx = cur[0]; ny = cur[1];
                        int num = 0;
                        while(true) {
                            nx += rx[j];
                            ny += ry[j];

                            if(nx < 0 || ny < 0 || nx >= n || ny >= n) break;
                            num++;

                            visited[nx][ny] = true;
                        }

                        backtrack(i, lineCnt + num, coreCnt + 1);

                        nx = cur[0]; ny = cur[1];
                        while(true) {
                            nx += rx[j];
                            ny += ry[j];

                            if(nx < 0 || ny < 0 || nx >= n || ny >= n) break;
                            visited[nx][ny] = false;
                        }
                    }
                }
            }

            visited[cur[0]][cur[1]] = false;
        }
    }
}
