import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class guitar {
	String name;
	boolean[] song;
	int canplay;

	public guitar(String name, boolean[] song, int canplay) {
		this.name = name;
		this.song = song;
		this.canplay = canplay;
	}
}

public class BJ1497_기타콘서트 {
	static guitar[] guitars;
	static int[] index;
	static int N, M, maxCanPlay, guitarNum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int result = 0;

		guitars = new guitar[N];
		index = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			boolean[] song = new boolean[M];
			int can = 0;

			String canplay = st.nextToken();
			for (int j = 0; j < M; j++) {
				song[j] = (canplay.charAt(j) == 'Y') ? true : false;
				if (song[j]) {
					can++;
					result++;
				}
			}
			guitars[i] = new guitar(name, song, can);
		}

		for (int i = 1; i <= N; i++) {
			com(0, 0, i);
		}

		if (result == 0)
			System.out.println(-1);
		else
			System.out.println(guitarNum);
	}

	public static void com(int cnt, int start, int max) {
		if (cnt == max) {
			if (check(guitars, index, max)) {
				return;
			}
			return;
		}

		for (int i = start; i < N; i++) {
			index[cnt] = i;
			com(cnt + 1, i + 1, max);
		}
	}

	public static boolean check(guitar[] guitars2, int[] index2, int max) {
		boolean result = true;
		int can = 0;

		for (int i = 0; i < M; i++) {
			boolean guitarCheck = guitars[index[0]].song[i];
			if (!guitarCheck) {
				for (int j = 1; j < max; j++) {
					guitarCheck = guitars[index[j]].song[i] || guitarCheck;
				}
			}

			if (!guitarCheck)
				result = false;
			if (guitarCheck)
				can++;
		}

		if (maxCanPlay < can) {
			maxCanPlay = can;
			guitarNum = max;
		}
		return result;
	}
}