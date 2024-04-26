import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		
		int n = Integer.parseInt(br.readLine());
		ArrayList<ArrayList<Integer>> data = new ArrayList<>();
		for(int i = 0; i <= n; i++) {
			data.add(new ArrayList<>());
		}
		
		int [] before = new int[n+1];
		int [] time = new int [n+1];
		
		for(int i = 1; i <= n; i++) {
			str = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(str.nextToken());
			
			while(true) {
				int tmp = Integer.parseInt(str.nextToken());
				if(tmp == -1) {
					break;
				}
				data.get(tmp).add(i);
				before[i]++;
			}
		}
		
		String result = topologicalSort(data, before, time, n);
		System.out.println(result);
	}

	private static String topologicalSort(ArrayList<ArrayList<Integer>> data, int[] before, int[] time, int n) {
		Queue<Integer> q = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i <= n; i++) {
			if(before[i] == 0) {
				q.offer(i);
			}
		}
		
		int [] resultData = new int [n+1];
		
		while(!q.isEmpty()) {
			int tmp = q.poll();
			
			for(int tmp2 : data.get(tmp)) {
				before[tmp2]--;
				resultData[tmp2] = Math.max(resultData[tmp2],resultData[tmp]+time[tmp]);
				
				if(before[tmp2] == 0) {
					q.offer(tmp2);
				}
			}
		}
		
		for(int i = 1; i <= n; i++) {
			sb.append(resultData[i]+time[i]+"\n");
		}
		
		return sb.toString();
	}
}
