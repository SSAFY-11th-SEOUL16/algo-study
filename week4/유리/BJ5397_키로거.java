import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		String [] data;
		while(t > 0) {
			t--;
			int count = 0;
			data = br.readLine().split("");
			Stack<String> leftResult = new Stack<>();
			Stack<String> rightResult = new Stack<>();
			for(String tmp : data) {
				if(tmp.contains("<")) {
					if(!leftResult.isEmpty()) {
						rightResult.push(leftResult.pop());
					}
				}else if(tmp.contains(">")) {
					if(!rightResult.isEmpty()) {
						leftResult.push(rightResult.pop());
					}
				}else if(tmp.contains("-")) {
					if(!leftResult.isEmpty()) {
						leftResult.pop();
					}
				}else {
					leftResult.push(tmp);
				}
				count++;
			}
			while(!leftResult.isEmpty()) {
				rightResult.push(leftResult.pop());
			}
			while(!rightResult.isEmpty()) {
				sb.append(rightResult.pop());
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
