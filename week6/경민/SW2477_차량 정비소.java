import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static class Work {
		int client;
		int desk;
		int time;

		public Work(int client, int desk, int time) {
			this.client = client;
			this.desk = desk;
			this.time = time;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			int np = Integer.parseInt(st.nextToken());
			int mp = Integer.parseInt(st.nextToken());
			int result = 0;
			int[] a = new int[n + 1];
			int[] b = new int[m + 1];
			int[] arrive = new int[k + 1];
			Work[] reception = new Work[n + 1];
			Work[] repair = new Work[m + 1];
			boolean[] check = new boolean[k + 1];

			Queue<Integer> receptionQ = new LinkedList<Integer>();
			Queue<Integer> repairQ = new LinkedList<Integer>();
			int time = Integer.MAX_VALUE; // 첫 고객이 도착한 시간
			int lastTime = 0; // 마지막 고객이 도착한 시간

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++)
				a[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= m; i++)
				b[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= k; i++) {
				arrive[i] = Integer.parseInt(st.nextToken());
				time = Math.min(time, arrive[i]);
				lastTime = Math.max(lastTime, arrive[i]);
			}

			int startC = 1;
			int receptionCnt = 0;
			int repairCnt = 0;
			int finish = 0;

			while (finish < k) {
				// 1. 도착시간이 된 고객들은 접수 대기 큐에
				if (lastTime >= time)
					for (int i = 1; i <= k && arrive[i] <= time; i++) {
						if (arrive[i] == time)
							receptionQ.add(i);
					}

				// 2. 접수 창구 시간 --
				if (receptionCnt != 0)
					for (int i = 1; i <= n; i++) {
						if (reception[i] != null) {
							reception[i].time--;

							if (reception[i].time == 0) {
								repairQ.add(reception[i].client);
								receptionCnt--;
								reception[i] = null;
							}
						}
					}

				// 3. 수리 창구 시간 --
				if (repairCnt != 0)
					for (int i = 1; i <= m; i++) {
						if (repair[i] != null) {
							repair[i].time--;

							if (repair[i].time == 0) {
								repairCnt--;
								repair[i] = null;
								finish++;
							}
						}
					}

				// 4. 접수 대기 큐 고객들 접수 창구에 배치
				for (int i = 1; i <= n && !receptionQ.isEmpty() && receptionCnt != n; i++)
					if (reception[i] == null) {
						// 접수창구가 비어있다면 고객 배치
						int client = receptionQ.poll();
						reception[i] = new Work(client, i, a[i]);
						receptionCnt++;
						if (i == np)
							check[client] = true;
					}

				// 5. 수리 대기 큐 고객들 수리 창구에 배치
				for (int i = 1; i <= m && !repairQ.isEmpty() && repairCnt != m; i++) {
					if (repair[i] == null) {
						// 수리창구가 비어있다면 고객배치
						int client = repairQ.poll();
						repair[i] = new Work(client, i, b[i]);
						repairCnt++;
						if (i == mp && check[client])
							result += client;
					}
				}
				time++;
			}

			if (result == 0)
				result = -1;
			sb.append('#').append(t).append(' ').append(result).append('\n');
			result = 0;

		}
		System.out.println(sb);
	}
}