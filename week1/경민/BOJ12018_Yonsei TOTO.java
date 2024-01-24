import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public int useMileage(PriorityQueue<Integer> queue, int limit) {
        int count = 0;
        while (count < limit - 1 && !queue.isEmpty()) {
            queue.poll();
            count++;
        }

        if (queue.isEmpty()) {
            return 1;
        } else {
            return queue.poll();
        }

    }

    public static void main(String[] args) throws IOException {
        Main M = new Main();
        int n, m, p, l;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 과목 수
        m = Integer.parseInt(st.nextToken()); // 총 마일리지

        PriorityQueue<Integer> mileage = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken()); // 신청한 사람 수
            l = Integer.parseInt(st.nextToken()); // 과목의 수강인원

            PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < p; j++) {
                queue.add(Integer.parseInt(st.nextToken()));
            }

            // 필요한 최소 마일리지 구한 후 mileage 배열에 추가
            if (p < l) {
                mileage.add(1);
            } else {
                mileage.add(M.useMileage(queue, l));
            }
        }

        int sum = 0, result = 0;
        while (!mileage.isEmpty()) {
            sum += mileage.poll();

            if (sum > m) {
                System.out.println(result);
                return;
            }
            result++;
        }
        System.out.println(n);
    }
}