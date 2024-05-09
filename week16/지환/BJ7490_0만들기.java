import java.io.BufferedReader;
import java.io.InputStreamReader;

//Java8, 338ms
public class BJ7490_0만들기 {
	static StringBuilder res = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		for(int t=0; t<test; t++) {
			int n = Integer.parseInt(br.readLine());
			StringBuilder sb = new StringBuilder();
			sb.append(1);
			dfs(2, 1, n, sb);
			res.append("\n");
		}
		System.out.println(res);
	}

	public static void dfs(int idx, int len, int n, StringBuilder sb) {
		if(idx == n+1) {
			//System.out.println(sb);
			if(calculate(sb.toString()) == 0) {
				res.append(sb).append("\n");
			}
			return;
		}

		sb.append(" ").append(idx);
		dfs(idx+1, len+2, n, sb);
		sb.delete(len, len+2);
		sb.append("+").append(idx);
		dfs(idx+1, len+2, n, sb);
		sb.delete(len, len+2);
		sb.append("-").append(idx);
		dfs(idx+1, len+2, n, sb);
		sb.delete(len, len+2);
	}

	public static int calculate(String str) {
		str = str.replaceAll(" ", "");
		//System.out.println(str);
		String[] numbers = str.split("[+,-]");
		int idx = 1;
		int sum = Integer.parseInt(numbers[0]);
		for(int i=0; i<str.length(); i++) {
			char op = str.charAt(i);
			if(op == '+') {
				sum += Integer.parseInt(numbers[idx]);
				idx++;
			} else if(op == '-') {
				sum -= Integer.parseInt(numbers[idx]);
				idx++;
			}
		}
		return sum;
	}
}
