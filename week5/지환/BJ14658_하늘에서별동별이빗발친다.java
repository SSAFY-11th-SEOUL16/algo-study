package week5.지환;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Java(148ms)
public class BJ14658_하늘에서별동별이빗발친다 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] stars = new int[k][2];
        for(int i =0 ; i < k ; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            stars[i][0] = x; stars[i][1] = y;
        }
        //별을 품는 최소한의 개수는 1이 되어야함.
        int max = 1;
        //별 두개를 가장자리에 놓으며, 오른쪽 아래로 내려가는 사각형을 구한다.(나머지 방향은 위에서 계산을 하니까 구해줄 필요가 없다)
        for(int i=0; i<k; i++) {
        	for(int j=i+1; j<k; j++) {
        		int res = 0;
        		int sx = Math.min(stars[i][0], stars[j][0]);
        		int sy = Math.min(stars[i][1], stars[j][1]);
        		for(int[] star: stars) {
        			if(sx<=star[0]&&star[0]<=sx+l && sy<=star[1]&&star[1]<=sy+l) res++;
        		}
        		max = Math.max(max, res);
        	}
        }
        System.out.println(k-max);
    }
}
