import java.util.*;
import java.io.*;

public class Main {

    /*
    입국심사대 n개 m명의 사람들
    각 입국심사대마다 심사하는 시간이 다름
    바로 심사를 안받아도 됨
     */
    static int n, m;
    static int[] time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        time = new int[n];
        long max = 0;
        long min = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            time[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, time[i]);
            min = Math.min(min, time[i]);
        }

        long left = min;
        long right = max * m;
 
        loop : while (left <= right) {
            long mid = (left + right) / 2;

            long count = 0;
            for (int i = 0; i < n; i++) {
                count += mid / time[i];

                if(count >= m) {
                    right = mid -1;
                    continue loop;
                }
            }
            left = mid + 1;
        }
        System.out.println(left);
    }

}
