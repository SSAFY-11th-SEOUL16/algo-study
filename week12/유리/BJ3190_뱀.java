import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, dCount = 0;
	static int [][] map;
	static Map<Integer, String> moveData;
	static int [] nr = {1, 0, -1, 0};
	static int [] nc = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		map = new int[n][n];
		moveData = new HashMap<>();
		for(int i = 0; i < k; i++) {
			str = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(str.nextToken()) -1;
			int y = Integer.parseInt(str.nextToken()) -1;
			map[x][y] = 1;
		}
		
		int l = Integer.parseInt(br.readLine());
		for(int i = 0; i < l; i++) {
			str = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(str.nextToken());
			String direction = str.nextToken();
			moveData.put(time, direction);
		}
		
		cal();
	}
	
	static void cal() {
		Queue<Integer> q = new LinkedList<>();
		q.add(0);
		int time = 0, x = 0, y = 0;
		while(true) {
			int tmpX = x + nr[dCount];
			int tmpY = y + nc[dCount];
			time++;
			
			if(tmpX < 0 || tmpY < 0 || tmpX+1 > n || tmpY+1 > n) {
				break;
			}
			
			if(q.contains(tmpY*n + tmpX)) {
				break;
			}
			
			if(map[tmpY][tmpX] == 1) {
				map[tmpY][tmpX] = 0;
				q.add(tmpY*n + tmpX);
			}else {
				q.add(tmpY*n + tmpX);
				q.poll();
			}
			
			if(moveData.containsKey(time)) {
				String data = moveData.get(time);
				if(data.equals("D")) {
					dCount+=1;
					if(dCount==4) {
						dCount=0;
					}
				}else {
					dCount -= 1;
					if(dCount == -1) {
						dCount = 3;
					}
				}
			}
			x = tmpX;
			y = tmpY;
		}
		System.out.println(time);
	}
}
