import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n, m, x, result = 0;
	static List<LinkedList<locTimeData>> data;   //파티장소로 출발할 경우(목적지, 소요시간)
	static List<LinkedList<locTimeData>> data2;  //파티장소에서 다시 마을로 돌아갈 경우(출발지, 소요시간)
	static int [] backData;   //파티장소에서 마을로 돌아갈 때 각 마을마다의 소요시간을 저장할 배열
	static int [] goData;     //파티장소로 이동할 때 각 마을마다의 소요시간을 저장할 배열
	static boolean [] check;  //방문 여부 확인
	static class locTimeData{
		locTimeData(int loc, int time){
			this.loc = loc;
			this.time = time;
		}
		int loc, time;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
		n = Integer.parseInt(str.nextToken());
		m = Integer.parseInt(str.nextToken());
		x = Integer.parseInt(str.nextToken());
		data = new ArrayList<LinkedList<locTimeData>>();
		data2 = new ArrayList<LinkedList<locTimeData>>();
		backData = new int [n+1];
		goData = new int [n+1];
		check = new boolean[n+1];
		check[x] = true;
		int tmpLoc=0, tmpResult = Integer.MAX_VALUE;
		int tmpLoc2=0, tmpResult2 = Integer.MAX_VALUE;
		
		for(int i = 0; i<=n; i++) {
			data.add(new LinkedList<locTimeData>());
			data2.add(new LinkedList<locTimeData>());
			backData[i] = Integer.MAX_VALUE;
			goData[i] = Integer.MAX_VALUE;
		}
		
		backData[x] = 0;
		goData[x] = 0;
		
		for(int i = 0; i < m; i++) {
			str = new StringTokenizer(br.readLine());
			int tmp = Integer.parseInt(str.nextToken());
			int tmp2 = Integer.parseInt(str.nextToken());
			int tmp3 = Integer.parseInt(str.nextToken());
			data.get(tmp).add(new locTimeData(tmp2, tmp3));
			data2.get(tmp2).add(new locTimeData(tmp, tmp3));
			if(tmp == x) {                         //파티장소가 출발지이면
				backData[tmp2] = tmp3;      //배열에 저장
				if(tmp3<tmpResult) {        //소요 시간이 파티장소가 출발지인 마을의 소요시간 중 가장 작은지 확인
					tmpLoc = tmp2;            //소요시간이 가장 적은 마을이면 해당 소요시간 및 도착지를 변수에 저장
					tmpResult = tmp3;
				}
			}
			if(tmp2 == x) {          //파티장소가 도착지 이면
				goData[tmp] = tmp3;    //배열에저장
				if(tmp3<tmpResult2) {  //소요시간이 파티장소가 도착지인 마을의 소요시간 중 가장 작은지 확인
					tmpLoc2 = tmp;            //소요시간이 가장 적은 마을이면 해당 소요시간 및 도착지를 변수에 저장
					tmpResult2 = tmp3;
				}
			}
		}

    //파티에서 마을로 돌아갈 때 소요시간
    //tmpLoc : 현재 데이터중 가장 작은 소요시간을 가진 마을
		int count = 1;
		while(count < n) {
			check[tmpLoc] = true;    //방문여부를 true로 변경
			for(int i = 0; i <data.get(tmpLoc).size(); i++) {
				locTimeData tmpData = data.get(tmpLoc).get(i); //해당 마을과 연결된 마을과 소요시간 정보를 얻어옴
				if(check[tmpData.loc]) {continue;}    //이미 방문한 마을인지 확인
				if(backData[tmpData.loc] > tmpData.time+tmpResult) {  //해당 마을에서 방문할 수 있는 도시에 대해 해당 마을 방문 후 방문 시 더 적은 소요시간이 걸리는지 확인
					backData[tmpData.loc] = tmpData.time+tmpResult;
				}
			}
			
			tmpResult = Integer.MAX_VALUE;
      //현재 세팅된 배열 내 방문하지 않은 마을 중 가장 적은 소요시간을 가진 마을 세팅
			for(int i = 1; i <= n; i++) {   
				if(check[i]) {continue;}
				if(tmpResult > backData[i]) {
					tmpResult = backData[i];
					tmpLoc = i;
				}
			}
			count ++;
		}

    //마을에서 파티장소로 갈 때 소요시간
		count = 1;
		check = new boolean[n+1];
		check[x] = true;
    //tmpLoc2 : 현재 데이터중 가장 작은 소요시간을 가진 마을
		while(count < n) {
			check[tmpLoc2] = true;   //방문여부를 true로 변경
			for(int i = 0; i <data2.get(tmpLoc2).size(); i++) { //해당 마을과 연결된 마을과 소요시간 정보를 얻어옴
				locTimeData tmpData = data2.get(tmpLoc2).get(i);
				if(check[tmpData.loc]) {continue;}  //이미 방문한 마을인지 확인
				if(goData[tmpData.loc] > tmpData.time+tmpResult2) { //해당 마을에서 방문할 수 있는 도시에 대해 파티 장소 도착 전 해당 마을 방문 후 방문 시 더 적은 소요시간이 걸리는지 확인
					goData[tmpData.loc] = tmpData.time+tmpResult2;
				}
			}

      //현재 세팅된 배열 내 방문하지 않은 마을 중 가장 적은 소요시간을 가진 마을 세팅
			tmpResult2 = Integer.MAX_VALUE;
			for(int i = 1; i <= n; i++) {
				if(check[i]) {continue;}
				if(tmpResult2 > goData[i]) {
					tmpResult2 = goData[i];
					tmpLoc2 = i;
				}
			}
			
			count ++;
		}
		
		//System.out.println("-----------------------");
		for(int i =1; i <=n; i++) {
			if(result < goData[i]+backData[i]) {
				result = goData[i]+backData[i];
			}
			//System.out.println(goData[i]+"/"+backData[i]);
			
		}
		System.out.println(result);
		
	}
}
