import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static Egg eggs[];

	static class Egg {
		int v; // 내구도
		int w; // 무게

		public Egg(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		eggs = new Egg[n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			eggs[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		backtrack(0, 0);
		
		if (result == Integer.MIN_VALUE)
			result = 0;
		System.out.println(result);
	}

	static int result = Integer.MIN_VALUE;

	static void backtrack(int x, int breakEggCnt) {
		result = Math.max(result, breakEggCnt);
		if (x == n)	return;
		
		Egg curEgg = eggs[x];
		if (curEgg.v <= 0) {
			backtrack(x + 1, breakEggCnt);
			return;
		}

		for (int i = 0; i < n; i++) {
			if(x == i) continue;
			
			Egg nextEgg = eggs[i];
			int breakEgg = 0;
			if (nextEgg.v > 0) { // 치려는 계란이 살아있는 경우
				nextEgg.v -= curEgg.w;
				curEgg.v -= nextEgg.w;

				if (curEgg.v <= 0) {
					breakEgg++;
				}
				if (nextEgg.v <= 0)
					breakEgg++;

				backtrack(x + 1, breakEggCnt + breakEgg);

				nextEgg.v += curEgg.w;
				curEgg.v += nextEgg.w;
			}
		}
	}
}

