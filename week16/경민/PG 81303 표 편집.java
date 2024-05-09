import java.util.Stack;

class Solution {
	public String solution(int n, int k, String[] cmd) {
		StringBuilder sb = new StringBuilder();
		Stack<Integer> delete = new Stack<>();
		int size = n;
		for (String str : cmd) {
			char c = str.charAt(0);

			if (c == 'D') {
				k += Integer.parseInt(str.substring(2));
			} else if (c == 'U') {
				k -= Integer.parseInt(str.substring(2));
			} else if (c == 'C') {
				delete.push(k);
				size--;
				if (k == size)
					k--;
			} else if (c == 'Z') {
				if (delete.pop() <= k)
					k++;
				size++;
			}
		}

		for (int i = 0; i < size; i++)
			sb.append("O");
		
		while(!delete.isEmpty())
			sb.insert(delete.pop(), "X");
		return sb.toString();
	}

}