import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//Java8 280ms
public class BJ3020_개똥벌레 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		//down은 석순, up은 종유석
		//두 배열은 높이마다 지나야하는 석순과 종유석의 개수를 저장하는 배열이다.
		int[] down = new int[h+1];
		int[] up = new int[h+1];
		int d = 0;
		
		//석순과 종유석의 맨 끝의 높이만 저장한다.
		//석순의 길이가 3이라면 down[3]에 1추가
		//이후 누적합으로 down[1], down[2]에 1씩 추가 될 것이다.(1,2,3 높이 모두 해당 석순을 거쳐야하므로)
		for(int i=0; i<n; i++) {
			int len = Integer.parseInt(br.readLine());
			if(d==0) {
				down[len]++;
			} else {
				up[h-len+1]++;
			}
			d = 1-d;
		}
		
		//누적합 방식으로 최종 장애물을 산정
		for(int i=2; i<=h; i++) {
			up[i] += up[i-1];
		}
		
		for(int i=h-1; i>=1; i--) {
			down[i] += down[i+1];
		}
		
		//높이마다 지나야하는 석순, 종유석의 최솟값을 구한다.
		int min = Integer.MAX_VALUE;
		for(int i=1; i<=h; i++) {
			min = Math.min(min, up[i]+down[i]);
		}
		int cnt = 0;
		for(int i=1; i<=h; i++) {
			if(min==(up[i]+down[i])) cnt++;
		}
		System.out.println(min + " " + cnt);
	}
}
