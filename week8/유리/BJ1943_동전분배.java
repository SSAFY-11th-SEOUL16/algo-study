import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class coinData{
		coinData(int coinNum, int count){
			this.coinNum = coinNum;
			this.count = count;
		}
		int coinNum, count;
	}
	static int n, total;
	static List<coinData> data;
	static int [][] check;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		for(int i = 1; i <=3; i++) {
			n = Integer.parseInt(br.readLine());
			data = new ArrayList<>();
			total = 0;
			for(int x = 0; x < n; x++) {
				str = new StringTokenizer(br.readLine());
				int coinNum = Integer.parseInt(str.nextToken());
				int count = Integer.parseInt(str.nextToken());
				data.add(new coinData(coinNum, count));
				total+=coinNum*count;
			}
			if(total %2 != 0) {
				System.out.println(0);
			}else {
				total/=2;
				check = new int[n+1][total+1];
				
				for(int x = 0; x <=n; x++ ) {
					for(int y = 0; y <= total; y++) {
						check[x][y] = -1;
					}
				}
				
				check[0][0] = 0;
				boolean check2 = false;
				for(int x = 0; x < n; x++) {
					coinData tmpData = data.get(x);
					for(int z = 0; z <= total; z++) {
						if(check[x][z] >= 0 && check[x][z] <= tmpData.count) {
							if(check[x][z] < tmpData.count && z+tmpData.coinNum <= total && check[x][z+tmpData.coinNum] == -1) {
								check[x][z+tmpData.coinNum] = check[x][z]+1;
								check[x+1][z+tmpData.coinNum] = 0;
							}
							check[x+1][z] = 0;
						}
						if(check[x+1][total] == 0) {
							check2 = true;
							break;
						}
					}
					/**
					System.out.println(x + " : ");
					for(int x2 = 0; x2 <=n; x2++ ) {
						for(int y2 = 0; y2 <= total; y2++) {
							if(check[x2][y2] >= 0) {
								System.out.print(check[x2][y2]);
							}else {
								System.out.print("-");
							}
						}
						System.out.println();
					}
					**/
					if(check2) {
						break;
					}
				}
				
				
				if(check[n][total] == 0 || check2) {
					System.out.println(1);
				}else {
					System.out.println(0);
				}
			}
		}
	}
}
