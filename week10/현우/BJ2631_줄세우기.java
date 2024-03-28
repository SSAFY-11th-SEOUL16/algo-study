import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2631_줄세우기 {
	
	/**
	 * 76 ms
	 * LIS
	 * 이미 정렬되어 있는 가장 긴 배열을 제외하고
	 * 나머지 아이들만 위치 변경
	 * N - (LIS 길이)
	 */
	
	private static int[] arr;
	
	public static int lowerBound(int num, int right) {
		int left, mid;
		
		left = 0;
		while (left < right) { // lowerBound 이분 탐색
			mid = (left + right) / 2;
			if (arr[mid] < num) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n, size, idx, num, i;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n]; // LIS 탐색을 위한 배열
		size = 0;
		for (i = 0; i < n; i++) {
			num = Integer.parseInt(br.readLine());
			idx = lowerBound(num, size);
			arr[idx] = num; // lowerBound 위치에 삽입
			if (idx == size) { // 가장 뒤에 추가된 경우
				size++; // LIS 길이 + 1
			}
		}
		System.out.print(n - size); // N - (LIS 길이)
	}
}
