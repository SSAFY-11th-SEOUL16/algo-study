package week4.상현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ5397_키로거 {
    /**
     * 처음 코드
     */
//    public static void main(String[] args) throws IOException {
//        StringBuilder sb = new StringBuilder();
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int T = Integer.parseInt(st.nextToken());
//
//        for (int tc = 0; tc < T; tc++) {
//            st = new StringTokenizer(br.readLine());
//
//            char[] input = st.nextToken().toCharArray();
//
//            LinkedList<Character> list = new LinkedList<>();
//            int nowIdx = 0;
//            for (int i = 0; i < input.length; i++) {
//                if (input[i] == '<') {
//                    if (nowIdx > 0) {
//                        nowIdx -= 1;
//                    }
//                    continue;
//                } else if (input[i] == '>') {
//                    if (nowIdx < list.size()) {
//                        nowIdx += 1;
//                    }
//                    continue;
//                } else if (input[i] == '-') {
//                    if (nowIdx > 0) {
//                        list.remove(nowIdx - 1);
//                        nowIdx -= 1;
//                    }
//                    continue;
//                }
//                list.add(nowIdx, input[i]);
//                nowIdx += 1;
//
//            }
//
//            for (Character c : list) {
//                sb.append(c);
//            }
//            sb.append("\n");
//        }
//        System.out.println(sb);
//    }

    /**
     * 1. 스택 두개를 통해 <,>,-에 대한 처리를 진행
     * 2. 뒤로 가는 이동 < 해당 문자열일 경우 insertStack의 제일 위의 문자를 pop한 후 delStack에 push
     * 3. 앞으로 가는 문자 > 일 경우 반대로 delStack에 있던 문자들을 insertStack으로 이동
     * 4. -는 그대로 처리해도 상관 없음 커서 위치가 변했다면 이미 그만큼 delStack에 쌓여있거나 insert스택으로 이동시킨 상태이기 때문
     */
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < T; tc++) {
            StringBuilder tmp = new StringBuilder();

            Stack<Character> inserStack = new Stack<>();
            Stack<Character> delStack = new Stack<>();

            st = new StringTokenizer(br.readLine());
            char[] input = st.nextToken().toCharArray();

            for (int i = 0; i < input.length; i++) {
                switch (input[i]) {
                    case '<' :
                        if (!inserStack.isEmpty()) {
                            delStack.add(inserStack.pop());
                        }
                        break;
                    case '>' :
                        if (!delStack.isEmpty()) {
                            inserStack.add(delStack.pop());
                        }
                        break;
                    case '-'  :
                        if (!inserStack.isEmpty()) {
                            inserStack.pop();
                        }
                        break;
                    default :
                        inserStack.add(input[i]);
                }
            }

            while (!delStack.isEmpty()) {
                inserStack.add(delStack.pop());
            }

            for (int i = 0; i < inserStack.size(); i++) {
                tmp.append(inserStack.get(i));
            }
            sb.append(tmp).append("\n");
        }
        System.out.println(sb);
    }
}
