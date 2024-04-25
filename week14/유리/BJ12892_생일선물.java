import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class gift implements Comparable<gift>{
		int p, v;
		gift(int p, int v){
			this.p = p;
			this.v = v;
		}
		@Override
		public int compareTo(gift o) {
			return Integer.compare(this.p, o.p);
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
		int n  = Integer.parseInt(str.nextToken()),d  = Integer.parseInt(str.nextToken());
		gift[] data = new gift[n];
		long result = 0;
		
		for(int i = 0; i < n; i++) {
			str = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(str.nextToken());
			int v = Integer.parseInt(str.nextToken());
			data[i] = new gift(p, v);
		}
		
		Arrays.sort(data);
		
		long sum = 0;
		int p=0, q=0;
		while(true) {
			while(q < n && data[q].p - data[p].p < d) {
				sum+=data[q].v;
				q++;
			}
			result = Math.max(result, sum);
			if(q==n) break;
			
			sum-=data[p].v;
			p++;
		}
		System.out.println(result);
	}
}
