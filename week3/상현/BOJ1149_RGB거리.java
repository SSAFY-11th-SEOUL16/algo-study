import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1149_RGB거리 {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][3];
        //1. 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //0 = 빨강 / 1 = 초록 / 2 = 파랑
        for (int i = 1; i < N; i++) {
            //2. 각 집(i)까지의 최소 비용은 = i번째 현재색 + (i -1 집의 현재색을 제외한 이전의 제외한 값 중 작은 값)
            //예시로 i번째에서 빨강(0)일 경우 i - 1에서 가능한 초록(1), 파랑(2) 중 작은 값을 더하면 최소 비용
            //3. map의 값 업데이트
            map[i][0] = map[i][0] + Math.min(map[i - 1][1], map[i - 1][2]);
            map[i][1] = map[i][1] + Math.min(map[i - 1][0], map[i - 1][2]);
            map[i][2] = map[i][2] + Math.min(map[i - 1][0], map[i - 1][1]);
        }

        //제일 마지막 값 들 중에서 가장 작은 값 출력
        System.out.println(Math.min(map[N - 1][0], Math.min(map[N - 1][1], map[N - 1][2])));
    }
}
