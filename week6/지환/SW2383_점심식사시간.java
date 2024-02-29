import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


//Java(131ms)
public class SW2383_점심식사시간 {
	static int n;
	static int[][] board;
	static List<int[]> stairPoints; //계단 위치 배열
	static List<Integer> stairLens; //계단 길이 배열
	static List<int[]> peoplePoints; //사람 위치 배열
	static int res = Integer.MAX_VALUE; //결과값
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int test = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=test; t++) {
			n = Integer.parseInt(br.readLine());
			board = new int[n][n];
			stairPoints = new ArrayList<>();
			stairLens = new ArrayList<>();
			peoplePoints = new ArrayList<>();
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<n; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if(board[i][j]==1) peoplePoints.add(new int[] {i,j});
					else if(board[i][j]>1) {
						stairPoints.add(new int[] {i,j});
						stairLens.add(board[i][j]);
					}
				}
			}
			subset(0, new boolean[peoplePoints.size()], peoplePoints.size());
			System.out.println("#" + t + " " + res);
			res = Integer.MAX_VALUE;
		}
	}
	
	//사람들로 가능한 부분집합을 구하고, 계단하고 매칭
	public static void subset(int idx, boolean[] pSelected, int end) {
		if(idx==end) {
			List<Integer> stairA = new ArrayList<>();
			List<Integer> stairB = new ArrayList<>();
			for(int i=0; i<end; i++) {
				if(pSelected[i]) stairA.add(i);
				else stairB.add(i);
			}
			res = Math.min(res, Math.max(calculate(stairA, 0), calculate(stairB, 1)));
			return;
		}
		pSelected[idx] = true;
		subset(idx+1, pSelected, end);
		pSelected[idx] = false;
		subset(idx+1, pSelected, end);
	}
	
	//구하는것은 총 소요시간으로 마지막 사람이 계단을 다 내려간 시간을 구하는 것
	//사람이 3명 이하일 경우에는 한명이 계단을 내려가는데 걸리는 시간은 (입구까지 이동시간) + (계단을 내려가는시간) + 1이다.
	//사람이 3명 이상일 때 계단에 사람이 다 차있다면 소요시간은 3칸 앞에 사람이 계단을 나 내려간 시점부터 + 계단을 내려가는 시간을 더해주면 된다.
	//dp를 사용해서 해결할 수 있다.
	public static int calculate(List<Integer> people, int stairNum) {
		if(people.size() == 0) {
			return 0;
		}
		int[] stair = stairPoints.get(stairNum);
		int peopleCnt = people.size();
		int stairLen = stairLens.get(stairNum);
		int[] times = new int[peopleCnt];
		for(int i=0; i<peopleCnt; i++) {
			int dist = Math.abs(stair[0] - peoplePoints.get(people.get(i))[0]) + 
					Math.abs(stair[1] - peoplePoints.get(people.get(i))[1]);
			times[i] = dist;
		}
		Arrays.sort(times);
		if(peopleCnt<=3) {
			return times[times.length-1] + stairLen + 1;
		}
		for(int i=0; i<3; i++) {
			times[i] += stairLen + 1;
		}
		for(int i=3; i<people.size(); i++) {
			if(times[i] < times[i-3]) {
				times[i] = times[i-3] + stairLen;
			} else {
				times[i] += stairLen + 1;
			}
		}
		return times[times.length-1];
	}
}
