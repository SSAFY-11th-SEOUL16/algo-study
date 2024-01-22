import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ12018_YonseiTOTO {
	private static int getMin(BufferedReader br, StringTokenizer st) throws IOException {
		int p, l, i;
		int[] list;
		
		st = new StringTokenizer(br.readLine());
		p = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		list = new int[p];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < p; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		if (p < l) {
			return 1;
		}
		Arrays.sort(list);
		return list[p - l];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, i;
		int[] min;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		min = new int[n];
		for (i = 0; i < n; i++) {
			min[i] = getMin(br, st);
		}
		Arrays.sort(min);
		for (i = 0; i < n && m >= min[i]; i++) {
			m -= min[i];
		}
		System.out.println(i);
	}
}