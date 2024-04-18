import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//Java8, 148ms
//내가 아닌 다른 두 수의 합을 구해야하므로 투포인터로 진행
//투포인터를 쓰기 위해 정렬 진행, 배열을 순회하며 다른 두 포인터의 합이 자신이면 체크
public class BJ1253_좋다 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		int cnt = 0;
		
		for(int i=0; i<n; i++) {
			int left = 0;
			int right = n-1;
			while(true) {
				if(left==i) left++;
				else if(right==i) right--;
				if(left >= right) break;
				if(arr[left] + arr[right] < arr[i]) left++;
				else if(arr[left] + arr[right] > arr[i]) right--;
				else {
					cnt++;
					break;
				}
			}
		}
		System.out.println(cnt);
	}
}
