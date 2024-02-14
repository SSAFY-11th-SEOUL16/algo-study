import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int cursor = 0;
            String input = br.readLine();
            LinkedList<Character> list = new LinkedList<>();

            for (char c : input.toCharArray()) {
                if(c == '-') { //백스페이스
                    if(cursor == 0) continue; //지울 문자가 없는 경우
                    cursor--;
                    list.remove(cursor);
                } else if(c == '<') { //커서 왼쪽 이동
                    if(cursor == 0) continue; //맨 왼쪽인 경우
                    cursor--;
                } else if(c == '>') { //커서 오른쪽 이동
                    if(cursor >= list.size()) continue;
                    cursor++;
                } else {
                    list.add(cursor, c);
                    cursor++;
                }
            }

            for (Character c : list) sb.append(c);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}

