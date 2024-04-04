import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		
		int n = Integer.parseInt(br.readLine());
		int [] data = new int [n];
		str = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			data[i] = Integer.parseInt(str.nextToken());
		}
		
		int left = 0, right =  n-1, result = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++) {
			int tmpLeft = i+1; 
			int tmpRight = n-1;
			while(tmpLeft <= tmpRight) {
				int mid = (tmpLeft+tmpRight)/2;
				int tmpResult = Math.abs(data[i]+data[mid]);
				if(tmpResult < result) {
					result = tmpResult;
					left = i;
					right = mid;
				}
				
				if(data[mid] >= -data[i]) {
					tmpRight = mid-1;
				}else {
					tmpLeft = mid+1;
				}
			}
		}
		
		System.out.println(data[left]+" "+ data[right]);
		
	}
}
