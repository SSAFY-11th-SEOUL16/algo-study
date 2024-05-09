import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
	static long n, m, result = Long.MAX_VALUE;
	static long [] data;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
		
		n = Long.parseLong(str.nextToken());
		m = Long.parseLong(str.nextToken());
		long max = 0;
		data = new long[(int)n];
		for(int i = 0; i < n; i++) {
			data[i] = Long.parseLong(br.readLine());
			max = Math.max(max, data[i]);
		}
		
		Arrays.sort(data);
		
		long left = 0;
		long right = max*m;
		while(left <= right) {
			long mid = (left+right)/2;
			
			long cnt = 0;
			for(long tmp : data) {
				if(cnt >= m) {
					break;
				}
				cnt +=mid/tmp;
			}
			
			if(cnt<m) {
				left= mid+1;
			}else {
				right=mid-1;
				result = Math.min(result, mid);
			}
		}
		
		System.out.println(result);
	}
}
