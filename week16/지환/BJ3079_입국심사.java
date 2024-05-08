import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Java8 264ms
public class BJ3079_입국심사 {
	static int n, m;
	static long[] times;
	static long maxTime;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		times = new long[n];
		for(int i=0; i<n; i++) {
			times[i] = Long.parseLong(br.readLine());
		}
		maxTime = 1000000000L * (long)m;
		System.out.println(binarySearch());
	}
	//lowerBound로 최소 시간 계산
	public static long binarySearch() {
		long left = 1; long right = maxTime;
		long mid;
		while(left <= right) {
			mid = (left+right)/2;
			if(calculate(mid) >= m) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}
	//특정 시간당 입국심사를 받을 수 있는 인원의 최대수
	public static long calculate(long mid) {
		long sum = 0;
		for(long time: times) {
			sum += mid/time;
			if(sum > m) break;
		}
		return sum;
	}

}
