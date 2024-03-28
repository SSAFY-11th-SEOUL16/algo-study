import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class road implements Comparable<road>{
		int house1, house2, cost;
		road(int house1, int house2,int cost){
			this.house1 = house1;
			this.house2 = house2;
			this.cost = cost;
		}
		@Override
		public int compareTo(road o) {
			return this.cost - o.cost;
		}
	}
	static int n, m; 
	static double maxInt = Integer.MAX_VALUE, resultData = 0, count = 0;
	static int [] union;
	static road[] data;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(str.nextToken());
		m = Integer.parseInt(str.nextToken());
		union = new int [n+1];
		data = new road[m];
		for(int i = 1; i <= n; i++) {
			union[i] = i;
		}
		
		for(int i = 0; i < m; i++) {
			str = new StringTokenizer(br.readLine());
			data[i] = new road(Integer.parseInt(str.nextToken()), Integer.parseInt(str.nextToken()), Integer.parseInt(str.nextToken()));
		}
		
		Arrays.sort(data);
		for(int i = 0; i < m; i++) {
			if(count+2 >= n) {
				break;
			}
			road tmp = data[i];
			int tmp1  = unionCal(tmp.house1);
			int tmp2 = unionCal(tmp.house2);
			if(tmp1 != tmp2) {
				//System.out.println(data[i].house1 +"/" + data[i].house2 +"/" + data[i].cost+"/"+union[tmp.house1]  + "/" + union[tmp.house2]);
				if(tmp1 > tmp2) {
					union[tmp1] = tmp2;
				}else {
					union[tmp2] = tmp1;
				}
				resultData+= tmp.cost;
				count++;
			}

		}
        System.out.println((long)resultData);
	}
	
	static int unionCal(int x) {
		if(union[x] == x) {return x;}
		else {
			return union[x] = unionCal(union[x]);
		}
	}
}
