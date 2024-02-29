import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2112_보호필름
{
	static int D,W,K;
	static int[] film; // 필름 정보 비트마스킹
	static int fillA; // A는 0
	static int fillB; // B는 1
	static int answer; 
	
	public static void subset(int idx, int cnt) {
		if(cnt >= answer) return;
		
		if(idx == D) {
			// 연속 K개 되는지
			for (int i = 0; i < W; i++) {
				int bit = (1 << i);
				boolean isValid = false;
				// 첫 번째가 B면 1, A면 0
				int prev = (film[0] & bit) == bit ? 1 : 0;
				int contCnt = 1; // 연속되는 개수
				for (int j = 1; j < D; j++) {
					int cur = (film[j] & bit) == bit ? 1 : 0;
					if(prev == cur) {
						contCnt++;
					}else {
						contCnt = 1;
						prev = cur;
					}
					
					if(contCnt == K) {
						isValid = true;
						break;
					}
				}
				if(!isValid) return;
			}
			answer = cnt;
			return;
		}
		
		// 약품 투여 X;
		subset(idx+1, cnt);
		
		// 약품 A 투여;
		int temp = film[idx];
		film[idx] = fillA;
		subset(idx+1, cnt+1);
		film[idx] = temp;

		// 약품 B 투여;
		film[idx] = fillB;
		subset(idx+1, cnt+1);
		film[idx] = temp;
	}
	
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(br.readLine());
        
        /*
         * D : 두께		<= 13
         * W : 가로 크기	<= 20
         * 
         * 성능검사 통과 기준
         * - 단면의 모든 세로방향에 대해 동일한 특성의 셀들이 K개 이상 연속적
         * 
         * 약품
         * - 막 내 모든 셀들이 하나의 특성(A, B)으로 변경
         * 
         * 약품 투입횟수 최소값 구하기
         * 
         * 각 막 마다 약품을 투여 안하는 경우, 약품 A를 투여하는 경우, 약품 B를 투여하는 경우 총 3가지
         * 3 ^ 13 ~= 1_600_000 가능
         */
        
        StringTokenizer tokens;
        film = new int[13];
        
        StringBuilder sb = new StringBuilder();
        
        for (int t = 1; t <= T; t++) {
        	tokens = new StringTokenizer(br.readLine());
			D = Integer.parseInt(tokens.nextToken());
			W = Integer.parseInt(tokens.nextToken());
			K = Integer.parseInt(tokens.nextToken());
			
			answer = K;
			
			for (int i = 0; i < D; i++) {
				film[i] = 0;
	        	tokens = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {			
					film[i] |= (Integer.parseInt(tokens.nextToken()) << j); // A : 0 , B : 1로 비트마스킹
				}
			}
			
			if(K != 1) {
				fillB = (int)Math.pow(2, W)-1; // B로 다 채우기 위해선 2^M - 1을 할당해주면 됨 - Mbit까지 전부 1로 채우면 B로 다 채우는 것
				subset(0,0);
			}else {
				answer = 0;
			}
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
        System.out.println(sb);
    }
}