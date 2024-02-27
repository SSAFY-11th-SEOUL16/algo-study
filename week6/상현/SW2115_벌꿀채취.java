import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 4시간 삽질하다가 결국 수한이 코드 참고...
 * 처음 시도는 입력받은 벌꿀들 조합을 모두 만들고 그 안에서 다시 조합 계산
 * ex) {6,1} {1,9} {9,7}, {9,8}, {8,5} 등을 모두 만들어 놓고 {6,1} 부터 {9,7}이랑 다시 조합하는 방식으로 시도 -> 47개에서 막힘
 *
 *
 */
public class SW2115_벌꿀채취 {
    private static int result;
    private static int[][] maxHoney;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            result = 0;
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            int[][] arr = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            maxHoney = new int[N][N - M + 1];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N - M + 1; j++) {
                    honeyCombi(0,0,M,C,0, i,j, arr);
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N - M + 1; j++) {
                    int v = 0;
                    for (int k = j + M; k < N - M + 1; k++) {
                        v = Math.max(v, maxHoney[i][k]);
                    }

                    for (int k = i + 1; k < N; k++) {
                        for (int l = 0; l < N - M + 1; l++) {
                            v = Math.max(v, maxHoney[k][l]);
                        }
                    }

                    result = Math.max(result, maxHoney[i][j] + v);
                }
            }

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);

    }

    private static void honeyCombi(int L, int sum, int M, int C, int total, int i, int j, int[][] arr) {
        if (sum > C) {
            return;
        }

        if (L == M) {
            maxHoney[i][j] = Math.max(maxHoney[i][j], total);
            return;
        }

        honeyCombi(L + 1,  sum + arr[i][j + L], M,C, total + (arr[i][j + L] * arr[i][j + L]), i, j, arr);
        honeyCombi(L + 1,  sum , M,C, total , i, j, arr);
    }

}
