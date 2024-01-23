#include <iostream>
#include <cstring>
#include <vector>
#include <algorithm>

using namespace std;

const int max_size = 101;
int ans;
int n, m, p, l;

struct Subject {
	int p;
	int l;
	int mileage[max_size];
};

Subject subject[max_size];
vector<int> arr;

void simulation() {
	//과목마다 최소로 넣어야하는 마일리지
	for (int i = 0; i < n; i++) {
		if (subject[i].p < subject[i].l) {
			arr.push_back(1);
			continue;
		}
		if (subject[i].p == subject[i].l) {
			arr.push_back(subject[i].mileage[0]);
			continue;
		}
		arr.push_back(subject[i].mileage[subject[i].p - subject[i].l]);
	}

	sort(arr.begin(), arr.end());

	for (int i = 0; i < arr.size(); i++) {
		if (m < arr[i]) break;
		m -= arr[i];
		ans++;

	}

}

int main() {
	//입력 및 초기화
	cin >> n >> m;
	memset(subject, 0, sizeof(subject));
	arr.clear();
	ans = 0;

	for (int i = 0; i < n; i++) {
		cin >> p >> l;
		subject[i].p = p;
		subject[i].l = l;
		for (int j = 0; j < p; j++) {
			cin >> subject[i].mileage[j];
		}
		sort(subject[i].mileage, subject[i].mileage + subject[i].p);

	}

	//연산
	simulation();

	//출력
	cout << ans;

	return 0;
}

//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Scanner;
//
//class Subject {
//	int p;
//	int l;
//	ArrayList<Integer> mileage = new ArrayList<>();
//}
//
//public class Main {
//	static final int max_size = 101;
//	static int ans;
//	static int n, m, p, l;
//	static ArrayList<Integer> arr = new ArrayList<>();
//	static Subject[] subject = new Subject[max_size];
//
//	static void simulation() {
//		for (int i = 0; i < n; i++) {
//			if (subject[i].p < subject[i].l) {
//				arr.add(1);
//				continue;
//			}
//			if (subject[i].p == subject[i].l) {
//				arr.add(subject[i].mileage.get(0));
//				continue;
//			}
//			arr.add(subject[i].mileage.get(subject[i].p - subject[i].l));
//		}
//
//		Collections.sort(arr);
//
//		for (int i = 0; i < arr.size(); i++) {
//			if (m < arr.get(i)) break;
//			m -= arr.get(i);
//			ans++;
//		}
//	}
//
//	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//
//		n = scanner.nextInt();
//		m = scanner.nextInt();
//
//		for (int i = 0; i < max_size; i++) {
//			subject[i] = new Subject();
//		}
//
//		arr.clear();
//		ans = 0;
//
//		for (int i = 0; i < n; i++) {
//			p = scanner.nextInt();
//			l = scanner.nextInt();
//			subject[i].p = p;
//			subject[i].l = l;
//
//			for (int j = 0; j < p; j++) {
//				subject[i].mileage.add(scanner.nextInt());
//			}
//
//			Collections.sort(subject[i].mileage);
//		}
//
//		simulation();
//
//		System.out.println(ans);
//	}
//}
