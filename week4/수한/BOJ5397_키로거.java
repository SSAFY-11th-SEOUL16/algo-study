package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class Main_5397_키로거{
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		/*
		 * 강산이 비밀번호 알아내기 
		 * 백스페이스 : -
		 * <> : 커서 움직임
		 * 
		 * 0 1 2
		 * A|B
		 * 
		 * 
		 */
				
		int T = Integer.parseInt(br.readLine());
		
		Deque<Character> q1;
		Deque<Character> q2;
		q1 = new ArrayDeque<>();
		q2 = new ArrayDeque<>();
		
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			for (char c : br.readLine().toCharArray()) {
				switch(c) {
					case '-':
						if(!q1.isEmpty())
							q1.pollLast();
						break;
					case '<':
						if(!q1.isEmpty())
							q2.offerFirst(q1.pollLast());
						break;
					case '>':
						if(!q2.isEmpty())
							q1.offerLast(q2.pollFirst());
						break;
					default:
						q1.offer(c);
						break;
				}
			}
			
			while(!q1.isEmpty()) sb.append(q1.poll());
			while(!q2.isEmpty()) sb.append(q2.poll());
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}
