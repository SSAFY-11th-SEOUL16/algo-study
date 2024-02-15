import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(str.nextToken());
		int m = Integer.parseInt(str.nextToken());
		int [] data = new int[m];
		int left = 1, right = 0, result=0;
		for(int i = 0; i <m; i++) {
			data[i] = Integer.parseInt(br.readLine());
			right = Math.max(right, data[i]);
		}
		//System.out.println("right : " + right);
		while(left <= right) {
			//System.out.println("left : " + left + " right : "+ right);
			int sum = 0;
			int mid = (left+right)/2;
			for(int i = 0; i <m; i++) {
				sum +=(data[i]/mid);
				if(data[i] % mid != 0) {
					sum++;
				}
			}
			//System.out.println("mid : "+mid+" sum : " + sum);
			if(sum > n) {
				left = mid +1;
			}
			else {
				right = mid-1;
				result = mid;
			}
		}
		System.out.println(result);
	}
}
