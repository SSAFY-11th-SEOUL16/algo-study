import java.io.*;
import java.util.*;

public class BOJ19640_화장실의규칙 {

    static class Node {
        int days, degree;

        public Node(int days, int degree) {
            this.days = days;
            this.degree = degree;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); //사원 수
        int m = Integer.parseInt(st.nextToken()); //새로운 줄의 수
        int k = Integer.parseInt(st.nextToken()); //앞에 서 있던 사원의 수
        boolean[] possible = new boolean[m+1];
        Node arr[] = new Node[n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int myGroup; //속한 그룹

        if(k+1 <= m) myGroup = k + 1;
        else myGroup = (k + 1) - ((k + 1) / m) * m;

        Node cur = arr[k+1];

        int curGroup = 0;
        int result = 0;
        for (int i = 1; i <= n; i++) {
        	curGroup++;
            if(curGroup == m+1) curGroup = 1;
        	
        	//현재 그룹이 막힌 경우
        	if(possible[curGroup]) continue;

        	if(i < k+1) {
        		if(curGroup == myGroup) { //같은 그룹일 떄
        			result++; 
        			continue;
        		}
        		
                if(arr[i].days > cur.days) result++;
                else if(arr[i].days == cur.days && arr[i].degree > cur.degree) result++;
                else if(arr[i].days == cur.days && arr[i].degree == cur.degree && myGroup > curGroup) result++;
                else possible[curGroup] = true;
            } else if(i > k+1) {
                if(curGroup == myGroup) continue; //같은 그룹일 때

                if(arr[i].days > cur.days) result++;
                else if(arr[i].days == cur.days && arr[i].degree > cur.degree) result++;
                else if(arr[i].days == cur.days && arr[i].degree == cur.degree && myGroup > curGroup) result++;
                else possible[curGroup] = true;
            }
        }

        System.out.println(result);
    }
}