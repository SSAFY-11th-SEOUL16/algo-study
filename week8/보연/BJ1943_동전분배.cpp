#include <iostream>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

typedef pair<int, int> ci;

const int N_MAX = 101;
int N, total, ans;
ci coins[N_MAX];

bool cmp (ci c1, ci c2) {
    return c1.first < c2.first;
}

void input() {
    total = 0;
    cin >> N;
    for(int i=0; i<N; i++) {
        int val, cnt;
        cin >> val >> cnt;
        coins[i] = {val, cnt};
        total += (val * cnt);
    }
    
}

void printArr(int flag) {
    if(flag == 0){
    for(int i=0; i<N; i++) {
        cout << coins[i].first << " " << coins[i].second << '\n';
    }
    }
    if(flag == 1) {
    //     for(int i=0; i<total/2; i++) {
    //     cout << dp[i] << ' ';
    // }
    // cout << '\n';
    }
}

void dfs(int cnt, int curr) {
    if(curr > total/2) return;
    if(cnt == N) return;
    if(curr == total/2) {
        ans = 1;
        return;
    }
    
    for(int i=0; i<coins[i].second; i++) {
        dfs(cnt + 1, curr + (coins[i].first * i));
    }
}

void solve() {
    ans = 0;
    sort(coins, coins + N, cmp);
    //printArr(0);
    
    bool dp[50001] = {0,};
    dp[0] = true;
    
    for(int i=0; i<N; i++) {
        for(int j=total/2; j>=coins[i].first; j--) {
            if(dp[j - coins[i].first]) {
                for(int k=0; k<coins[i].second; k++) {
                    if(j + coins[i].first * k > total / 2) break;
                    dp[j + coins[i].first * k] = true;
                }
            }
        }
    }
    for(int i=0; i<total/2; i++) {
        co