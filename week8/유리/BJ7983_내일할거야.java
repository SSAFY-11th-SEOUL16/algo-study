import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		int n = Integer.parseInt(br.readLine());
		
		class work implements Comparable<work> {
			int use, deadLine;
			work(int use, int deadLine){
				this.use = use;
				this.deadLine = deadLine;
			}
			
			@Override
			public int compareTo(work o) {
				if(o.deadLine > this.deadLine) {
					return 1;
				}
				return -1;
			}
		}
		
		work [] data = new work[n];
		for(int i = 0; i <n; i++) {
			str = new StringTokenizer(br.readLine());
			data[i] = new work(Integer.parseInt(str.nextToken()),Integer.parseInt(str.nextToken()));		
		}
		
		Arrays.sort(data);
		
		for(int i = 1; i <n; i++) {
			int tmp = data[i-1].deadLine-data[i-1].use;
			if(data[i].deadLine > tmp) {
				data[i].deadLine = tmp;
			}
		}
		
		System.out.println(data[n-1].deadLine-data[n-1].use);
	}
}
