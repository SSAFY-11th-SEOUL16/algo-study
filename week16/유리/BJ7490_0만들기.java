import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++) {
			n = Integer.parseInt(br.readLine());
			
			cal("1",2);
			
			System.out.println();
		}
	}
	static void cal(String str, int tmpNum) {
		if(tmpNum == n+1) {
			int tmpResult = 0;
			int tmpNumber = 0;
			char tmpSign = '+';
			for(char tmp : str.replace(" ", "").toCharArray()) {
				//System.out.println(tmp + "/"+ tmpResult + "/"+ tmpSign+tmpNumber);
				if(tmp != '+' && tmp!= '-') {
					//System.out.println("숫자입니다");
					tmpNumber = tmpNumber*10 + ((int)tmp-(int)'0');
				}else {
					//System.out.println("기호입니다.");
					if(tmpSign == '+') {
						tmpResult +=tmpNumber;
					}else {
						tmpResult-=tmpNumber;
					}
					tmpSign = tmp;
					tmpNumber = 0;
				}
			}
			
			if(tmpSign == '+') {
				tmpResult +=tmpNumber;
			}else {
				tmpResult-=tmpNumber;
			}
			
			if(tmpResult == 0) {
				System.out.println(str);
			}
			//System.out.println("====================================");
			return;
		}
		
		cal(str+" "+tmpNum, tmpNum+1);
		cal(str+"+"+tmpNum, tmpNum+1);
		cal(str+"-"+tmpNum, tmpNum+1);
	}
}
