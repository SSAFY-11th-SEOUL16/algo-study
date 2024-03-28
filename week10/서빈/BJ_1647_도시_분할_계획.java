import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * java
 * 1860 ms
 * MST, 크루스칼
 * 
 * MST를 찾은 다음, 그 중에서 가장 큰 가중치를 가진 간선을 제거
 * => 마을을 두 개로 분할하는 비용을 최소화함
 * 결과값 - 가장 큰 유지비 = 두 마을의 유지비의 합의 최솟값
 */

public class BJ_1647_도시_분할_계획 {
	static int N, M;
	static ArrayList<House> houseList; // 길 정보 저장
	static int[] parent; // union-find

	static class House implements Comparable<House> {
		int start; // 시작점
		int end; // 끝점
		int price; // 유지비

		public House(int start, int end, int price) {
			this.start = start;
			this.end = end;
			this.price = price;
		}

		public int compareTo(House o) { // 유지비 기준으로 오름차순 정렬
			return Integer.compare(this.price, o.price);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int result = 0, max = 0;

		houseList = new ArrayList<>();
		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i; // 모든 노드의 부모를 자기 자신
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());

			houseList.add(new House(s, e, p));
		}

		Collections.sort(houseList); // 유지비 기준 정렬

		for (int i = 0; i < houseList.size(); i++) {
			House house = houseList.get(i);

			if (find(house.start - 1) != find(house.end - 1)) { // 두 집이 연결되어있지 않다면
				union(house.start - 1, house.end - 1); // 연결
				result += house.price; // 유지비를 결과값이 더함
				max = Math.max(max, house.price); // 가장 큰 유지비를 갱신 => 가장 큰 값을 끊으면 됨
			}
		}

		System.out.println(result - max); // 결과값 - 가장 큰 유지비
	}

	public static boolean union(int x, int y) {
		int xParent = find(x);
		int yParent = find(y);

		if (xParent == yParent)
			return false;

		if (xParent > yParent) {
			parent[xParent] = yParent;
		} else {
			parent[yParent] = xParent;
		}
		return true;
	}

	public static int find(int index) {
		if (index == parent[index]) {
			return index;
		}
		return parent[index] = find(parent[index]);
	}
}
