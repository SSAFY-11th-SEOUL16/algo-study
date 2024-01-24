import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] tmpData = br.readLine().split(" ");
		int n = Integer.parseInt(tmpData[0]);
		int m = Integer.parseInt(tmpData[1]);
		int [] tmp = new int[n];
		int result = 0;
		
		
		while(n > 0) {
			String [] tmpData2 = br.readLine().split(" ");
			int P = Integer.parseInt(tmpData2[0]);
			int L = Integer.parseInt(tmpData2[1]);
			tmpData2 = br.readLine().split(" ");
			int [] tmpData3 = new int[tmpData2.length];
			for(int i = 0; i<P; i++) {
				tmpData3[i] = Integer.parseInt(tmpData2[i]);
			}
			Arrays.sort(tmpData3);
			if(P>=L) {
				tmp[n-1]=tmpData3[P-L];
			}
			else {
				tmp[n-1]=1;
			}
			n--;
		}
		Arrays.sort(tmp);
		for(int i = 0; i <tmp.length; i++) {
			m -= tmp[i];
			if(m < 0) {
				break;
			}
			result ++;
		}
		System.out.println(result);
	}
}
