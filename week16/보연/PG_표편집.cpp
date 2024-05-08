#include <string>
#include <vector>
#include <stack>
#include <iostream>

using namespace std;

string solution(int n, int k, vector<string> cmd) {
    string answer = "";
    vector<bool> is_delete;
    int total = n;
    stack<int> recent;
    for(int i=0; i<n; i++) {
        is_delete.push_back(false);
    }
    
    for(int i=0; i<cmd.size(); i++) {
        char dir = cmd[i][0];
        int cnt = 0;
        
        //선택 칸 이동
        if(dir == 'D') {
            cnt = cmd[i][2] - '0';
            
            while(cnt > 0) {
                k++;
                if(is_delete[k]) {
                    continue;
                }
                cnt--;
            }
        }
        if (dir == 'U') {
            cnt = cmd[i][2] - '0';
            while(cnt > 0) {
                k--;
                if(is_delete[k]) {
                    continue;
                }
                cnt--;
            }
        }
        if(dir == 'C') {
            is_delete[k] = true;
            recent.push(k);
            if(k == total - 1) {
                //마지막 위치였다면
                k--;
                while(is_delete[k]) {
                    k--;
                }
                
            } else {
                k++;
                while(is_delete[k]) {
                    k++;
                }
            }
        }
        
        if(dir == 'Z') {
            if(recent.size() == 0) continue;
            is_delete[recent.top()] = false;
            recent.pop();
        }
        
    //     cout << dir << " " << k << "-> ";
    //     for(int i=0; i<n; i++) {
    //     if(is_delete[i]) {
    //         cout << 'X';
    //     } else {
    //         cout << 'O';
    //     }
    // }
    //     cout << '\n';
    }
    
    for(int i=0; i<n; i++) {
        if(is_delete[i]) {
            answer += 'X';
        } else {
            answer += 'O';
        }
    }
    
    return answer;
}