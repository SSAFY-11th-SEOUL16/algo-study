import java.util.*;
import java.io.*;

public class Main {

    static int n, result, arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;

            while (left < right) {
                if (left == i) left++;
                else if (right == i) right--;
                else {
                    int sum = arr[left] + arr[right];
                    if (sum == arr[i]) {
                        result++;
                        break;
                    }

                    if (sum < arr[i])
                        left++;
                    else
                        right--;
                }
            }

        }

        System.out.println(result);
    }
}
