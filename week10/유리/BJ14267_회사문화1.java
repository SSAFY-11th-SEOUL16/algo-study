import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(str.nextToken());
		int m = Integer.parseInt(str.nextToken());
		int [] data = new int [n+1];
		int [] result = new int[n+1];
		
		str = new StringTokenizer(br.readLine());
		for(int i = 1; i <=n; i++) {
			data[i] = Integer.parseInt(str.nextToken());
		}
		
		for(int i = 0; i < m; i++) {
			str = new StringTokenizer(br.readLine());
			result[Integer.parseInt(str.nextToken())] += Integer.parseInt(str.nextToken());
		}
		
		for(int i  = 1; i <= n; i++) {
			if(data[i] == -1) {
				continue;
			}else {
				result[i] = result[data[i]]+result[i];
			}
		}
		
		for(int i = 1; i <= n; i++) {
			System.out.printf("%d ", result[i]);
		}
		
	}
}
