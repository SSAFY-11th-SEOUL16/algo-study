import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[K + 1];
        int[][] itemsArr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            itemsArr[i][0] = Integer.parseInt(st.nextToken());
            itemsArr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(itemsArr, (a, b) -> Integer.compare(b[0], a[0]));

        for (int[] item : itemsArr) {
            for (int i = K; i >= item[0]; i--) {
                arr[i] = Math.max(arr[i], arr[i - item[0]] + item[1]);
            }
        }

        System.out.println(arr[K]);
    }
}
