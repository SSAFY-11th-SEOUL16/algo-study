import java.util.Arrays;
class Solution {
    public int solution(String[] words) {
        Arrays.sort(words);
		
		char [][] data = new char[words.length][];
		for(int i = 0; i < words.length; i++) {
			data[i] = words[i].toCharArray();
		}
		
		int answer = 0;
		boolean check = false;
		for(int i = 0; i < Math.min(data[0].length, data[1].length); i++) {
			if(data[0][i] == data[1][i]) {
				answer++;
			}
			else {
				answer++;
				check = true;
				break;
			}
		}
		if(!check && data[1].length < data[0].length) {
			answer++;
		}
		//System.out.println(answer);
		
		check = false;
		for(int i = 0; i < Math.min(data[data.length-1].length, data[data.length-2].length); i++) {
			if(data[data.length-1][i] == data[data.length-2][i]) {
				answer++;
			}
			else {
				answer++;
				check = true;
				break;
			}
		}
		if(!check && data[data.length-2].length < data[data.length-1].length) {
			answer++;
		}
		//System.out.println(answer);
		
		for(int i = 1; i < data.length-1; i++) {
			check = false;
			int tmp = 0;
			for(int x = 0; x < Math.min(data[i-1].length, data[i].length); x++) {
				if(data[i-1][x] == data[i][x]) {
					tmp++;
				}else {
					check = true;
					tmp++;
					break;
				}
			}
			if(!check && data[i-1].length < data[i].length) {
				tmp++;
			}
			
			check = false;
			int tmp2 = 0;
			for(int x = 0; x < Math.min(data[i+1].length, data[i].length); x++) {
				if(data[i+1][x] == data[i][x]) {
					tmp2++;
				}else {
					check = true;
					tmp2++;
					break;
				}
			}
			if(!check && data[i+1].length < data[i].length) {
				tmp2++;
			}
			
			answer += Math.max(tmp, tmp2);
			//System.out.println(answer);
		}
		
		//System.out.println(answer);
        return answer;
    }
}
