import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ5397_키로거 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        Deque<Character> right = new ArrayDeque<>();
        Deque<Character> left = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            String L = br.readLine();
            for (char c : L.toCharArray()) {
                if (c == '-') {
                    if (!left.isEmpty())
                        left.pollLast();
                } else if (c == '<') {
                    if (!left.isEmpty()) {
                        char tmp = left.pollLast();
                        right.addLast(tmp);
                    }
                } else if (c == '>') {
                    if (!right.isEmpty()) {
                        char tmp = right.pollLast();
                        left.addLast(tmp);
                    }
                } else {
                    left.addLast(c);
                }
            }

            while (!left.isEmpty()) {
                sb.append(left.removeFirst());
            }
            while (!right.isEmpty()) {
                sb.append(right.removeLast());
            }
            sb.append("\n");
            right.clear();
            left.clear();
        }
        System.out.println(sb.toString());
    }
}