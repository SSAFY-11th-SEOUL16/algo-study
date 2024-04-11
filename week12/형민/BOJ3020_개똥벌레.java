import java.io.*;
import java.util.*;

public class BOJ3020_개똥벌레 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        //down[i]: 1~i 높이에 포함되어 있는 장애물 개수
        //up[i]: i~h 높이에 포함되어 있는 장애물 개수
        int down[] = new int[h + 2];
        int up[] = new int[h + 2];

        for (int i = 0; i < (n/2); i++) {
            down[Integer.parseInt(br.readLine())]++;
            up[h - Integer.parseInt(br.readLine()) + 1]++;
        }

        for (int i = 1; i <= h; i++) {
            down[i] += down[i - 1];
        }

        for (int i = h; i >= 1; i--) {
            up[i] += up[i + 1];
        }

        int min = n;
        int cnt = 0;
        for (int i = 1; i <= h; i++) {
            int v = (n / 2 - down[i - 1]) + (n / 2 - up[i + 1]);

            if(v < min) {
                min = v;
                cnt = 0;
            }
            if(v == min) cnt++;
        }

        System.out.println(min + " " + cnt);
    }

}