import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {
        final int MAX = 200001; // 더 큰 MAX 값으로 설정
        int N = 0, K = 0;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            K = Integer.parseInt(input[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(bfs(N, K, MAX));
    }

    private static int bfs(int start, int target, int max) {
        Deque<Integer> queue = new ArrayDeque<>();
        int[] time = new int[max];
        for (int i = 0; i < max; i++) {
            time[i] = -1;
        }

        queue.add(start);
        time[start] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == target) {
                return time[current];
            }

            for (int i = 0; i < 3; i++) {
                int next;
                if (i == 0) {
                    next = current - 1;
                } else if (i == 1) {
                    next = current + 1;
                } else {
                    next = current * 2;
                }

                if (next < 0 || next >= max) {
                    continue;
                }

                if (time[next] != -1 && time[next] <= time[current]) {
                    continue;
                }

                if (i < 2) {
                    queue.add(next);
                    time[next] = time[current] + 1;
                } else {
                    queue.addFirst(next);
                    time[next] = time[current];
                }
            }
        }

        return -1;  // 도달할 수 없는 경우
    }
}
