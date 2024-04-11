import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//Java8 636ms
public class BJ19640_화장실의규칙 {
	//줄마다 Queue를 생성
	static Queue<Person>[] lines;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		lines = new LinkedList[m];
		for(int i=0; i<m; i++) {
			lines[i] = new LinkedList<>();
		}
		//한명씩 돌아가면서 줄에 세운다.
		//idx는 데카를 식별하기 위한 사람들의 식별자
		//데카가 k+1번째로 줄을 섰으므로 k+1번
		int row = 0;
		int idx = 1;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			lines[row%m].add(new Person(idx, row%m, d, h));
			row++;
			idx++;
		}
		
		System.out.println(solve(m,k));
	}
	
	public static int solve(int m, int k) {
		int cnt = 0;
		//각 줄의 선두들을 저장하는 우선순위큐
		Queue<Person> first = new PriorityQueue<>();
		//가장 처음 선두들을 넣어준다.
		for(int i=0; i<m; i++) {
			if(!lines[i].isEmpty()) {
				first.add(lines[i].remove());
			}
		}
		//선두 우선순위 큐에서 한명을 꺼내고, 꺼낸 사람의 줄에 선두가 바뀌었으므로 반영
		while(true) {
			Person now = first.remove();
			//데카 차례가 오면 break
			if(now.idx == k+1) break;
			if(!lines[now.row].isEmpty()) {
				first.add(lines[now.row].remove());
			}
			cnt++;
		}
		return cnt;
	}
	
	//식별자, 줄 번호, 근무 일수, 급한 정도
	static class Person implements Comparable {
		int idx;
		int row;
		int d;
		int h;
		
		Person(int idx, int row, int d, int h) {
			this.idx = idx;
			this.row = row;
			this.d = d;
			this.h = h;
		}

		@Override
		public int compareTo(Object o) {
			Person p = (Person) o;
			if(this.d==p.d && this.h==p.h) return this.row-p.row;
			if(this.d==p.d) return p.h-this.h;
			return p.d - this.d;
		}
	} 
}
