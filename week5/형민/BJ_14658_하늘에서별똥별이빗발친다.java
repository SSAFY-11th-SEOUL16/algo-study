import java.io.*;
import java.util.*;

public class BJ_14658_하늘에서별똥별이빗발친다 {

	static int k;
	static int arr[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
	
		arr = new int[k][2];
		
		for(int i=0;i<k;i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int result = 0;
		for(int i=0;i<k;i++) {
			for(int j=0;j<k;j++) {
				result = Math.max(result, func(arr[i][0], arr[i][0]+l, arr[j][1], arr[j][1]+l));
			}
		}
		
		System.out.println(k - result);
	}

	static int func(int startX, int endX, int startY, int endY) {
		int cnt=0;
		
		for(int i=0;i<k;i++) {
			if(startX <= arr[i][0] && arr[i][0] <= endX && startY <= arr[i][1] && arr[i][1] <= endY)
				cnt++;
		}
		
		return cnt;
	}
}
