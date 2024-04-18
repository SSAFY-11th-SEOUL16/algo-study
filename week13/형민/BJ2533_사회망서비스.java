import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int n;
	
	static class Edge {
		int u,v;

		public Edge(int u, int v) {
			super();
			this.u = u;
			this.v = v;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++) {
        	st = new StringTokenizer(br.readLine());
        	Edge e = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
	}
}
