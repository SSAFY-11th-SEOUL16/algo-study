import java.io.*;
import java.util.*;

public class SW_2477_차량정비소 {
	static int n, m, k, a, b, result;
	static int receiptProcess[], repairProcess[]; //각 접수 창구, 정비 창구 마다 걸리는 시간
	static PriorityQueue<Person> waitingPerson; //접수 창구 기다리는 사람
	static Person receiptCounter[]; //접수 창구
	static Person repairCounter[]; //정비 창구

	static class Person implements Comparable<Person> {
		//고객 번호, 접수 창구 번호, 정비 창구 번호, 방문 시간, 끝나는 시간
		int num, receiptNum, repairNum, time, endTime;

		public Person(int num, int receiptNum, int time) {
			this.num = num;
			this.receiptNum = receiptNum;
			this.time = time;
		}

		@Override
		public int compareTo(Person o) {
			if (time == o.time) return num - o.num;
			return time - o.time;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine());
		for (int t = 0; t < tc; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); //접수 창구 개수
			m = Integer.parseInt(st.nextToken()); //정비 창구 개수
			k = Integer.parseInt(st.nextToken()); //방문 고객 수
			a = Integer.parseInt(st.nextToken()); //지갑 두고 간 접수 창구번호
			b = Integer.parseInt(st.nextToken()); //지갑 두고 간 정비 창구번호

			receiptProcess = new int[n + 1];
			repairProcess = new int[m + 1];
			receiptCounter = new Person[n + 1];
			repairCounter = new Person[m + 1];

			//접수 창구 걸리는 시간 저장
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				receiptProcess[i] = Integer.parseInt(st.nextToken());
			}

			//정비 창구 걸리는 시간 저장
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= m; i++) {
				repairProcess[i] = Integer.parseInt(st.nextToken());
			}

			//방문 시간 저장
			waitingPerson = new PriorityQueue<>();
			st = new StringTokenizer(br.readLine());
			int num = 1;
			for (int i = 0; i < k; i++) {
				waitingPerson.offer(new Person(num++, -1, Integer.parseInt(st.nextToken())));
			}

			result = 0;
			run();

			if (result == 0) result = -1;
			sb.append("#" + (t + 1) + " " + result + "\n");
		}

		System.out.println(sb.toString());
	}

	static void run() {
		//접수창구 시작
		int cnt = 0; //차량 정비 끝난 사람 수
		int time = 0;
		List<Person> repairWaitingPersonList = new ArrayList<>(); //정비 창구 기다리는 사람

		while (true) {
			//정비 창구 나올 사람 뽑기(모든 업무 완료한 사람)
			for (int i = 1; i <= m; i++) {
				if (repairCounter[i] != null && repairCounter[i].endTime - time <= 0) {
					if (repairCounter[i].receiptNum == a && repairCounter[i].repairNum == b) {
						result += repairCounter[i].num;
					}

					
					repairCounter[i] = null; //정비 창구 비우기
					cnt++;
				}
			}

			//정비 창구 들어갈 수 있는 사람 뽑기(접수 업무 끝난 사람)
			for (int i = 1; i <= n; i++) {
				if (receiptCounter[i] != null && receiptCounter[i].endTime - time <= 0) {
					repairWaitingPersonList.add(receiptCounter[i]);
					receiptCounter[i] = null; //접수창구 비우기
				}
			}

			//정비 창고 우선순위 기준에 맞춰 정렬
			Collections.sort(repairWaitingPersonList, new Comparator<Person>() {
				@Override
				public int compare(Person o1, Person o2) {
					if (o1.endTime == o2.endTime) {
						return o1.receiptNum - o2.receiptNum;
					}
					return o1.endTime - o2.endTime;
				}
			});

			//정비 창구 들어갈 사람 뽑기
			int size = repairWaitingPersonList.size();
			for (int i = 1; i <= m; i++) {
				//i번째 정비 창구가 비어있고
				if (repairCounter[i] == null) {
					//기다리는 사람이 있을 때
					if (repairWaitingPersonList.size() > 0) {
						Person p = repairWaitingPersonList.get(0);
						repairWaitingPersonList.remove(0);
						p.repairNum = i;
						p.endTime = time + repairProcess[i];

						repairCounter[i] = p;
						continue;
					}
					break;
				}
			}

			if (cnt == k)
				break;

			//접수 창구 들어갈 사람 뽑기
			for (int i = 1; i <= n; i++) {
				//i번째 접수 창구가 비어있고
				if (receiptCounter[i] == null) {
					//기다리는 사람이 있을 때
					if (!waitingPerson.isEmpty() && waitingPerson.peek().time <= time) {
						Person p = waitingPerson.poll();
						p.receiptNum = i;
						p.endTime = time + receiptProcess[i];

						receiptCounter[i] = p;
						continue;
					}
					break;
				}
			}

			time++;
		}
	}
}
