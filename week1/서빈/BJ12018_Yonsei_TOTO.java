import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BJ12018_Yonsei_TOTO {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str);

		int n = Integer.parseInt(st.nextToken()); // 과목 수 N
		int m = Integer.parseInt(st.nextToken()); // 주어진 마일리지 M
		int[] mileage = new int[n]; // 각 과목을 듣기 위한 최소 마일리지의 배열

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			int p = Integer.parseInt(st.nextToken()); // 각 과목에 신청한 사람의 수 P
			int l = Integer.parseInt(st.nextToken()); // 수강인원 L

			int[] tmp_mileage = new int[p]; // 해당 과목에서 각 사람이 마일리지를 얼마나 넣었는지 담은 배열
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < p; j++) {
				tmp_mileage[j] = Integer.parseInt(st.nextToken());
			}

			if (p >= l) { // 만약 신청 인원이 수강 인원보다 많을 때
				Arrays.sort(tmp_mileage); // 각 사람의 마일리지를 정렬
				mileage[i] = tmp_mileage[p - l]; // P - L 번째 값을 할당함 (마일리지가 같다면 성준이에게 우선순위)
			} else { // 만약 신청 인원이 수강 인원보다 적을 때
				mileage[i] = 1; // 최소 마일리지인 1을 할당함
			}
		}
		System.out.println(countMaxSubject(mileage, m));
	}

	public static int countMaxSubject(int[] mileage, int m) {
		Arrays.sort(mileage);
		int sum = 0, count = 0;
		for (int i = 0; i < mileage.length; i++) {
			sum += mileage[i];
			if (sum > m) {
				break;
			}
			count++;
		}
		return count;
	}
}