import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
class SW2382_미생물격리 {
    private static class Cell {
        boolean poison;
        ArrayList<Microbe> mics;
         
        Cell(boolean poison) {
            this.poison = poison;
            mics = new ArrayList<>();
        }
    }
     
    private static class Microbe {
        int num, x, y;
        int[] way;
        Cell cell;
         
        Microbe(int x, int y, int num, int way, Cell cell) {
            this.x = x;
            this.y = y;
            this.num = num;
            switch (way) {
            case 1:
                this.way = new int[] {-1, 0};
                break;
            case 2:
                this.way = new int[] {1, 0};
                break;
            case 3:
                this.way = new int[] {0, -1};
                break;
            case 4:
                this.way = new int[] {0, 1};
                break;
            }
            this.cell = cell;
        }
         
        boolean move() {
            cell.mics.remove(this);
            x += way[0];
            y += way[1];
            cell = cells[x][y];
            if (cell.poison) {
                num /= 2;
                if (num == 0) {
                    microbes.remove(this);
                    return true;
                }
                way[0] *= -1;
                way[1] *= -1;
            }
            cell.mics.add(this);
            return false;
        }
         
        void merge() {
            int max;
             
            if (cell.mics.size() < 2) {
                return;
            }
            max = num;
            for (Microbe microbe : cell.mics) {
                if (microbe == this) {
                    continue;
                }
                if (microbe.num > max) {
                    max = microbe.num;
                    way = microbe.way;
                }
                num += microbe.num;
                microbes.remove(microbe);
            }
            cell.mics.clear();
            cell.mics.add(this);
        }
    }
     
    private static Cell[][] cells;
    private static ArrayList<Microbe> microbes;
     
    private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
        int n, m, k, i, j, x, y, ans;
        Microbe microbe;
         
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        cells = new Cell[n][n];
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                cells[i][j] = new Cell(i == 0 || j == 0 || i == n - 1 || j == n - 1);
            }
        }
        microbes = new ArrayList<>();
        for (i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            microbe = new Microbe(x, y, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), cells[x][y]);
            cells[x][y].mics.add(microbe);
            microbes.add(microbe);
        }
        for (i = 0; i < m; i++) {
            for (j = 0; j < microbes.size(); j++) {
                if (microbes.get(j).move()) {
                    j--;
                }
            }
            for (j = 0; j < microbes.size(); j++) {
                microbes.get(j).merge();
            }
        }
        ans = 0;
        for (Microbe mic : microbes) {
            ans += mic.num;
        }
        return ans;
    }
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
         
        int t, testCase;
         
        t = Integer.parseInt(br.readLine());
        for (testCase = 1; testCase <= t; testCase++) {
            sb.append('#').append(testCase).append(' ').append(solution(br, st)).append('\n');
        }
        System.out.println(sb);
    }
}
