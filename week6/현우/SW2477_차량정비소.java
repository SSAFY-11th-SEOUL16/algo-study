import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW2477_차량정비소 {
	
	/**
	 * 146 ms
	 * 시간 증가시키면서 모든 고객이 마칠 때까지 진행
	 * 현재 시각에 도착한 고객들 인덱스 부여해서 qA로 이동
	 * 접수 끝난 창구의 고객 qB로 이동
	 * 정비 끝난 창구의 고객 나오면서 이용한 창구들 확인
	 * qA에서 접수 완료 시각, 접수 창구 넣어서 빈 접수 창구로 이동
	 * qB에서 정비 완료 시각 넣어서 빈 정비 창구로 이동
	 */
	
	private static final int MAX_N = 9;
	private static final int MAX_M = 9;
	private static final int MAX_TK = 1001;
	private static final Customer EMPTY = null;
	
	private static class Customer { // 고객
		int idx, time, a; // 인덱스, 접수/정비 완료 시각, 이용한 접수 창구
		
		Customer(int idx) {
			this.idx = idx;
		}
		
		Customer setA(int a) { // 접수 창구 세팅
			this.a = a;
			return this;
		}
		
		Customer setTime(int time) { // 접수/정비 완료 시각 세팅
			this.time = time;
			return this;
		}
	}
	
	private static int n, m, k, a, b;
	private static int[] ai, bi, tk;
	private static Customer[] reception, repair;
	private static Queue<Customer> qA, qB;
	
	private static int repairShop() {
		int time, cnt, num, idx, ans, i;
		Customer customer;
		
		ans = 0;
		idx = 1;
		for (cnt = 0, time = 0; cnt < k; time++) { // 시간 증가시키면서 진행
			if (time < MAX_TK) {
				num = tk[time]; // 지금 도착한 고객들
				for (i = 0; i < num; i++, tk[time]--) {
					qA.add(new Customer(idx++)); // qA에서 접수 대기
				}
			}
			for (i = 0; i < n; i++) {
				customer = reception[i];
				if (customer != EMPTY && customer.time == time) { // 접수 끝난 고객
					qB.add(customer); // qB에서 정비 대기
					reception[i] = EMPTY; // 접수 창구 비우기
				}
			}
			for (i = 0; i < m; i++) {
				customer = repair[i];
				if (customer != EMPTY && customer.time == time) { // 정비 끝난 고객
					if (customer.a == a && i == b) { // 이용한 접수/정비 창구 확인
						ans += customer.idx; // 전화
					}
					repair[i] = EMPTY; // 정비 창구 비우기
					cnt++; // 정비 완료 고객 카운트
				}
			}
			for (i = 0; !qA.isEmpty() && i < n; i++) {
				if (reception[i] == EMPTY) { // 빈 접수 창구
					reception[i] = qA.poll().setA(i).setTime(time + ai[i]); // 접수 창구, 접수 완료 시각 세팅
				} // qA에서 접수 창구로 이동
			}
			for (i = 0; !qB.isEmpty() && i < m; i++) {
				if (repair[i] == EMPTY) { // 빈 정비 창구
					repair[i] = qB.poll().setTime(time + bi[i]); // 정비 완료 시각 세팅
				} // qB에서 정비 창구로 이동
			}
		}
		return ans == 0 ? -1 : ans;
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken()) - 1;
		b = Integer.parseInt(st.nextToken()) - 1;
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) { // 접수 시간 입력
			ai[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < m; i++) { // 정비 시간 입력
			bi[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < k; i++) { // 도착 시간별 고객 수
			tk[Integer.parseInt(st.nextToken())]++;
		}
		return repairShop();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		reception = new Customer[MAX_N];
		repair = new Customer[MAX_M];
		ai = new int[MAX_N];
		bi = new int[MAX_M];
		tk = new int[MAX_TK];
		qA = new ArrayDeque<>();
		qB = new ArrayDeque<>();
		t = Integer.parseInt(br.readLine());
		for (testCase = 1; testCase <= t; testCase++) {
			sb.append('#').append(testCase).append(' ').append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}
