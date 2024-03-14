import java.io.*;
import java.util.*;

public class BJ2141_우체국 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int arr[][] = new int[n][2];

        long peopleSum = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); //마을 번호
            arr[i][1] = Integer.parseInt(st.nextToken()); //사람 수
            peopleSum += arr[i][1];
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        long half = (peopleSum + 1) / 2;
        long cur = 0;
        for (int i = 0; i < n; i++) {
            cur += arr[i][1];

            if(cur >= half) {
                System.out.println(arr[i][0]);
                break;
            }
        }
    }
}
