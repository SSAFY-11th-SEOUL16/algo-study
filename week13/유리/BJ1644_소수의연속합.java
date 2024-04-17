import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main{
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//데이터 세팅
		int n = Integer.parseInt(br.readLine());
		boolean [] dataCheck = new boolean [n+1];
		ArrayList<Integer> data = new ArrayList<>();
		int result = 0;
		
		//소수 구하기, 에라토스테네스의 체 이용
    //2부터 시작하여 특정 수의 배수에 해당하는 수를 모두 지움
    //자기자신은 지우지 않으며 이미 지워진 수는 건너뜀
		for(int i = 2;i <= Math.sqrt(n); i++) {
			if(dataCheck[i]) {continue;}
			for(int x = i+i; x <= n; x+=i) {
				if(x%i == 0) {
					dataCheck[x] = true;
				}
			}
		}
    //남아있는 수를 모두 ArrayList에 담음
		for(int i = 2; i <= n; i++) {
			if(!dataCheck[i]) {
				data.add(i);
			}
		}
		
		//투포인터 사용
		int left = 0, right = 0, size = data.size(), sum = 2;	//제일 작은소수가 2이기 때문에 초기 sum의 값을 2로 설정
		while(left<size && right < size) {
			if(sum == n) {
				result++;
				sum -= data.get(left);
				left++;
			}
			else if(sum > n) {
				sum -= data.get(left);
				left++;
			}else {
				right++;
				if(right >= size) {
					break;
				}
				sum+=data.get(right);
			}
		}
		
		
		System.out.println(result);
	}
}
