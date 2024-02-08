package baekjoon.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1326_폴짝폴짝 {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int result = bfs(arr, start, end, N);

        System.out.println(result);
    }

    //오른쪽에서 왼쪽으로 점프만 체크하면 오답
    //왼쪽에서 오른쪽으로 점프했을 때가 나올 수 있으므로 둘 다 체크
    private static int bfs(int[] arr, int start, int end, int N) {
        int[] distance = new int[N + 1];
        boolean[] visit = new boolean[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        Queue<int[]> Q = new LinkedList<>();
        //0번은 위치값, 1은 몇번만에 가는지
        Q.add(new int[]{start, 0});
        distance[start] = 0;

        while (!Q.isEmpty()) {
            int[] now = Q.poll();
            //0번 즉 위치값이 end와 같을 경우 return
            if (now[0] == end) {
                return now[1];
            }

            //start가 end보다 작을 경우
            for (int i = now[0]; i <= N; i += arr[now[0]]) {
                //+1해서 거리가 더 작을경우 Q에 add
                if (now[1] + 1 < distance[i]) {
                    distance[i] = now[1] + 1;
                    Q.add(new int[]{i, now[1] + 1});
                }
            }
            //start가 end보다 클 경우 즉 거꾸로 가는 경우
            //필요한 케이스 뒤로 갔다가 앞으로 가는 경우

            //7 2 2 3 1 2 2 3 3
            //4 8
            for (int i = now[0]; i > 0; i -= arr[now[0]]) {
                if (now[1] + 1 < distance[i]) {
                    distance[i] = now[1] + 1;
                    Q.add(new int[]{i, now[1] + 1});
                }
            }

        }

        return -1;
    }


}
