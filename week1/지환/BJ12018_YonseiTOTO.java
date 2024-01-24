package week1.지환;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ12018_YonseiTOTO {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] lectures = new int[n];
		for(int i=0; i<n; i++){
			st = new StringTokenizer(br.readLine(), " ");
			int p = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int[] nums = new int[p];
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<p; j++) {
				nums[j] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(nums);
			if(l>p) {
				lectures[i] = 1;
			} else {
				lectures[i] = nums[p-l];
			}
		}
		Arrays.sort(lectures);
		int lectureCnt = 0;
		int mileCnt = 0;
		for(int mile: lectures) {
			if(mileCnt + mile > m) {
				break;
			}
			mileCnt += mile;
			lectureCnt++;
		}
		System.out.println(lectureCnt);
	}
}
