import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
	static class homework{
		int sirial ,time, num;
		int [] preWork;
		
		homework(int sirial, int time, int num, int[] preWork) {
			this.sirial = sirial;
			this.time = time;
			this.num = num;
			//this.preWork = new int[num];
			this.preWork = preWork;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		
		int n = Integer.parseInt(br.readLine());
		homework[] data = new homework[n];
		int [] dataResult = new int[n+1];
		for(int i = 0; i < n; i++) {
			str = new StringTokenizer(br.readLine());
			
			int time = Integer.parseInt(str.nextToken());
			int num = Integer.parseInt(str.nextToken());
			int [] preWork = new int[num];
			for(int x = 0; x < num; x++) {
				preWork[x] = Integer.parseInt(str.nextToken());
			}
			
			data[i] = new homework(i +1, time, num, preWork);
		}
		
		int result = 0;
		for(int i = 0; i< n; i++) {
			homework tmp = data[i];
			int tmpResult = 0;
			
			for(int x = 0; x < tmp.num; x++) {
				if(tmpResult < dataResult[tmp.preWork[x]]) {
					tmpResult = dataResult[tmp.preWork[x]];
				}
			}
			
			tmpResult += tmp.time;
			dataResult[tmp.sirial] = tmpResult;
			if(tmpResult > result) {
				result = tmpResult;
			}
			//System.out.println(tmpResult+"\t");
		}
		
		System.out.println(result);
	}
}
