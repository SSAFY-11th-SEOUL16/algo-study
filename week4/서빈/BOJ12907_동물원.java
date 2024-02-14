import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12907_동물원 {
    static int[] animal;
    static int[] animal_num = new int[41];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        animal = new int[N];
        for (int i = 0; i < N; i++) {
            animal[i] = Integer.parseInt(st.nextToken());
            animal_num[animal[i]]++;
            if (animal_num[animal[i]] > 2) {
                System.out.println(0);
                return;
            }
        }

        int combinations = 1;
        if (animal_num[0] == 0) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < 41; i++) {
            if (animal_num[i] == 0) {
                if (animal_num[i - 1] == 1)
                    combinations *= 2;
                break;
            }
            if (i > 0 && animal_num[i - 1] < animal_num[i]) {
                System.out.println(0);
                return;
            }
            if (animal_num[i] == 2)
                combinations *= 2;
        }
        System.out.println(combinations);
    }
}