import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

/*
 * JAVA 
 * 428 ms
 * 
 * 자료구조, 우선순위 큐
 * 
 * PriorityQueue 2개를 사용하여 중간값 기준으로 값을 관리함
 * 
 * 최대 힙 : 중간값보다 큰 값을 저장
 * 최소 힙 : 중간값보다 작거나 같은 값을 저장
 * 
 * 숫자를 입력 받을 때마다, 해당 숫자를 적절한 힙에 추가
 * 추가한 후 두 힙의 최상단 값을 비교하여 최소 힙의 값이 최대 힙의 값보다 작은 경우, 두 값을 교환
 */

public class BJ1655_가운데를_말해요 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 최소 힙: 큰 값을 관리
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		// 최대 힙: 작은 값을 관리
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 최대 힙

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());

			if (minHeap.size() == maxHeap.size()) {
				maxHeap.add(num);
			} else {
				minHeap.add(num);
			}

			// 최소 힙과 최대 힙의 상위 값이 잘못 배치되어 있으면 교환
			if (!minHeap.isEmpty() && !maxHeap.isEmpty() && minHeap.peek() < maxHeap.peek()) {
				int minHeapVal = minHeap.poll();
				int maxHeapVal = maxHeap.poll();

				minHeap.add(maxHeapVal);
				maxHeap.add(minHeapVal);
			}
			sb.append(maxHeap.peek()).append("\n"); // 최대 힙의 상위 값(==중간값)을 결과에 추가
		}
		System.out.println(sb.toString());
	}
}