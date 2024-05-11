#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

const int SIZE = 100001;
int N;
vector<string> ans;
vector<int> num;
char ch[3] = {'+', '-', ' '};

void init() {
    ans.clear();
    num.clear();
    
}

void input() {
    cin >> N;
    for(int i=0; i<N; i++) {
        num.push_back(i+1);
    }
}

void solve(int cnt, string op) {
    if(cnt == N - 1) {
        string str = "1";
        //연산하기
        int sum = 1;
        int idx = 2;
        for(int i=0; i<N - 1; i++) {
            str += op[i] + to_string(idx);
            
            if(op[i] == ' ') {
                //숫자 이어붙이기
                if(i == 0 || op[i-1] == ' ')
                    sum = sum * 10 + idx;
                else {
                    if(op[i-1] == '+'){
                        sum -= (idx-1);
                        sum += (idx-1) * 10 + idx;
                    } else if(op[i-1] == '-') {
                        sum += (idx-1);
                        sum -= (idx-1) * 10 + idx;
                    }
                    
                }
            }
            
            if(op[i] == '+') {
                sum += idx;
            }
            
            if(op[i] == '-') {
                sum -= idx;
            }
            
            idx++;
        }
        
        if(sum == 0) {
            ans.push_back(str);
        }
        return;
    }
    //+, -, ' '를 넣는 중복 조합 구하기
    for(int i=0; i<3; i++) {
        solve(cnt+1, op + ch[i]);
    }
}

int main(){
    int T;
    cin >> T;
    for(int tc=1; tc<=T; tc++) {
        init();
        input();
        solve(0, "");
        sort(ans.begin(), ans.end());
        for(int i=0; i<ans.size(); i++) {
            cout << ans[i] <<'\n';
        }
        cout << '\n';
    }
    return 0;
}
