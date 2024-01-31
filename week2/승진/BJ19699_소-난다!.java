import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();

        List<Integer> cows = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            cows.add(scanner.nextInt());
        }

        Map<Integer, Integer> cowWeightSet = new HashMap<>();

        for (List<Integer> cowCombination : combinations(cows, M)) {
            int sum = 0;
            for (int cow : cowCombination) {
                sum += cow;
            }
            cowWeightSet.put(sum, 1);
        }

        List<Integer> resultArr = new ArrayList<>();

        for (int cow : cowWeightSet.keySet()) {
            if (checkNum(cow)) {
                resultArr.add(cow);
            }
        }

        resultArr.sort(null);

        if (!resultArr.isEmpty()) {
            for (int cow : resultArr) {
                System.out.print(cow + " ");
            }
        } else {
            System.out.println(-1);
        }
    }

    private static List<List<Integer>> combinations(List<Integer> elements, int K) {
        List<List<Integer>> result = new ArrayList<>();
        int[] combination = new int[K];
        combine(elements, combination, 0, 0, result);
        return result;
    }

    private static void combine(List<Integer> elements, int[] combination, int start, int index, List<List<Integer>> result) {
        if (index == combination.length) {
            List<Integer> subset = new ArrayList<>();
            for (int i : combination) {
                subset.add(elements.get(i));
            }
            result.add(subset);
        } else {
            for (int i = start; i < elements.size(); i++) {
                combination[index] = i;
                combine(elements, combination, i + 1, index + 1, result);
            }
        }
    }

    private static boolean checkNum(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
