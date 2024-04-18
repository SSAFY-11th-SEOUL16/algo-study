import java.util.*;
import java.io.*;

public class Main {

	static int n, result, arr[], mix[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		
		arr = new int[n];
		mix = new int[n];

		st = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			mix[i] = Integer.parseInt(st2.nextToken());
		}

		// 카드를 플레이어에게 줄 수 없는 상황은 최초의 arr 배열로 다시 돌아왔을 경우 !!

		int[] copy = arr.clone();
		int[] init = arr.clone();
		
		result = 0;
		loop : while (true) {
			// 1. 조건에 만족하는지 체크
			boolean flag = true;
			
			for (int i = 0; i < n; i++) {
				if(arr[i] != i % 3) {
					flag = false;
					break;
				}
			}
			
			if(flag) break;
			
			// 2. 자리바꾸기
			for (int i = 0; i < n; i++)
				copy[mix[i]] = arr[i];
			arr = copy.clone();
			result++;
			
			// 3. 초기값과 똑같은지 체크
			for (int i = 0; i < n; i++) 
				if(init[i] != arr[i]) continue loop;
			
			result = -1;
			break;
		}
		System.out.println(result);

	}
}
