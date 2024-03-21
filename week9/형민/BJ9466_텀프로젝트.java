import java.io.*;
import java.util.*;

public class BJ9466_텀프로젝트 {
    static int n;
    static int arr[];
    static int visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        for (int t = 0; t < tc; t++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n+1];
            visited = new int[n+1]; //0: 아직 안감, 1: 현재 팀원, 2: 노답
            result = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                pointX = -1;
                if(visited[i] == 0) dfs(i, 0);
            }

            sb.append(n - result + "\n");
        }
        System.out.println(sb.toString());
    }

    static int pointCnt = 0; //사이클이 발생했을 때 팀원 수
    static int pointX = 0; //사이클이 발생한 팀원의 번호
    static int result = 0; //팀에 속한 학생 수
    static void dfs(int x, int cnt) {
        //이미 속한 팀원 => 사이클 발생, 1개의 팀 생성
        if(visited[x] == 1) {
            //자기 자신으로 이동한 경우
            if(x == arr[x]) result += 1;
            else {
                pointX = x;
                pointCnt = cnt;
            }
            visited[x] = 2;
            return;
        }

        //더 가면 답이 없는 경우
        if(visited[x] == 2) {
            return;
        }

        visited[x] = 1;
        dfs(arr[x], cnt + 1);

        if(x == pointX) {
            result += (pointCnt - cnt);
        }

        visited[x] = 2;
    }
}
