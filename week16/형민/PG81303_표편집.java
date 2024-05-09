import java.io.*;
import java.util.*;

class Solution {

    static public String solution(int n, int k, String[] cmd) {
        LinkedList<Character> linkedList = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            linkedList.addLast('O');
        }

        Stack<Integer> stack = new Stack<>();
        int moveCnt = 0;
        for(String cm : cmd) {
            char c = cm.charAt(0);

            if(c == 'U') {
                moveCnt = cm.charAt(2) - '0';
                k -= moveCnt;
            } else if(c == 'D') {
                moveCnt = cm.charAt(2) - '0';
                k += moveCnt;
            } else if(c == 'C') {
                if(k == linkedList.size() - 1) {
                    linkedList.remove(k);
                    stack.push(k);
                    k -= 1;
                } else {
                    linkedList.remove(k);
                    stack.push(k);
                }

            } else if(c == 'Z') {
                int pop = stack.pop();
                linkedList.add(pop, 'O');
            }
        }

        while(!stack.isEmpty()) {
            linkedList.add(stack.pop(), 'X');
        }

        StringBuilder sb = new StringBuilder();
        for (Character c : linkedList) {
            sb.append(c);
        }

        return sb.toString();
    }
}