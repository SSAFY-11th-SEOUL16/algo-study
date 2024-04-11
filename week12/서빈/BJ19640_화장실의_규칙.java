import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * java
 * 576 ms
 */

public class BJ19640_화장실의_규칙 {
	static int N, M, K;
	static Queue<Person>[] queue;
	static PriorityQueue<Person> pq = new PriorityQueue<>();

	static class Person implements Comparable<Person> {
		int D, H, line;
		boolean isDeca;

		public Person(int d, int h, int line, boolean isDeca) {
			D = d;
			H = h;
			this.line = line;
			this.isDeca = isDeca;
		}

		@Override
		public int compareTo(Person o) {
			if (this.D == o.D) {
				if (this.H == o.H)
					return Integer.compare(this.line, o.line);
				return Integer.compare(o.H, this.H);
			}
			return Integer.compare(o.D, this.D);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		queue = new Queue[M];
		for (int i = 0; i < M; i++) {
			queue[i] = new ArrayDeque<>();
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			queue[i % M].add(new Person(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i % M,
					i == K ? true : false));
		}

		int turn = 0;
		for (int i = 0; i < M; i++) {
			if (!queue[i].isEmpty())
				pq.add(queue[i].poll());
		}

		while (!pq.isEmpty()) {
			Person current = pq.poll();
			if (current.isDeca) {
				break;
			}
			if (!queue[current.line].isEmpty()) {
				pq.add(queue[current.line].poll());
			}
			turn++;
		}
		System.out.println(turn);
	}
}