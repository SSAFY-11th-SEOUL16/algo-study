import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int arr[];
    static ArrayList<Integer> al = new ArrayList();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        backtrack(0, 0, 0);

        Collections.sort(al);
        for (int i : al) {
            System.out.print(i + " ");
        }

        if (al.size() == 0) System.out.print(-1);
    }

    static void backtrack(int x, int sum, int cnt) {
        if (cnt == m) {
            //소수 판별
            for (int i = 2; i <= sum / 2; i++) {
                if (sum % i == 0) return;
            }
            if (!(al.contains(sum))) al.add(sum);
        }

        for (int i = x; i < n; i++) {
            backtrack(i + 1, sum + arr[i], cnt + 1);
        }
    }
}
