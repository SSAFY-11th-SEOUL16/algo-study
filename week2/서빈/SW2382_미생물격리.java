import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static class Virus {
        int num, i, j, cnt, dir;

        Virus(int num, int i, int j, int cnt, int dir) {
            this.num = num;
            this.i = i;
            this.j = j;
            this.cnt = cnt;
            this.dir = dir;
        }
    }

    static int di[] = new int[]{0, -1, 1, 0, 0};
    static int dj[] = new int[]{0, 0, 0, -1, 1};

    static int N, M, K;
    static List<Virus> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();
            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                list.add(new Virus(i * N + j, i, j, cnt, dir));
            }

            for (int time = 0; time < M; time++) {
                for (int idx = 0; idx < list.size(); idx++) {
                    Virus virus = list.get(idx);
                    virus.i = virus.i + di[virus.dir];
                    virus.j = virus.j + dj[virus.dir];
                    virus.num = (virus.i * N) + virus.j;

                    if (virus.i == 0 || virus.j == 0 || virus.i == N - 1 || virus.j == N - 1) {
                        virus.cnt /= 2;
                        virus.dir = changeDir(virus.dir);
                        if (virus.cnt == 0) {
                            list.remove(idx);
                            idx--;
                        }
                    }
                }

                for (int idx = 0; idx < list.size() - 1; idx++) {
                    Virus now = list.get(idx);
                    Virus next = list.get(idx + 1);

                    if (now.num == next.num) {
                        now.cnt += next.cnt;
                        list.remove(idx + 1);
                        idx--;
                    }
                }
            }

            int total = 0;
            for (int i = 0; i < list.size(); i++) {
                total += list.get(i).cnt;
            }
            System.out.println("#" + tc + " " + total);
        }
    }

    static int changeDir(int dir) {
        switch (dir) {
            case 1:
                return 2;
            case 2:
                return 1;
            case 3:
                return 4;
            case 4:
                return 3;
        }
        return -1;
    }
}