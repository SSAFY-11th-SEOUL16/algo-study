import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Integer> al1 = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            List<Integer> al2 = new ArrayList<>();
            while (st.hasMoreTokens()) al2.add(Integer.parseInt(st.nextToken()));
            Collections.sort(al2, Collections.reverseOrder());

            if (al2.size() < l) al1.add(1); //과목을 신청한 학생보다 수강인원이 많은 경우
            else al1.add(al2.get(l - 1));
        }

        int result = 0;
        Collections.sort(al1);
        for (Integer i : al1) {
            m -= i;
            if (m >= 0) result += 1;
            else break;
        }

        System.out.println(result);
    }
}
