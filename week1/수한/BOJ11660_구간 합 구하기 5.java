import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        StringBuilder sb = new StringBuilder();

        /*
            cumul[i][j] : i,j에서 0,0까지의 합
        */
        int[][] cumul = new int[N+1][N+1];
        int[] cumulHor = new int[N+1];
        for (int i = 1; i <= N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                cumulHor[j] = Integer.parseInt(tokenizer.nextToken()) + cumulHor[j-1];
                cumul[i][j] = cumul[i-1][j] + cumulHor[j];
            }
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(tokenizer.nextToken())-1;
            int y1 = Integer.parseInt(tokenizer.nextToken())-1;
            int x2 = Integer.parseInt(tokenizer.nextToken());
            int y2 = Integer.parseInt(tokenizer.nextToken());

            sb.append(cumul[x2][y2]-(cumul[x2][y1]+cumul[x1][y2]-cumul[x1][y1])).append('\n');
        }

        System.out.println(sb);
    }
}