import java.io.*;
import java.util.*;

public class BJ1655_가운데를말해요 {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder()); //최대 힙
        PriorityQueue<Integer> minPq = new PriorityQueue<>(); //최소 힙

        for (int i = 0; i < n; i++) {
            int v = Integer.parseInt(br.readLine());

            //최대 힙의 크기가 최소 힙의 크기보다 같거나 하나 더 크도록 유지
            if(maxPq.size() == minPq.size()) maxPq.add(v);
            else minPq.add(v);

            int maxPeek = maxPq.peek();
            int minPeek = minPq.peek() == null ? maxPeek + 1 : minPq.peek();

            //최대 힙의 최댓값은 최소 힙의 최솟값보다 작거나 같도록 유지
            if(maxPeek > minPeek) {
                maxPq.poll();
                maxPq.add(minPeek);
                minPq.poll();
                minPq.add(maxPeek);
            }

            sb.append(maxPq.peek() + "\n");
        }

        System.out.println(sb.toString());
    }
}


