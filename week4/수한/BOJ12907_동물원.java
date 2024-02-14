package algorithm.baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12907_동물원 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(br.readLine());

        long cat, rabbit;
        cat = rabbit = 0;
        
        /*
            각 동물들에게 자신보다 키가 큰 같은 종류의 동물들이 몇마리인지 물어봄
            
            각 종류마다 대답을 정렬하면 0부터 연속적이어야함 
            
            고양이와 토끼 각각 비트 마스킹
            
            고양이 먼저, 만약 이미 마스킹 되었다면 그다음 토끼 마스킹
            
            만약 고양이, 토끼 둘 다 마스킹 되어있으면 불가능한 경우
            
            다 마스킹 후 고양이, 토끼와 checkNum을 비교
        */
        
        long maxCatBit = 0;
        long maxRabbitBit = 0;
        
        boolean isValid = true; 
        
        for (int i = 0; i < N; i++) {
        	int n = Integer.parseInt(tokenizer.nextToken());
        	long bitN = 1L << n;
        	if((cat & bitN) == bitN) {
            	// 이미 cat에 마스킹 된 경우
        		if((rabbit & bitN) == bitN) {
                	// 이미 rabbit에 마스킹 된 경우
        			isValid = false;
        			break;
        		}else {
        			rabbit |= bitN;
        			maxRabbitBit = Math.max(maxRabbitBit, bitN);
        		}
        	}else {
        		cat |= bitN;
        		maxCatBit = Math.max(maxCatBit, bitN);
        	}
        }
        
        long result = 0;
        
        if(isValid && cat == (maxCatBit << 1)-1 && (maxRabbitBit == 0 || rabbit == (maxRabbitBit << 1)-1)) {
        	if(maxRabbitBit == 0) {
        		result = 2;
        	}else {
            	result = maxRabbitBit << 1;
            	if(maxCatBit != maxRabbitBit) {
            		result <<= 1;
            	}
        	}
        }

        System.out.println(result);
    }
}