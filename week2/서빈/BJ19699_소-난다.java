import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ19699_소-난다 {
    static HashSet<Integer> resultSet = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] array = new int[n];
        boolean[] visited = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        combination(array, visited, 0, n, m);

        if (resultSet.isEmpty()) {
            System.out.println(-1);
        } else {
            ArrayList<Integer> result = new ArrayList<>(resultSet);
            Collections.sort(result);
            for (int num : result) {
                sb.append(num).append(' ');
            }
            System.out.println(sb.toString().trim());
        }
    }

    public static void combination(int[] arr, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            int sum = 0;
            for (int i = 0; i < arr.length; i++) {
                if (visited[i]) {
                    sum += arr[i];
                }
            }
            if (isPrime(sum)) {
                resultSet.add(sum);
            }
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    public static boolean isPrime(int number) {
        if (number < 2) return false;
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
