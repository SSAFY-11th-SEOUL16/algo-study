import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
	     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	     StringTokenizer st;
	     
	     int n = Integer.parseInt(br.readLine());
	     int degree[] = new int[n+1];
	     int times[] = new int[n+1];
	     int result[] = new int[n+1];
	     ArrayList<ArrayList<Integer>> al = new ArrayList();
	     
	     for(int i=0;i<=n;i++) {
	    	 al.add(new ArrayList<>());
	     }
	     
	     for(int i=1;i<=n;i++) {
	    	 st = new StringTokenizer(br.readLine());
	    	 times[i] = Integer.parseInt(st.nextToken());
	    	 
	    	 while(true) {
	    		 int pre = Integer.parseInt(st.nextToken());
	    		 if(pre == -1) break;
	    		 
	    		 degree[i]++;
	    		 al.get(pre).add(i);
	    	 }
	     }
	     
	     while(true) {
	    	 int idx = -1;
	    	 
	    	 //차수 0인 것 구하기
	    	 for(int i=1;i<=n;i++) {
	    		 if(degree[i] == 0) {
	    			 idx = i;
	    			 break;
	    		 }
	    	 }
	    	 
	    	 if(idx == -1) break;
	    	 if(result[idx] == 0) result[idx] = times[idx];
	    	 
	    	 degree[idx] = Integer.MAX_VALUE;
	    	 
	    	 for(int i : al.get(idx)) {
	    		 degree[i]--;
	    		 result[i] = Math.max(result[i], result[idx] + times[i]);
	    	 }
	     }
	     
	     for(int i=1;i<=n;i++) System.out.println(result[i]);
	}

}
