import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long m = Long.parseLong(st.nextToken());

        long arr[] = new long[n]; //심사대
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        long result = 1000000000 * m;
        long left = 0;
        long right = 1000000000 * m;
        while (left <= right) {
            long mid = (left + right) / 2;

            //mid 시간내에 가능한지 확인
            long cnt = 0;
            for (long t : arr) {
                cnt += (mid / t);
                if(cnt >= m) break;
            }

            if (cnt < m) left = mid + 1;
            else {
                result = Long.min(result, mid);
                right = mid - 1;
            }
        }

        System.out.println(result);
    }
}
