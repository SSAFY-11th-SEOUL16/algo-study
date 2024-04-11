import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
public class Main {
	//priority Queue 활용
	static class employee{
		int d, h, line;
		boolean check;
		employee(int d, int h, int line, boolean check){
			this.d = d;
			this.h = h;
			this.line = line;
			this.check = check;
		}
	}
	static int n,m,k;
	static List<employee> list[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
		n = Integer.parseInt(str.nextToken());
		m = Integer.parseInt(str.nextToken());
		k = Integer.parseInt(str.nextToken());
		
		list = new LinkedList[m];
		for(int i = 0; i < m; i++) {
			list[i] = new LinkedList<>();
		}
		
		int idx = 0;
		int count = n;
		while(--count >= 0) {
			str = new StringTokenizer(br.readLine());
			int tmpLine = idx++ % m;
			boolean check = (k-- == 0);
			list[tmpLine].add(new employee(Integer.parseInt(str.nextToken()),Integer.parseInt(str.nextToken()),tmpLine, check));
		}
		count = 0;
		PriorityQueue<employee> pQ = new PriorityQueue<>((o1, o2) -> o1.d == o2.d ? o1.h == o2.h ? o1.line - o2.line : o2.h - o1.h : o2.d - o1.d);
		
		for(int i = 0; i < m; i++) {
			if(list[i].size() > 0) {
				pQ.add(list[i].get(0));
			}
		}
		
		for(int i = 0; i < n; i++) {
			employee tmpEmployee = pQ.poll();
			if(tmpEmployee.check) {
				System.out.println(count);
				break;
			}else {
				count++;
				list[tmpEmployee.line].remove(0);
				if(list[tmpEmployee.line].size() > 0) {
					pQ.add(list[tmpEmployee.line].get(0));
				}
			}
		}
	}	
}
