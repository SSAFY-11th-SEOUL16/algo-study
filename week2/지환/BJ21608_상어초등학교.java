package week2.지환;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BJ21608_상어초등학교 {
	static int n;
	static int[][] board;
	static int[][] friends;
	static int[] dx = new int[] {-1,1,0,0};
	static int[] dy = new int[] {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		board = new int[n+1][n+1];
		StringTokenizer st;
		friends = new int[n*n+1][4];
		for(int i=1; i<=n*n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int sn = Integer.parseInt(st.nextToken());
			for(int j=0; j<4; j++) {
				int fn = Integer.parseInt(st.nextToken());
				friends[sn][j] = fn;
			}
			int[] tmp = find(sn);
			board[tmp[0]][tmp[1]] = sn;
		}
		
		int score = 0;
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				score += calculate(board[i][j], i, j);
			}
		}
		System.out.println(score);
	}
	
	public static int calculate(int sn, int x, int y) {
		int friendNum = 0;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(checkRange(nx, ny)) {
				for(int j=0; j<4; j++) {
					if(board[nx][ny] == friends[sn][j]) {
						friendNum++;
					}
				}
			}
		}
		return (int) Math.pow(10, friendNum-1);
	}
	
	public static int[] find(int sn) {
		List<Seat> list = new ArrayList<>();
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(board[i][j]==0) {
					list.add(generatePossibleSeat(sn, i, j));
				}
			}
		}
		Collections.sort(list, Comparator.comparing(Seat::getFriendNum, Comparator.reverseOrder())
				.thenComparing(Seat::getEmptyNum, Comparator.reverseOrder())
				.thenComparing(Seat::getX)
				.thenComparing(Seat::getY));
		Seat seat = list.get(0);
		return new int[] {seat.x, seat.y};
	}
	
	public static Seat generatePossibleSeat(int sn, int x, int y) {
		int friendNum = 0;
		int emptyNum = 0;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(checkRange(nx, ny)) {
				if(board[nx][ny] == 0) {
					emptyNum++;
				}
				for(int j=0; j<4; j++) {
					if(board[nx][ny] == friends[sn][j]) {
						friendNum++;
					}
				}
			}
		}
		return new Seat(friendNum, emptyNum, x, y);
	}
	
	public static boolean checkRange(int x, int y) {
		return x>=1 && x<=n && y>=1 && y<=n;
	}
	
	static class Seat{
		int friendNum;
		int emptyNum;
		int x;
		int y;
		
		Seat(int friendNum, int emptyNum, int x, int y) {
			this.friendNum = friendNum;
			this.emptyNum = emptyNum;
			this.x = x;
			this.y = y;
		}
		
		int getFriendNum() {
			return friendNum;
		}
		int getEmptyNum() {
			return emptyNum;
		}
		int getX() {
			return x;
		}
		int getY() {
			return y;
		}
	}
	
}
