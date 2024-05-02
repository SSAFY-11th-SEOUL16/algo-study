import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class subject{
		int k, s;
		subject(int k, int s){
			this.k = k;
			this.s = s;
		}
	}
	static int n, t;
	static subject[] data;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
		n = Integer.parseInt(str.nextToken());
		t = Integer.parseInt(str.nextToken());
		
		data = new subject[n+1];
		data[0] =  new subject(0, 0);
		for(int i = 1; i <= n; i++) {
			str = new StringTokenizer(br.readLine());
			data[i] = new subject(Integer.parseInt(str.nextToken()), Integer.parseInt(str.nextToken()));
		}
		
		 int[][] result = new int[n + 1][t + 1];
	     for(int i = 1; i <= n; i++) {
	    	 for(int j = 0; j <= t; j++) {
	    		 if(data[i].k <= j){
	    			 result[i][j] = Math.max(result[i - 1][j], result[i - 1][j - data[i].k] + data[i].s);
	    		 } else {
	    			 result[i][j] = result[i - 1][j];
	    		 }
	    	 }
	     }
	     System.out.println(result[n][t]);
	}
}
