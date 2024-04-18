import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int p[], s[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        p = new int[n];
        s = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) p[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) s[i] = Integer.parseInt(st.nextToken());
        
        int init[] = p.clone();
        int copy[] = p.clone();
        
        int result = 0;
        while(true) {
        	boolean flag = false;
        	
        	for(int i=0;i<n;i++) {
        		if(p[i] != (i%3)) {
        			flag = true;
        			break;
        		}
        	}
        	
        	//원하는 수열을 만든 경우
        	if(!flag) break;
        	
        	//카드 섞기
        	for(int i=0;i<n;i++) {
        		copy[s[i]] = p[i];
        	}
        	
        	p = copy.clone();
        	
        	//처음의 배열로 돌아온 경우, 싸이클
        	for(int i=0;i<n;i++) {
        		if(p[i] != init[i]) flag = false;
        		if(!flag) break;
        	}
        		
        	result++;
        	
        	if(flag) {
        		result= -1;
        		break;
        	}
        }
        
        System.out.println(result);
	}
}
