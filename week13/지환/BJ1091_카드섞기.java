import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Java8, 188ms
public class BJ1091_카드섞기 {
	static int n;
	static int[] new_card;
	static boolean cycle = false;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[] p = new int[n];
		int[] s = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			s[i] = Integer.parseInt(st.nextToken());
		}
		
		new_card = new int[n];
		int[] card = new int[n];
		//카드 배열 초기값 [0,1,2,.. ,n]
		for(int i=0; i<n; i++) {
			card[i] = i;
		}
		
		//p배열이 되지 않거나 싸이클이 돌지 않으면, 카드를 한번 더 섞어준다.
		int i;
		for(i=0; !check(card, p) && !cycle(i, card) ; i++) {
			move(card, s);
		}
		System.out.println(cycle ? -1 : i);
	}
	
	//card를 배열을 순회하며 사람들에게 나누어주었을 떄 p배열이 되어야한다.
	public static boolean check(int[] card, int[] p) {
		for(int i=0; i<n; i++) {
			if(p[card[i]]!=i%3) return false;
		}
		return true;
	}
	
	//카드를 섞다가 다시 처음 상태로 왔을 때
	public static boolean cycle(int idx, int[] card) {
		if(idx==0) return false;
		for(int i=0; i<n; i++) {
			if(card[i] != i) return false;
		}
		cycle = true;
		return true;
	}
	
	//카드를 섞는다.
	public static void move(int[] card, int[] s) {
		for(int i=0; i<n; i++) {
			new_card[s[i]] = card[i];
		}
		
		for(int i=0; i<n; i++) {
			card[i] = new_card[i];
		}
	}
}
