import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int arr[] = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int result = 0;
        for (int i = 0; i < n; i++) {

            int left = 0;
            int right = n - 1;
            while (left < right) {
                //자기 자신일 때 건너뛰기
                if(left == i) {
                    left++; continue;
                }
                if(right == i) {
                    right--; continue;
                }

                int sum = arr[left] + arr[right];

                if(sum < arr[i]) left++; //구하려는 수보다 작은 경우
                else if(arr[i] < sum) right--; //구하려는 수보다 큰 경우
                else {
                    result++;
                    break;
                }
            }
        }

        System.out.println(result);
    }
}