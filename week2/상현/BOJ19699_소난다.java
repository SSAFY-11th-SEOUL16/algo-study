import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BOJ19699_소난다 {
	
	//5C3 이므로 3개의 조합을 담을 arr
	private static int[] arr;
	//체크 배열
	private static boolean[] ch;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] cows = new int[N];
		ch = new boolean[N];
		arr = new int[M];
		
		st = new StringTokenizer(br.readLine());
		int maxSum = 0;
		for (int i = 0; i < cows.length; i++) {
			cows[i] = Integer.parseInt(st.nextToken());
			maxSum += cows[i];
		}
		boolean[] sumCheck = new boolean[maxSum + 1];
		
		rec(0, 0,M, cows, sumCheck);
		
		//출력
		for (int i = 0; i < sumCheck.length; i++) {
			if (sumCheck[i]) {
				sb.append(i).append(" ");
			}
		}
		System.out.println(sb.length() > 0 ? sb : -1);
	}
	
	//count = 현재 arr에 들어간 요소의 개수
	//idx = idx 이후의 값 부터 cows 순회 
	//M = 조합의 MAX
	private static void rec(int count, int idx, int M, int[] cows, boolean[] sumCheck) {
		if (count == M) {
			int sum = 0;
			for (int i : arr) {
				sum += i;
			}
			
			if (isPrim(sum) && !sumCheck[sum]) {
				sumCheck[sum] = true;
			}
			
			return;
		}
		
		for (int i = idx; i < cows.length; i++) {
			if (!ch[i]) {
				arr[count] = cows[i];
				ch[i] = true;
				rec(count + 1, i + 1, M, cows, sumCheck);
				ch[i] = false;
			}
		}
	}
	
	//소수 판별
	private static boolean isPrim(int num) {
		for (int i = 2; i < num; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
}
