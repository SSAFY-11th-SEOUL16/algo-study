import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		int lt = 0;
		int rt = n - 1;

		int minMix = Integer.MAX_VALUE;
		int a = 0, b = 0;
		while (lt < rt && rt < n) {
			int mix = arr[lt] + arr[rt];
			int mixAbs = Math.abs(mix);
			if (mixAbs <= minMix) {
				minMix = mixAbs;
				a = arr[lt];
				b = arr[rt];
				if(minMix == 0) break;
			}
			if (mix > 0)
				rt--;
			else
				lt++;
		}

		System.out.println(a + " " + b);
	}
}