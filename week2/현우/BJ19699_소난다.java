import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ19699_소난다 {
	private static int n, m;
	private static int[] cow, selected;
	private static boolean[] isPrime, visited;
	private static ArrayList<Integer> list;
	
	private static void dfs(int start, int depth) {
		int val, i;
		
		if (depth == m) {
			val = 0;
			for (int weight : selected) {
				val += weight;
			}
			if (isPrime[val] && !visited[val]) {
				list.add(val);
				visited[val] = true;
			}
		} else {
			for (i = start; i < n; i++) {
				selected[depth] = cow[i];
				dfs(i + 1, depth + 1);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int size, i, j;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		cow = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			cow[i] = Integer.parseInt(st.nextToken());
		}
		size = m * 1000;
		isPrime = new boolean[size + 1];
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		isPrime[1] = false;
		for (i = 2; i <= size; i++) {
			if (isPrime[i]) {
				for (j = i + i; j <= size; j += i) {
					isPrime[j] = false;
				}
			}
		}
		list = new ArrayList<>();
		selected = new int[m];
		visited = new boolean[size];
		dfs(0, 0);
		if (list.isEmpty()) {
			System.out.print("-1");
			return;
		}
		Collections.sort(list);
		for (int ans : list) {
			sb.append(ans).append(' ');
		}
		System.out.print(sb);
	}
}
