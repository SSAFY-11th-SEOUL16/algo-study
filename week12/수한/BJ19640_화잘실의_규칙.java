package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_19640_화잘실의_규칙 {

    static class Person implements Comparable<Person>{
        int N; // 줄 순 순서
        int M; // 줄 번호
        int D; // 근무 일수
        int H; // 화잘실 급한 정도
        public Person(int N, int M, int D, int H){
            this.N = N;
            this.M = M;
            this.D = D;
            this.H = H;
        }

        @Override
        public int compareTo(Person o) {
            if(this.D == o.D){
                if(this.H == o.H) return this.M - o.M;
                return o.H - this.H;
            }
            return o.D - this.D;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens;

        tokens = new StringTokenizer(br.readLine());
        int N, M, K;
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        // M개의 줄에 선 사람 리스트
        Queue<Person>[] peopleList = new ArrayDeque[M];
        // 선두 사람 리스트
        PriorityQueue<Person> firstPeople = new PriorityQueue<>();

        for (int i = 0; i < M; i++) {
            peopleList[i] = new ArrayDeque<>();
        }

        boolean isFirst = true;
        int col = 0;
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            int D = Integer.parseInt(tokens.nextToken());
            int H = Integer.parseInt(tokens.nextToken());
            Person person = new Person(i,col,D,H);
            if(isFirst) firstPeople.add(person);
            else peopleList[col].add(person);
            if(++col == M){
                col = 0;
                isFirst = false;
            }
        }

        int cnt = 0;
        while(!firstPeople.isEmpty()){
            Person person = firstPeople.poll();
            if(person.N == K){
                System.out.println(cnt);
                break;
            }

            if(!peopleList[person.M].isEmpty()){
                firstPeople.offer(peopleList[person.M].poll());
            }

            cnt++;
        }
    }
}