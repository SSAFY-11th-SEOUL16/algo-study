import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        
        //소수 구하기
        boolean prime[] = new boolean[n+1];        
        ArrayList<Integer> prime_numbers = new ArrayList<>();
        
        prime[0] = prime[1] = true;       
        for(int i=2; i*i<=n; i++){
            if(!prime[i]) for(int j=i*i; j<=n; j+=i) prime[j]=true;                
        }            
        for(int i=1; i<=n;i++){
        	if(!prime[i]) {
        		prime_numbers.add(i);     
        	}
        }
        
        //경우의 수 찾기
        int left, right, sum, result;
        left = right = sum = result = 0;
        
        while(true) {
        	if(sum >= n) {
        		if(sum == n) result++;
        		sum -= prime_numbers.get(left++);
        	} else if(right == prime_numbers.size()) {
        		break;
        	} else if(sum < n) {
        		sum += prime_numbers.get(right++);
        	}
        }
        
        System.out.println(result);
	}
}
