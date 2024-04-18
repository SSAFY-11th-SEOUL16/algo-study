import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		
		//데이터 세팅
		int n = Integer.parseInt(br.readLine());
		int [] changeRule = new int[n];
		int [] resultData = new int[n];
		int [] basicData = new int[n];
		int [] basicData2 = new int[n];
		int [] tmpData;
		int result = 0;
		
		//System.out.println();
		for(int i = 0; i <n; i++) {
			basicData[i] = i%3;
			basicData2[i] = i%3;
			//System.out.print(basicData[i]+"\t");
		}
		//System.out.println();
		//System.out.println("=======================================");
		
		str = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			resultData[i] = Integer.parseInt(str.nextToken());
		}
		
		str = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			changeRule[i] = Integer.parseInt(str.nextToken());
		}
		
		
		while(true) {
			boolean check = true;    //결과값과 동일한지 확인하는 flag
			boolean check2 = false;  //맨 처음 초기값과 동일한지 확인하는 flag
      //원하는 결과값과 동일한지 확인
			for(int i = 0; i < n; i++) {
				if(resultData[i] != basicData[i]) {  //확인 중 동일하지 않은 값 발견시 확인 종료
					check = false;
					break;
				}
			}
      //원하는 결과값과 동일할 경우, 카드 섞은 횟수 출력 및 while문 종료
			if(check) {
				System.out.println(result);
				break;
			}
			result++;    //카드 섞은 횟수 추가
			tmpData = new int[n];
      //카드 섞기
			for(int i = 0; i <n; i++) {
				tmpData[i] = basicData[changeRule[i]];
				//System.out.print(tmpData[i]+"\t");
			}
			//System.out.println();
			//System.out.println("=======================================");
			basicData = tmpData;
      
      //맨 처음 초기값과 섞은 카드의 값이 동일한지 확인
			for(int i = 0; i < n; i++) {
				if(basicData[i] != basicData2[i]) {  //동일하지 않은 값 발견 시 확인 종료
					check2 = true;
					break;
				}
			}
      //맨 처음 초기값과 동일 시, 원하는 결과값을 만들 수 없다는 것이므로 -1 출력 후 while문 종료
			if(!check2) {
				System.out.println(-1);
				break;
			}
		}
	}
}
