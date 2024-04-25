import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int p, v; //가격, 만족도

        public Node(int p, int v) {
            this.p = p;
            this.v = v;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        Node nodes[] = new Node[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            nodes[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(nodes, (o1, o2) ->  o1.p - o2.p);

        int left = 0, right = 0;
        long like = 0, result = 0;

        //슬라이딩윈도우
        while(right != n) {
            int diffCost = nodes[right].p - nodes[left].p; //차이 가격
            if(diffCost < d) {
                like += nodes[right].v;
                result = Long.max(result, like);
                right++;
            } else {
                like -= nodes[left].v;
                result = Long.max(result, like);
                left++;
            }
        }

        System.out.println(result);
    }
}
