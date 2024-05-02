import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 84 ms
 * Knapsack
 * 단원 개수 -> 개수
 * 총 시간 -> 용량
 * 예상 공부 시간 -> 무게
 * 배점 -> 가치
 * 백트래킹 Knapsack 사용
 * */
public class BJ14728_벼락치기 {
	private static final class Item implements Comparable<Item> {
		int weight;
		int value;
		double ratio; // (가치 / 무게) 비율
		
		Item(int weight, int value) {
			this.weight = weight;
			this.value = value;
			this.ratio = (double) value / weight;
		}
		
		@Override
		public int compareTo(Item o) { // bound 계산을 위해 (가치 / 무게) 비율이 높은 순으로 정렬
			return Double.compare(o.ratio, this.ratio);
		}
	}
	
	private static int n;
	private static int t;
	private static int maxProfit;
	private static Item[] items;
	
	private static boolean promising(int depth, int weight, int profit) {
		int i;
		int bound;
		int totalWeight;
		
		if (weight >= t) { // 가방이 꽉 차면
			return false;
		}
		bound = profit; // 해당 노드 이하로의 탐색이 만들 수 있는 최대 가치 범위
		totalWeight = weight;
		for (i = depth; i < n && totalWeight + items[i].weight <= t; i++) {
			bound += items[i].value; // 범위 계산
			totalWeight += items[i].weight;
		}
		if (i < n) { // 남은 용량
			bound += (t - totalWeight) * items[i].ratio; // 마지막 짐을 분할해서 계산
		}
		return bound > maxProfit; // 범위가 현재까지 탐색된 최대 가치보다 클 경우 유망함
	}
	
	private static void knapsack(int depth, int weight, int profit) {
		if (weight <= t && profit > maxProfit) {
			maxProfit = profit; // 최대 가치 업데이트
		}
		if (promising(depth, weight, profit)) { // 유망한 노드인지 확인
			knapsack(depth + 1, weight + items[depth].weight, profit + items[depth].value); // 짐 선택
			knapsack(depth + 1, weight, profit); // 짐 선택 안함
		}
	}
	
	public static void main(String[] args) throws IOException {
		int i;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		items = new Item[n]; // 짐
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			items[i] = new Item(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(items); // (가치 / 무게)로 정렬
		maxProfit = 0;
		knapsack(0, 0, 0); // 백트래킹 Knapsack
		System.out.print(maxProfit); // 최대 가치 출력
	}
}
