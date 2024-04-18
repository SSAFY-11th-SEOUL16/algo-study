import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		
		//데이터 세팅
		int n = Integer.parseInt(br.readLine());
		str = new StringTokenizer(br.readLine());
		int [] data = new int[n];
		int result = 0;
		for(int i = 0; i< n; i++) {
			data[i] = Integer.parseInt(str.nextToken());
		}
		
		//데이터 정렬
		Arrays.sort(data);
		
		//투포인터 알고리즘
		for(int i = 0; i < n; i++) {
			int tartgetNum = data[i];
			int startIndex = 0, endIndex = n-1;
			
			while(startIndex < endIndex) {
				int sum = data[startIndex]+data[endIndex];
				
				if(sum == tartgetNum) {
					if(startIndex != i && endIndex != i) {
						result++;
						break;
					}else if(startIndex == i){
						startIndex++;
					}else {
						endIndex--;
					}
				}else if(sum < tartgetNum) {
					startIndex++;
				}else {
					endIndex--;
				}
			}
		}
		
		System.out.println(result);
	}
}
