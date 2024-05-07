import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BJ_5052_전화번호목록 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<test; t++) {
			Set<String> set = new HashSet<>();
			int n = Integer.parseInt(br.readLine());
			for(int i=0; i<n; i++) {
				String number = br.readLine();
				set.add(number);
			}

			if(check(set)) sb.append("YES").append("\n");
			else sb.append("NO").append("\n");
		}

		System.out.println(sb);

	}

	public static boolean check(Set<String> set) {
		for(String number: set) {
			for(int i=0; i<number.length(); i++) {
				if(set.contains(number.substring(0, i))) return false;
			}
		}
		return true;
	}
}
