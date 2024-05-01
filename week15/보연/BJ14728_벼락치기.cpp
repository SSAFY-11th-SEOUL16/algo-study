#include <iostream>
#include <cstring>
#include <algorithm>

using namespace std;

struct Lecture {
    int t, s; //time, score
};

const int SIZE = 101;
int N, T;
Lecture lec[SIZE];
int dp[SIZE][10001];

bool cmp(Lecture l1, Lecture l2) {
    return l1.t < l2.t;
}

void input() {
    cin >> N >> T;
    
    for(int i=0; i<N; i++) {
        int k, s;
        cin >> k >> s;
        
        lec[i] = {k, s};
    }
}

void solve() {
    memset(dp, 0, sizeof(dp));
    sort(lec, lec+N, cmp);
    
    for(int i=lec[0].t; i<=T; i++ ) {
        dp[0][i] = lec[0].s;
    }
    
    for(int i=1; i<N; i++) {
        for(int j=1; j<=T; j++) {
            if(j >= lec[i].t) {
                dp[i][j] = max(dp[i-1][j], dp[i-1][j-lec[i].t] + lec[i].s);
            } else
                dp[i][j] = dp[i-1][j];
        }
    }
    
    cout << dp[N-1][T];
}

int main() {
    input();
    solve();
    return 0;
}
