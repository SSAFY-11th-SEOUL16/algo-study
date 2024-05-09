import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static List<String> list = new ArrayList<>();

    public static void dfs(int index, int num, int sum, int op, String str) {
        if (index == n) {
            sum += num*op;
            if(sum == 0)
                list.add(str);
            return;
        }

        dfs(index + 1, index + 1, sum + (op * num), 1, str + "+" + Integer.valueOf(index + 1)); // 더하는 경우
        dfs(index + 1, index + 1, sum + (op * num), -1, str + "-" + Integer.valueOf(index + 1)); // 빼는 경우
        dfs(index + 1, num * 10 + (index + 1), sum, op, str + " " + Integer.valueOf(index + 1));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            n = Integer.parseInt(br.readLine());

            dfs(1, 1, 0, 1, "1");
            Collections.sort(list);
            for(String str : list)
                sb.append(str).append('\n');
            sb.append('\n');
            list.clear();
        }
        System.out.println(sb);
    }

}