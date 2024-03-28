#include <string>
#include <vector>
#include <algorithm>
#include <set>
#include <iostream>

using namespace std;

bool isSame(string ban, string user) {
    if(ban.length() != user.length()) {
        return false;
    }
    for(int i=0; i<ban.length(); i++) {
        for(int j=0; j<user.length(); j++) {
            if(ban[i] == '*') continue;
            if(ban[i] != user[i]) return false;
        }
    }
    return true;
}

int solution(vector<string> user_id, vector<string> banned_id) {
    int answer = 0;
    set<string> s;
    sort(user_id.begin(), user_id.end());
    do {
        vector<string> vec;

        for(int j=0; j<banned_id.size(); j++) {
            if(isSame(banned_id[j], user_id[j])) {
                vec.push_back(user_id[j]);
            }
        }
        string list = "";
        if(vec.size() == banned_id.size()) {
            sort(vec.begin(), vec.end());
            for(int i=0; i<vec.size(); i++) {
                list += vec[i];
            }
            s.insert(list);
        }
    } while(next_permutation(user_id.begin(), user_id.end()));
    answer = s.size();
    return answer;
}