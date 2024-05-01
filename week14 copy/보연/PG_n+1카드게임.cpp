#include <string>
#include <vector>
#include <set>

using namespace std;

set<int> hand, deck;
int target_num;

bool is_possible(set<int> &s1, set<int> &s2) {
    for(int i : s1) {
        int target = target_num - i;
        
        if(s2.find(target) != s2.end()) {
            s1.erase(i);
            s2.erase(target);
            return true;
        }
    }
    return false;
}

int solution(int coin, vector<int> cards) {
    int answer = 0;
    int N = cards.size();
    target_num = N + 1;
    int idx = N /3;

    for(int i=0; i<idx; i++) {
        hand.insert(cards[i]);
    }
    int round = 1;
    while(idx < N) {
        for(int i=0; i<2; i++) {
            deck.insert(cards[idx++]);
        }
        if(hand.size() >=2 && is_possible(hand, hand)) {
            round++;
        } else if(hand.size() >= 1 && deck.size() >= 1 && coin >= 1 && is_possible(hand, deck)) {
            coin--;
            round++;
        } else if(deck.size() >= 2 && coin >= 2 && is_possible(deck, deck)) {
            coin-=2;
            round++;
        } else {
            break;
        }
    }
    answer = round;
    return answer;
}