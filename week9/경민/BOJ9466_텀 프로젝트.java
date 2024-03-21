import java.io.*;
import java.util.*;

public class Main {
	static int index, n, m, result;
	static int[] arr;
	static boolean[] checked;
	static List<Integer> list = new ArrayList<Integer>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n + 1];
			checked = new boolean[n + 1];

			result = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) 
				arr[i] = Integer.parseInt(st.nextToken());

			
			for (int i = 1; i <= n; i++) {
				if (checked[i])
					continue;
				if (i == arr[i])
					continue;

				index = i;
				checked[i] = true;
				list.add(i);

				while (true) {
					int next = arr[index]; // index의 함께 하고 싶은 학생 => next

					// next가 혼자하고 싶다면 list에 있던 사람들은 전부 팀에 속하지 않게 됨
					if (next == arr[next]) {
						result += list.size();
						checked[next] = true;
						break;
					}

					// next가 list에 포함된다면 list에서 next부터 list.size까지 같은 팀에 속함
					// => next 이전 값들은 프로젝트에 속하지 않게 됨
					if (checked[next] && list.contains(next)) {
						result += list.indexOf(next);
						break;
					}

					// next가 list에 포함되지 않으면서 체크했다면 list의 사람들은 팀에 속하지 않게됨
					if (checked[next]) {
						result += list.size();
						break;
					}

					checked[next] = true;
					list.add(next);
					index = next;
				}
				list.clear();
			}
			sb.append(result).append('\n');
		}
		System.out.println(sb);
	}
}
