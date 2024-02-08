import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class egg {
	int s;
	int w;

	public egg(int s, int w) {
		this.s = s;
		this.w = w;
	}
}

public class BJ16987_계란으로계란치기 {
	static egg[] eggs;
	static int N, max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		eggs = new egg[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			eggs[i] = new egg(s, w);
		}

		checkEgg(0, 0);
		System.out.println(max);
	}

	public static void checkEgg(int idx, int cnt) {
		if (idx == N) {
			max = Math.max(max, cnt);
			return;
		}

		if (eggs[idx].s <= 0 || cnt == N - 1) {
			checkEgg(idx + 1, cnt);
			return;
		}

		int tmp = cnt;

		for (int i = 0; i < N; i++) {
			if (i == idx)
				continue;
			if (eggs[idx].s <= 0)
				continue;
			breakEgg(idx, i);

			if (eggs[idx].s <= 0) {
				cnt++;
			}

			if (eggs[i].s <= 0) {
				cnt++;
			}
			checkEgg(idx + 1, cnt);
			recover(idx, i);
			cnt = tmp;
		}
	}

	public static void breakEgg(int handEgg, int targetEgg) {
		eggs[targetEgg].s -= eggs[handEgg].w;
		eggs[handEgg].s -= eggs[targetEgg].w;
	}

	public static void recover(int handEgg, int targetEgg) {
		eggs[targetEgg].s += eggs[handEgg].w;
		eggs[handEgg].s += eggs[targetEgg].w;
	}
}