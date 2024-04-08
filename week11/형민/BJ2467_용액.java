import java.io.*;
import java.util.*;

public class BJ2467_용액 {
	static int n;
	static int arr[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0, right = n-1;
		int result = Integer.MAX_VALUE, resultX = -1, resultY = -1;
		while(left<right) {
			int value = arr[left] + arr[right];
			
			//특성값이 0에 가까운 경우
			if(result >= Math.abs(value)) {
				//갱신
				result = Math.abs(value);
				resultX = arr[left];
				resultY = arr[right];
			}
			
			if(value > 0) right--; //특성값이 0보다 큰 경우 특성값을 낮추기 위해 right--
			else left++; //특성값이 0보다 작거나 같은 경우 특성값을 높이기 위해 left--
		}
		
		System.out.println(resultX + " " + resultY);
	}

}

