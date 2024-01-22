import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        int result = 0;

        int[] useM = new int[n];

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            int P = Integer.parseInt(line[0]);
            int L = Integer.parseInt(line[1]);

            String[] tmpStr = br.readLine().split(" ");
            int[] tmp = new int[P];
            for (int j = 0; j < P; j++) {
                tmp[j] = Integer.parseInt(tmpStr[j]);
            }

            int person = P - L;
            if (person < 0) {
                useM[i] = 1;
                continue;
            }

            Arrays.sort(tmp);
            if (tmp[person] > 36) {
                continue;
            }
            useM[i] = tmp[person];
        }

        Arrays.sort(useM);

        for (int um : useM) {
            if (m - um < 0) {
                break;
            }
            m -= um;
            result++;
        }
        System.out.println(result);
    }
}
