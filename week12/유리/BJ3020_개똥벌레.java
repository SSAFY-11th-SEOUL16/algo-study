import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, H, lenVal = Integer.MAX_VALUE, result = 0;
	static int[] down, up;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(str.nextToken());
		H = Integer.parseInt(str.nextToken());
		
		down = new int[H+2];
		up= new int[H+2];
		
		for(int i = 0; i < N/2; i++) {
			int tmp = Integer.parseInt(br.readLine());
			down[tmp]++;
			tmp = H- Integer.parseInt(br.readLine())+1;
			up[tmp]++;
		}
		
		for(int i = 1; i<=H; i++) {
			down[i]+=down[i-1];
		}
		for(int i = H; i >= 1; i--) {
			up[i]+=up[i+1];
		}
		for(int i = 1; i < H+1; i++) {
			int tmp = (down[H]-down[i-1]) + (up[1]-up[i+1]);
			if(tmp < lenVal) {
				lenVal = tmp;
				result = 1;
			}else if(tmp == lenVal) {
				result++;
			}
		}
		
		
		System.out.println(lenVal+" "+result);
	}
}
