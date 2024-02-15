import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int arr[] = new int[m];

        int left = 1, right = 0, result = 0;
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            right = Math.max(right, arr[i]);
        }

        while (left <= right) {
            int mid = (left + right) / 2; //질투심

            int sum = 0;
            for (int i = 0; i < m; i++) {
                sum += (arr[i] / mid);
                if (arr[i] % mid != 0) {
                    sum++;
                }
            }

            if (sum > n) left = mid + 1;
            else {
                right = mid - 1;
                result = mid;
            }
        }
        System.out.println(result);
    }
}

