import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * DFS(풀이 참고)
 * 1300ms
 * 사이클이 생기는 번호 배열을 만들어준뒤 dfs를 돌다가 다음 학생이 만약 방문을 했다면 사이클 생성 유무를 확인
 * 사이클이 생기지 않은 학생이라면 다음 학생부터 다시 돌아오기까지 next를 갱신해주면서 cnt값을 증가시켜 몇명의 학생이 포함되는지 확인
 * N개에서 cnt 값을 빼줌으로써 몇명이 혼자인지 출력
 */
public class BOJ9466_텀프로젝트 {
    private static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            cnt = 0;
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());

            int[] selectInfo = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                selectInfo[j] = Integer.parseInt(st.nextToken());
            }

            boolean[] visit = new boolean[N + 1];
            boolean[] isTeam = new boolean[N + 1];

            for (int j = 1; j <= N; j++) {
                if (!visit[j]) {
                    func(j, visit, isTeam, selectInfo);
                }
            }

            sb.append(N - cnt).append("\n");
        }

        System.out.println(sb);

    }

    private static void func(int now, boolean[] visit, boolean[] isTeam, int[] selectInfo) {
        if (visit[now]) {
            return;
        }

        visit[now] = true;
        //선택한 학생 확인
        int next = selectInfo[now];

        //확인 하지 않은 학생이면 탐색 진행
        if (!visit[next]) {
            func(selectInfo[now], visit, isTeam, selectInfo);
        } else {
            //사이클을 생성하지 않았다면 돌아오기까지 몇명의 학생인지 구함
            if (!isTeam[next]) {
                cnt++;
                while (next != now) {
                    cnt++;
                    next = selectInfo[next];
                }
            }
        }

        isTeam[now] = true;
    }

}
