import java.util.Stack;
class Solution {
    public String solution(int n, int k, String[] cmd) {
		boolean [] check = new boolean[n];
		Stack<Integer> st = new Stack<>();
		StringBuilder sb = new StringBuilder();
		int tmpNum = 0;
		for(String tmp : cmd) {
			//System.out.println(k+"번째 위치 : " + tmp);
			
			if(tmp.contains("U")) {
				tmpNum = 0;
				for(char tmpChar : tmp.toCharArray()) {
					if(tmpChar != 'U' && tmpChar != ' ') {
						tmpNum=tmpNum*10+(int)(tmpChar-'0');
					}
				}
				for(int i = 0; i < tmpNum; i++) {
					k--;
					if(check[k]) {
						i--;
					}
				}
			}else if(tmp.contains("D")) {
				tmpNum = 0;
				for(char tmpChar : tmp.toCharArray()) {
					if(tmpChar != 'D' && tmpChar != ' ') {
						tmpNum=tmpNum*10+(int)(tmpChar-'0');
					}
				}
				for(int i = 0; i < tmpNum; i++) {
					k++;
					if(check[k]) {
						i--;
					}
				}
			}else if(tmp.contains("C")) {
				check[k] = true;
				st.add(k);
				boolean flag = false;
				while(true) {
					if(flag || k == n-1) {
						k--;
						if(!check[k]) {
							break;
						}
						flag = true;
					}else {
						k++;
						if(!check[k]) {
							break;
						}
					}
				}
			}else {
				check[st.pop()] = false;
			}
			
			//System.out.println();
		}
		
		for(boolean tmp : check) {
			if(tmp) {
				sb.append('X');
			}else {
				sb.append('O');
			}
		}
		return(sb.toString());
    }
}
