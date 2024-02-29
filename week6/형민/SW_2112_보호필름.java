import java.io.*;
import java.util.*;

public class SW_2112_보호필름 {
	static int d, w, k; //행, 열 , 합격기준
	static int arr[][]; //셀 정보
	static int copyArr[][]; //셀 정보 복제
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine());

		for (int t = 0; t < tc; t++) {
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			arr = new int[d][w];
			copyArr = new int[d][w];
			//약품을 최소로 해야하기에 결과값을 MAX_VALUE로 설정
			result = Integer.MAX_VALUE;

			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					copyArr[i][j] = arr[i][j];
				}
			}

			//약품 개수를 기준으로 탐색
			for (int i = 0; i <= k; i++) {
				subset(0, 0, i);
				
				//가장 먼저 통과한 것이 약품 사용 수가 최소이다.
				if (result != Integer.MAX_VALUE)
					break;
			}

			sb.append("#" + (t + 1) + " " + result + "\n");
		}
		System.out.println(sb.toString());
	}

	static void subset(int x, int cnt, int dep) {
		//약품 선택 완료
		if (cnt == dep) {
			int totalCnt = 0; //통과한 열 개수
			for (int i = 0; i < w; i++) { //열 기준
				boolean flag = false;

				for (int j = 0; j < d - k + 1; j++) { //행 기준
					int startCell = arr[j][i]; //시작 셀 정보
					int sequenceCnt = 0; //연속된 셀 개수
					
					for (int l = 0; l < k; l++) {
						//연속된 셀 정보라면
						if (startCell == arr[j + l][i])
							sequenceCnt++;
						else
							break;
					}
					
					//성능검사 통과(연속된 셀 개수 == 합격 기준)
					if (sequenceCnt == k) {
						flag = true;
						break;
					}
				}

				if (flag) totalCnt++;
				else break; //하나라도 통과하지 못 한다면 종료
			}
			
			//모든 열에서 성능검사 통과
			if (totalCnt == w) result = Integer.min(result, dep);

			return;
		}

		for (int i = x; i < d; i++) {
			
			//A로 변경
			for (int j = 0; j < w; j++) {
				arr[i][j] = 0;
			}
			subset(i + 1, cnt + 1, dep);
			//복원
			for (int j = 0; j < w; j++) {
				arr[i][j] = copyArr[i][j];
			}

			
			//B로 변경
			for (int j = 0; j < w; j++) {
				arr[i][j] = 1;
			}
			subset(i + 1, cnt + 1, dep);
			//복원
			for (int j = 0; j < w; j++) {
				arr[i][j] = copyArr[i][j];
			}
			
		}
	}
}
