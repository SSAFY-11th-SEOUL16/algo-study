import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * dp, 트리
 * 인접 배열로 할 경우 메모리 폭파
 * 완탐시 시간 초과
 * 인접 리스트로 각 상사별로 직속 부하 직원을 add
 * 입력을 받으면서 초기에 각 직원들이 받은 칭찬 수치를 sum 배열에 입력
 * 각 직원의 직속 부하들에게 자신이 받은 점수값을 더해줌
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer>[] adjList = new ArrayList[N + 1];

        st = new StringTokenizer(br.readLine());
        st.nextToken();

        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 2; i <= N; i++) {
            adjList[Integer.parseInt(st.nextToken())].add(i);
        }

        int[] sum = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            //각 직원들이 받은 칭찬 수치를 sum 배열에 입력
            sum[num] += w;

        }

        //각 직원의 직속 부하들에게 자신이 받은 점수값을 더해줌
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < adjList[i].size(); j++) {
                sum[adjList[i].get(j)] += sum[i];
            }
        }

        for (int i = 1; i <= N; i++) {
            sb.append(sum[i]).append(" ");
        }

        System.out.println(sb);
    }

}