import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ7579_앱 {
	
	/**
	 * 80 ms
	 * Knapsack
	 * M 바이트 이상의 메모리 확보,
	 * 	비용 c 합 최소로 앱 비활성화
	 * = (메모리 합 - M) 바이트까지만 사용,
	 * 	비용 c 합 최대로 앱 활성화
	 * = (무게 합 - M) 용량의 가방에
	 * 	가치 c 합 최대로 짐 채우기
	 * 백트래킹 Knapsack 사용
	 */
	
	private static final class Item implements Comparable<Item> {
		int weight;
		int value;
		double ratio; // (가치 / 무게) 비율
		
		Item(int weight) {
			this.weight = weight;
		}
		
		void setValue(int value) {
			this.value = value;
			this.ratio = (double) value / weight;
		}
		
		@Override
		public int compareTo(Item o) { // bound 계산을 위해 (가치 / 무게) 비율이 높은 순으로 정렬
			return Double.compare(o.ratio, this.ratio);
		}
	}
	
	private static int n, m, maxProfit;
	private static Item[] items;
	
	private static boolean promising(int depth, int weight, int profit) {
		int totalWeight, bound, i;
		
		if (weight >= m) { // 가방이 꽉 차면
			return false;
		}
		bound = profit; // 해당 노드 이하로의 탐색이 만들 수 있는 최대 가치 범위
		totalWeight = weight;
		for (i = depth + 1; i < n && totalWeight + items[i].weight <= m; i++) {
			bound += items[i].value; // 범위 계산
			totalWeight += items[i].weight;
		}
		if (i < n) { // 남은 용량
			bound += (m - totalWeight) * items[i].ratio; // 마지막 짐을 분할해서 계산
		}
		return bound > maxProfit; // 범위가 현재까지 탐색된 최대 가치보다 클 경우 유망함
	}
	
	private static void knapsack(int depth, int weight, int profit) {
		if (weight <= m && profit > maxProfit) {
			maxProfit = profit; // 최대 가치 업데이트
		}
		if (promising(depth, weight, profit)) { // 유망한 노드인지 확인
			knapsack(depth + 1, weight + items[depth + 1].weight, profit + items[depth + 1].value); // 짐 선택
			knapsack(depth + 1, weight, profit); // 짐 선택 안함
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int weight, value, weightSum, valueSum, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		items = new Item[n]; // 짐
		weightSum = 0; // 전체 무게
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			weight = Integer.parseInt(st.nextToken()); // 짐 무게
			items[i] = new Item(weight);
			weightSum += weight;
		}
		valueSum = 0; // 전체 가치
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			value = Integer.parseInt(st.nextToken()); // 짐 가치
			items[i].setValue(value);
			valueSum += value;
		}
		m = weightSum - m; // (무게 합 - M)으로 용량 설정
		Arrays.sort(items); // (가치 / 무게)로 정렬
		maxProfit = 0;
		knapsack(-1, 0, 0); // 백트래킹 Knapsack
		System.out.print(valueSum - maxProfit); // 비활성화이므로 (전체 가치 - 최대 가치 합) 출력
	}
}
