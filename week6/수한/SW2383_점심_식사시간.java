import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_2383_점심_식사시간
{ 
	static int N;

	static int[] lenStair; // 계단 길이
	static int[][] distsToStairs; // 선택된 사람과 계단과의 거리
	static Queue<Integer> onStair = new ArrayDeque<>(); // 계단 위에 있는 사람들이 계단을 다 내려왔을 때의 시간 저장
	
	static int numPeople; // 사람수
	static int[][] dists; // 사람과 각 계단과의 거리
	static int[] numPeopleOfStairs; // 각 계단에 도착한 사람의 수
	
	static int answer = Integer.MAX_VALUE;
	
	public static void subset(int cnt) {
		if(cnt == numPeople) {
			int result = 0;
			for (int i = 0; i < 2; i++) {
				// 해당 계단에 도착하는 사람 있는지 체크
				if(numPeopleOfStairs[i] == 0) continue;
				
				// 해당 계단에 도착하는 사람들의 거리 저장 후 정렬
				int size = numPeopleOfStairs[i];
				int[] stair = new int[size];
				for (int j = 0; j < size; j++) {
					stair[j] = distsToStairs[i][j];
				}

				Arrays.sort(stair);

				int numP = 0; // 계단 다 내려간 사람 수
				int flowTime = 0; // 흐른 시간 (분)
				int pIdx = 0; // 기다리는 사람 idx
				
				while(numP < size) {
					flowTime++;
					// 현재 계단에 있는 사람들 처리
					// 현재 계단에 있던 사람들이 도착한 시간 + 계단 내려가는 시간이 같거나 짧으면 다 내려간 것
					while(!onStair.isEmpty() && (onStair.peek() <= flowTime)) {
						onStair.poll();
						numP++;
					}
					
					// 기다리던 사람들 계단에 추가
					while(pIdx < size && stair[pIdx] < flowTime && onStair.size() < 3) {
						pIdx++;
						// 다 내려갔을 때의 시간 추가
						onStair.offer(flowTime + lenStair[i]);
					}					
				}
				
				result = Math.max(result, flowTime);
			}
			
			answer = Math.min(answer, result);
			return;
		}
		
		// 계단 1 선택
		distsToStairs[0][numPeopleOfStairs[0]] = dists[cnt][0];
		numPeopleOfStairs[0]++;
		subset(cnt+1);
		numPeopleOfStairs[0]--;
	
		// 계단 2 선택
		distsToStairs[1][numPeopleOfStairs[1]] = dists[cnt][1];
		numPeopleOfStairs[1]++;
		subset(cnt+1);
		numPeopleOfStairs[1]--;
	}
	
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(br.readLine());
        
        /*
         * N * N 크기의 정사각형 모양의 방에 사람들이 않아 있음
         * 
         * P : 사람
         * S : 계단 입구
         * 
         * 각 사람마다 2개의 계단 중 1개로 간다고 생각
         * 
         * 사람 수가 최대 10이므로
         * 2^10의 경우 -> 가능 !!
         * 
         */
        
        StringTokenizer tokens;
        
        distsToStairs = new int[2][201];
		int[][] stairXY = new int[2][2];
		int[][] peopleXY = new int[101][2];
		dists = new int[101][2];
		lenStair = new int[2];
		numPeopleOfStairs = new int[2];
		
		StringBuilder sb = new StringBuilder();
		
        for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());

	        int stairIdx = 0;	
	        numPeople = 0;
	        answer = Integer.MAX_VALUE;
	        
			for (int i = 0; i < N; i++) {
				tokens = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(tokens.nextToken());
					if(num == 1) {
						peopleXY[numPeople][0] = i;
						peopleXY[numPeople][1] = j;
						for (int k = 0; k < stairIdx; k++) {
							dists[numPeople][k] = Math.abs(stairXY[k][0] - i) + Math.abs(stairXY[k][1] - j);
						}
						numPeople++;
					}else if(num >= 2) {
						stairXY[stairIdx][0] = i;
						stairXY[stairIdx][1] = j;
						for (int k = 0; k < numPeople; k++) {
							dists[k][stairIdx] = Math.abs(peopleXY[k][0] - i) + Math.abs(peopleXY[k][1] - j);
						}
						lenStair[stairIdx++] = num;
					}
				}
			}
        	
			subset(0);
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
        
        System.out.println(sb);
    }
}