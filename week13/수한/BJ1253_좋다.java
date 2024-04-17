package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1253_좋다 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(tokens.nextToken());
            nums[i] = num;
        }

        Arrays.sort(nums);

        int answer = 0;
        for (int i = 0; i < N; i++) {
        	// i번째 수를 만들 수 있는지 투포인터로 확인
        	int left = 0;
        	int right = N-1;

        	while(true) {
                if(right == i) right--;
                else if(left == i) left++;

                if(left == right) break;

                int sum = nums[left] + nums[right];

        		if(sum == nums[i]) {
        			answer++;       			
        			break;
        		}else if(sum > nums[i]) {
        			right--;
        		}else{
        			left++;
        		}
        	}
        }        
        
        System.out.println(answer);
    }
}