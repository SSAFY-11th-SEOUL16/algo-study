import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder()); // 큰 수가 나오게
		PriorityQueue<Integer> right = new PriorityQueue<>(); // 작은 수가 나오게

		for (int i = 0; i < n; i++) {
			int tmp = Integer.parseInt(br.readLine());
			if (left.size() == right.size())
				left.offer(tmp);
			else
				right.offer(tmp);

			if (!left.isEmpty() && !right.isEmpty() &&  left.peek() > right.peek()) {
				int val = right.poll();
				right.offer(left.poll());
				left.offer(val);
			}

			sb.append(left.peek()).append('\n');
		}
		System.out.println(sb);
	}
}
