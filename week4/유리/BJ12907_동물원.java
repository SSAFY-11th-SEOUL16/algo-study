import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n, result = 0;
	static int [] data;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		
		 n = Integer.parseInt(br.readLine());
		 data = new int [n];
		 str = new StringTokenizer(br.readLine());
		 for(int i = 0; i < n; i++) {
			 data[i] = Integer.parseInt(str.nextToken());
		 }
		 
		 Arrays.sort(data);
		 
		 cal(0,0,0);
		 System.out.println(result);
	}
	
	private static void cal(int count, int rabbit, int cat) {
		boolean check = true;
		if(count == n) {
			result++;
			return;
		}
		
		if(rabbit == data[count]) {
			cal(count+1, rabbit+1, cat);
			check = false;
		}
		if(cat == data[count]) {
			cal(count+1, rabbit, cat+1);
			check = false;
		}
		if(check) {
			return;
		}
	}
}
