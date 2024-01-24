import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ12018_YonseiTOTO {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            int[] mileages = new int[P];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < P; j++) {
                mileages[j] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(mileages);
            pq.add(P - L >= 0 ? mileages[P - L] : 1);

        }
        int sum = 0, cnt = 0;
        while (!pq.isEmpty()){
            sum += pq.poll();
            if (sum > K) {
                break;
            }
            cnt++;
        }

        System.out.println(cnt);
    }
}
