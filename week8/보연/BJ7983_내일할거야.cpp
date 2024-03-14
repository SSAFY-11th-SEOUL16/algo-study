#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

typedef pair<int, int> ci;

int N;
vector<ci> report;

bool cmp(ci a, ci b) {
    //if(a.second == b.second) {
        return a.second > b.second;
    //}
    //return a.first > b.first;
}

void input() {
    cin >> N;
    for(int i=0; i<N; i++) {
        int D, T;
        cin >> D >> T;
        report.push_back({D, T});
    }
    sort(report.begin(), report.end(), cmp);
}

void printArr() {
    for(int i=0; i<N; i++) {
        cout << report[i].first <<" " << report[i].second << '\n';
    }
}

void solve() {
    
    int res = report[0].second;
    for(int i=0;i<report.size(); i++) {
        if(res > report[i].second) {
            res = report[i].second;
        } else { //res <= report[i].second -> 겹칠 때
            //tmp = report[i].first;
        }
        res -= report[i].first;
        //cout << i+1 << "번째 " <<res <<'\n';
    }
    cout << res;
}

int main()
{
    input();
    //printArr();
    solve();
    return 0;
}
