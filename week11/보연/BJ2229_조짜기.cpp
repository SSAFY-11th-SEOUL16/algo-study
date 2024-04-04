#include <iostream>
#include <algorithm>
using namespace std;

const int SIZE = 1001;
int N;
int stud[SIZE];
int dp[SIZE];

int main() {
    cin >> N;
    for(int i=1; i<=N; i++) {
        cin >> stud[i];
    }
    
    //i의 합은 최소, stud[i]의 차는 최댓값이 되도록
    for(int i=2; i<=N; i++ ) {
        int MIN = stud[i];
        int MAX = stud[i];
        for(int j=i-1; j>=0; j--) {
            MIN = min(MIN, stud[j+1]);
            MAX = max(MAX, stud[j+1]);
            dp[i] = max(dp[i], dp[j] + MAX - MIN);
        } 
    }
    cout << dp[N];

    return 0;
}          
