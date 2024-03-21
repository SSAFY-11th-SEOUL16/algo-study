import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static  StringBuilder sb = new StringBuilder();

    static class Node {
        HashMap<String, Node> childs = new HashMap<>();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        Node root = new Node();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int tmp = Integer.parseInt(st.nextToken());
            Node cur = root;

            for (int j = 0; j < tmp; j++) {
                String s = st.nextToken();

                if (!cur.childs.containsKey(s)) {
                    cur.childs.put(s, new Node());
                }
                cur = cur.childs.get(s);
            }
        }
        print(root, "");
        System.out.println(sb);
    }

    public static void print(Node root, String bar) {
        Object[] key = root.childs.keySet().toArray();
        Arrays.sort(key);

        for (Object s : key) {
            sb.append(bar).append(s).append('\n');
            print(root.childs.get(s), bar + "--");
        }
    }

}

