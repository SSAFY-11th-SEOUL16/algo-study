import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 투포인터
 *
 * 낮은 가격순으로 선물 정렬
 * lp는 현재의 최소값
 * 현재의 최소값과 다음 선물의 값 차이가 D보다 클 경우 현재의 최소값과 위치를 증가시키며
 * 차이가 D 이하가 될 때까지 가치를 빼줌
 */
public class Main {

    private static class Gift {
        int p;
        int v;

        public Gift(int p, int v) {
            this.p = p;
            this.v = v;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        Gift[] giftes = new Gift[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            giftes[i] = new Gift(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        long sum = 0;
        Arrays.sort(giftes, (o1, o2) -> {
            if (o1.p == o2.p) {
                return o1.v - o2.v;
            }
            return o1.p - o2.p;
        });

        int lp = 0;
        long max = 0;
        for (int rp = 0; rp < n; rp++) {
            sum += giftes[rp].v;

            while (giftes[rp].p - giftes[lp].p >= d) {
                sum -= giftes[lp++].v;
            }

            max = Math.max(sum, max);
        }
        System.out.println(max);
    }
}
