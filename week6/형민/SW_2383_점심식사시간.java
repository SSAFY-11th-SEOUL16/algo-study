import java.io.*;
import java.util.*;

public class SW_2383_점심식사시간 {

	static int n, personSize, result;
	static int arr[][];
	static int floor1XY[], floor2XY[];  //x, y, 계단 길이
	static ArrayList<Person> personList; //사람 좌표
	static ArrayList<Person> floor1, floor2; //계단 1 or 계단 2로 가는 사람 집단
	static boolean floorVisited[]; //true: 1번, false: 2번
	static PriorityQueue<Integer> waiting1Pq; //계단1 기다리는 사람(계단1 입구까지 이동시간 저장)
	static PriorityQueue<Integer> waiting2Pq; //계단2 기다리는 사람
	static Deque<Integer> work1Queue; //계단 1 내려가는 사람의 이동 완료 시간
	static Deque<Integer> work2Queue; //계단 2 내려가는 사람(의 완료 시간?)
	
	static class Person {
		int x, y;

		public Person(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine());

		for (int t = 0; t < tc; t++) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n][n];
			personList = new ArrayList<>();
			floor1 = new ArrayList<>();
			floor2 = new ArrayList<>();
			floor1XY = new int[3];
			floor2XY = new int[3];
			result = Integer.MAX_VALUE;

			floor1XY[0] = -1;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					
					//사람 좌표 저장
					if (arr[i][j] == 1)
						personList.add(new Person(i, j));
					else if (arr[i][j] != 0) { //계단 정보 저장
						if (floor1XY[0] == -1) {
							floor1XY[0] = i;
							floor1XY[1] = j;
							floor1XY[2] = arr[i][j];
						} else {
							floor2XY[0] = i;
							floor2XY[1] = j;
							floor2XY[2] = arr[i][j];
						}
					}
				}
			}
			
			personSize = personList.size();
			floorVisited = new boolean[personSize];

			subset(0);

			sb.append("#" + (t + 1) + " " + result + "\n");
		}

		System.out.println(sb.toString());
	}

	static void subset(int x) {
		//모든 사람이 이동할 계단을 선택한 경우
		if (x == personSize) {
			//시뮬레이션
			waiting1Pq = new PriorityQueue<>();
			waiting2Pq = new PriorityQueue<>();
			work1Queue = new ArrayDeque<Integer>();
			work2Queue = new ArrayDeque<Integer>();

			for (int i = 0; i < personSize; i++) {
				if (floorVisited[i]) { //계단1
					int dis = Math.abs(personList.get(i).x - floor1XY[0]) + Math.abs(personList.get(i).y - floor1XY[1]); //이동시간
					waiting1Pq.add(dis);
				} else { //계단2
					int dis = Math.abs(personList.get(i).x - floor2XY[0]) + Math.abs(personList.get(i).y - floor2XY[1]); //이동시간
					waiting2Pq.add(dis);
				}
			}

			int curPossibleTime = 0;
			int time = 1;
			int startCnt = waiting1Pq.size(); //계단으로 들어간 사람 수
			int finishCnt = 0; //이동 완료한 사람의 수
			
			//계단1 run
			while (true) {
				//이동 완료한 사람 뽑기
				while (!work1Queue.isEmpty() && work1Queue.peekFirst() - time == 0) {
					work1Queue.pollFirst();
					finishCnt++;
				}

				if (finishCnt == startCnt) {
					break;
				}
				
				//시간 맞는 사람 있고 계단1에 사람 3명 이하인 경우
				while (!waiting1Pq.isEmpty() && time >= waiting1Pq.peek() && work1Queue.size() < 3) {
					int moveCompleteTime = waiting1Pq.poll();

					//먼저 와서 기다리던 경우
					if (time > moveCompleteTime)
						work1Queue.addLast(time + floor1XY[2]);
					else
						work1Queue.addLast(time + floor1XY[2] + 1);
				}

				time++;
			}
			curPossibleTime = time;

			time = 1;
			startCnt = waiting2Pq.size();
			finishCnt = 0;
			
			//계단2 run
			while (true) {
				//이동 완료한 사람 뽑기
				while (!work2Queue.isEmpty() && work2Queue.peekFirst() - time == 0) {
					work2Queue.pollFirst();
					finishCnt++;
				}

				//이동 완료한 사람의 수 == 계단으로 들어간 사람 수
				if (finishCnt == startCnt) {
					break;
				}
				//시간 맞는 사람 있고 계단1에 사람 3명 이하인 경우
				while (!waiting2Pq.isEmpty() && time >= waiting2Pq.peek() && work2Queue.size() < 3) {
					int moveCompleteTime = waiting2Pq.poll();
					
					//먼저 와서 기다리던 경우
					if (time > moveCompleteTime)
						work2Queue.addLast(time + floor2XY[2]);
					else
						work2Queue.addLast(time + floor2XY[2] + 1);
				}

				time++;
			}
			//두 계단의 이동 완료 시간 중 최대값
			curPossibleTime = Math.max(curPossibleTime, time);
			
			result = Math.min(result, curPossibleTime);
			return;
		}

		floorVisited[x] = true;
		subset(x + 1);
		floorVisited[x] = false;
		subset(x + 1);
	}

}
