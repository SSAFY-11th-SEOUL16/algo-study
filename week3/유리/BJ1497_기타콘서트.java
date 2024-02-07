import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m, result, max;
	static String [] data;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(str.nextToken());
		m = Integer.parseInt(str.nextToken());
		data = new String [n];
		int check = 0;
		for(int i = 0; i < n; i++) {
			str = new StringTokenizer(br.readLine(), " ");
			str.nextToken();
			String tmp = str.nextToken();
			//System.out.println(tmp);
			if(tmp.contains("Y")) {
				check++;
			}
			data[i] = tmp;
		}
		if(check > 0 ) {
			result = 0;
			getGuitar(0,"", 0);
			System.out.println(result);
		}else {
			System.out.println(-1);
		}
	}
	
	
	private static void getGuitar(int count, String musicCount, int startnum) {
		
		if(count == n+1) {
			//System.out.println("count : "+ count + " musicCount : " + musicCount + " startNum : " + startnum + " result : " +result);
			return;
		}
		
		if(musicCount != "") {
			int tmpNum = musicCount.replace("N", "").length();
			if(max < tmpNum) {
				result = count;
				max = tmpNum;
			}else if(max == tmpNum && result > count) {
				result = count;
			}
			//System.out.println("count : "+ count + " musicCount : " + musicCount + " startNum : " + startnum + " tmpNum : " + tmpNum + " result : " +result);
			
			
		}
		for(int i = startnum; i < n ; i++) {
			if(musicCount == "") {
				getGuitar(count+1, data[i], i+1);
			}else {
				String [] tmp = data[i].split("");
				String [] tmp2 = musicCount.split("");
				String tmp3 = "";
				for(int x = 0; x<m; x++) {
					if(tmp[x].contains("Y") || tmp2[x].contains("Y")) {
						tmp3+="Y";
					}else {
						tmp3+="N";
					}
				}
				getGuitar(count+1, tmp3, i+1);
			}
		}
		
	}
}
