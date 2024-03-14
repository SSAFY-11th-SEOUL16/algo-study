import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static class town implements Comparable<town>{
		long loc, count;
		town(long loc, long count){
			this.loc = loc;
			this.count = count;
		}
		@Override
		public int compareTo(town o) {
			if(o.loc > this.loc) {
				return -1;
			}
			return 1;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		
		int n = Integer.parseInt(br.readLine());
		long total = 0;
		town [] data = new town[n];

		for(int i = 0; i < n; i++) {
			str = new StringTokenizer(br.readLine());
			long tmp = Integer.parseInt(str.nextToken());
			long tmp2 = Integer.parseInt(str.nextToken());
			data[i] = new town(tmp, tmp2);
			total+=tmp2;
		}
		
		total = (total+1)/2;
		Arrays.sort(data);
		
		long tmpCount = 0;
	
		for(int i = 0; i < n; i++) {
			town tmpTown = data[i];
			tmpCount+=tmpTown.count;
			//System.out.println(total + " / " + tmpCount);
			if(tmpCount >= total) {		
				System.out.println(tmpTown.loc);
				break;
			}
		}
	}
}
