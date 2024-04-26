import java.util.*;
import java.io.*;

public class Main {

    /*
    누구도 미안하지 않게 선물을 받으면서, 만족도의 최대 합
    => 받을 선물의 금액 차가 d 이상이 되면 안됨
     */

    // 10 11 12 13
    static int n;
    static long result;

    static class Present implements Comparable<Present> {
        int price;
        int good;

        public Present(int price, int good) {
            this.price = price;
            this.good = good;
        }

        @Override
        public int compareTo(Present o) {
            if(this.price < o.price) return -1;
            return 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        long d = Integer.parseInt(st.nextToken());

        Present[] presents = new Present[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            presents[i] = new Present(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(presents);

        long sum = 0;
        int left, right;
        left = right = 0;

        while(left <= right && right < n) {
            // 금액차이가 d보다 작으면 right++
            // 금액차이가 d보다 크면 left++

            if(presents[right].price - presents[left].price < d) {
                sum += presents[right++].good;
            } else {
                sum -= presents[left++].good;
            }

            result = Math.max(sum, result);
        }
        System.out.println(result);

    }
}
