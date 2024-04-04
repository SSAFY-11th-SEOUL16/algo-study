import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 우선순위 큐
 * 우선순위 큐 2개를 활용하여 해결
 * 한개는 오름차순 한개는 내림차순으로 정렬을 유지하며 두 큐의 사이즈가 같을 경우에는 maxQ에 넣음
 * 각 큐에 넣으면서 maxQ의 첫번째 값과 minQ의 첫번째 값을 비교 후 maxQ의 값이 크다면 서로 바꿔줌
 * -> maxQ는 작은값을 중에 가장 큰 값을 저장하기 위해서 사용하고 minQ는 큰 값들 중 가장 작은 값을 유지하기 위해서 사용
 * 10 5 8 5
 * maxQ 10      maxQ 5      maxQ 5 8
 * minQ         minQ 10     minQ 10
 * 10           5           5
 * 이런식으로 해결
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minQ = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int v = Integer.parseInt(st.nextToken());

            if (maxQ.size() == minQ.size()) {
                maxQ.add(v);

                if (!minQ.isEmpty() && minQ.peek() < maxQ.peek()) {
                    minQ.add(maxQ.poll());
                    maxQ.add(minQ.poll());
                }
            } else {
                minQ.add(v);

                if (minQ.peek() < maxQ.peek()) {
                    minQ.add(maxQ.poll());
                    maxQ.add(minQ.poll());
                }
            }

            sb.append(maxQ.peek()).append("\n");
        }
        System.out.println(sb);
    }

}