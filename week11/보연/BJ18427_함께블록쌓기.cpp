#include <iostream>
#include <vector>
using namespace std;

int N, M, H;
int R = 10007;
vector<int> block[51];
int dp[51][1001];

void input() {
    cin >> N >> M >> H;
    cin.ignore(1);
    for(int i=1; i<=N; i ++) {
        string str;
        getline(cin, str, '\n');
        for(int j=0; j<str.length(); j++) {
            if(str[j]==' '||j==0){
                block[i].push_back(stoi(&str[j]));
            }
        }
    }
}

void solve() {
    for(int i=0; i<=N; i++) {
        dp[i][0] = 1;
    }
    
    for(int i=1; i<=N; i++) {
        for(int j=1; j<=H; j++) {
            for(int k=0; k<block[i].size(); k++) {
                if(j >= block[i][k]) {
                    dp[i][j] += dp[i-1][j - block[i][k]];
                    dp[i][j] %= R;
                }
            }
            dp[i][j] += dp[i-1][j];
            dp[i][j] %= R;
        }
    }
    
    cout << dp[N][H];
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    input();
	solve();
	return 0;
}
