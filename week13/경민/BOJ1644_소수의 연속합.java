import java.util.*;
import java.io.*;

public class Main {

	static int n, result;
	static boolean[] check;
	static List<Integer> prime = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		if (n == 1) {
			System.out.println(0);
			return;
		}
		
		check = new boolean[n + 1];
		for (int i = 2; i <= n; i++) {
			if (check[i])
				continue;

			for (int j = i * 2; j <= n; j += i) {
				check[j] = true; // 소수가 아닌 수 true
			}
		}

		for (int i = 2; i <= n; i++) {
			if (!check[i])
				prime.add(i);
		}

		if (!check[n]) result++;
		
		int sum = 0, start = 0, end = 0, size = prime.size();

		while (start < size && end < size) {
			if (sum == n)
				result++;
			if (sum < n)
				sum += prime.get(end++);
			else if (sum >= n)
				sum -= prime.get(start++);
		}

		System.out.println(result);

	}
}
