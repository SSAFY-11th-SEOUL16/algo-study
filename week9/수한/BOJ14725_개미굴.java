package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_14725_개미굴{
	
	static class Cave{
		Map<String,Cave> foodMap;

		public Cave() {
			this.foodMap = new HashMap<>();
		}
	}

	static StringBuilder sb;
	static String[] prefixes;
	
	public static void print(Cave cave, int iter) {
		String prefix = prefixes[iter];

        List<String> keySet = new ArrayList<>(cave.foodMap.keySet());

        // 키 값으로 오름차순 정렬
        Collections.sort(keySet);
        
		for(String name : keySet) {
			sb.append(prefix).append(name).append("\n");
			print(cave.foodMap.get(name),iter+1);
		}
	}

	public static void initPrefix() {
		prefixes = new String[16];	
		String prefix = "";
		for (int iter = 0; iter < 16; iter++) {
			prefixes[iter] = prefix;
			prefix+="--";
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens;
		
		int N = Integer.parseInt(br.readLine());

		Cave entry = new Cave();
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			
			int K = Integer.parseInt(tokens.nextToken());
			
			Cave cave = entry;
			for (int j = 0; j < K; j++) {
				String foodName = tokens.nextToken();
				
				if(!cave.foodMap.containsKey(foodName)) {
					cave.foodMap.put(foodName,new Cave());
				}
				cave = cave.foodMap.get(foodName);
			}
		}
		
		sb = new StringBuilder();
		
		initPrefix();
		print(entry,0);
		
		System.out.println(sb);
	}
}
