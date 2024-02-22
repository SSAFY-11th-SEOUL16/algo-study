import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(str.nextToken());
		int m = Integer.parseInt(str.nextToken());
		int l = Integer.parseInt(str.nextToken());
		int k = Integer.parseInt(str.nextToken());
		int result = 0;
		int[][] data = new int[k][2];
		for(int i = 0; i <k; i++) {
			str = new StringTokenizer(br.readLine());
			data[i][0]=Integer.parseInt(str.nextToken());
			data[i][1]=Integer.parseInt(str.nextToken());
		}
		
		for(int [] data1 : data) {
			int tmpX = data1[0];
			for(int [] data2 : data) {
				int tmpY = data2[1];
				int tmpResult = 0;
				for(int [] data3 : data) {
					if(data3[0] >= tmpX && data3[0] <= tmpX+l && data3[1] >= tmpY && data3[1] <= tmpY+l) {
						 tmpResult++;
					}
				}
				if(tmpResult > result) {
					result = tmpResult;
				}
			}
		}
		
		System.out.println(k-result);
	}
}
