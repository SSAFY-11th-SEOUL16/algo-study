import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int [] parent;
	static ArrayList<edge> edgeData;
	static class loc{
		int num;
		double x;
		double y;
		
		loc(int num, double x, double y){
			this.num = num;
			this.x = x;
			this.y = y;
		}
	}
	static class edge implements Comparable<edge>{
		int start, end;
		double len;
		
		edge(int start, int end, double len){
			this.start = start;
			this.end = end;
			this.len = len;
		}
		
		@Override
		public int compareTo(edge o) {
			if(len < o.len) {
				return -1;
			}
			return 1;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer str = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(str.nextToken());
		m = Integer.parseInt(str.nextToken());
		
		parent = new int[n+1];
		for(int i = 1; i <= n; i++) {
			parent[i]=i;
		}
		
		loc [] data  = new loc[n+1];
		
		for(int i = 1; i <= n; i++) {
			str = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(str.nextToken());
			double y = Double.parseDouble(str.nextToken());
			
			data[i] = new loc(i,x,y);
		}
		
		for(int i = 0; i <m; i++) {
			str = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(str.nextToken());
			int end = Integer.parseInt(str.nextToken());
			
			union(start, end);
		}
		
		edgeData = new ArrayList<>();
		for(int x = 1; x <= n; x++) {
			for(int y = x+1; y <= n; y++) {
				double len = distance(data[x], data[y]);
				edgeData.add(new edge(data[x].num, data[y].num, len));
			}
		}
		
		Collections.sort(edgeData);
		
		double result = 0;
		for(int i = 0; i < edgeData.size(); i++) {
			edge tmp = edgeData.get(i);
			if(find(tmp.start) != find(tmp.end)) {
				result += tmp.len;
				union(tmp.start, tmp.end);
			}
		}
		
		bw.write(String.format("%.2f", result)+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static double distance(loc data1, loc data2) {
		return Math.sqrt(Math.pow(data1.x - data2.x, 2) + Math.pow(data1.y - data2.y, 2));
	}
	
	static int find(int idx) {
		if(idx == parent[idx]) {
			return idx;
		}
		
		return parent[idx] = find(parent[idx]);
	}
	
	static void union(int start, int end) {
		start = find(start);
		end = find(end);
		
		
		if(start != end) {
			parent[end] = start;
		}
	}
}
