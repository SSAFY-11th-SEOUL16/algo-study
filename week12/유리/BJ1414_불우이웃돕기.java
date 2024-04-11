import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static int data[];
	static class Edge implements Comparable<Edge>{
		int start, end, w;
		public Edge(int start, int end, int w) {
			this.start = start;
			this.end = end;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.w > o.w ? 1 : -1;
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2)->o1[2]-o2[2]);
		int count = 0;
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		for(int i = 0; i < n; i++) {
			char[] arr = br.readLine().toCharArray();
			for(int x = 0; x < n; x++) {
				if(arr[x] == '0') {
					continue;
				}else {
					int t = toLen(arr[x]);
					count += t;
					if(i!=x && t!=0) {
						pQ.add(new Edge(i, x, toLen(arr[x])));
					}
				}
			}
		}
		data = new int[n+1];
		for(int x = 0; x <= n; x++) {
			data[x]=x;
		}
		int mst_len = 0;
		int used = 0;
		while(!pQ.isEmpty()) {
			Edge tmp = pQ.poll();
			if(find(tmp.start)!=find(tmp.end)) {
				union(tmp.start, tmp.end);
				mst_len+=tmp.w;
				used++;
			}
		}
		if(used==n-1) {
			System.out.println(count-mst_len);
		}else {
			System.out.println(-1);
		}
	}
	static void union(int x, int x2) {
		x = find(x);
		x2 = find(x2);
		if(x != x2) {
			data[x2] = x;
		}
	}
	
	static int find(int x) {
		if(x == data[x]) {
			return x;
		}else {
			return data[x] = find(data[x]);
		}
	}
	
	static int toLen(char x) {
		if(x >= 'a' && x <= 'z') {
			return x - 'a' + 1;
		}else {
			return x - 'A' + 27;
		}
	}
}
