import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int [] data = new int[100001];
	static int result;
	static boolean [] check;
	static boolean [] check2;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int t = Integer.parseInt(br.readLine());
		for(int i = 1; i <= t; i++) {
			n = Integer.parseInt(br.readLine());
			result = 0;	
			check = new boolean[n+1];
			check2 = new boolean[n+1];
			str = new StringTokenizer(br.readLine());
			for(int x = 1; x <= n; x++) {
				data[x] = Integer.parseInt(str.nextToken());
			}
			//System.out.println();
			for(int x = 1; x <= n; x++) {
				if(!check2[x]) {
					cal(x);
				}
			}
			bw.append(n-result+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void cal(int idx) {
		if(check2[idx]) {
			return;
		}
		if(check[idx]) {
			check2[idx] = true;
			result ++;
		}
		check[idx] = true;
		cal(data[idx]);
		check2[idx] = true;
		check[idx] = false;
	}
}
