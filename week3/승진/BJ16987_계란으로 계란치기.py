def backtrack(cur, cnt):
    if cur == N:
        global max_cnt
        if cnt > max_cnt: max_cnt = cnt
        return
    
    if S[cur] <= 0 or cnt == N - 1: 
        backtrack(cur + 1, cnt)
        return

    for i in range(N):
        if i == cur or S[i] <= 0:
            continue
        # update s, w
        S[cur] -= W[i]
        S[i] -= W[cur]
        if S[cur] <= 0: cnt += 1
        if S[i] <= 0: cnt += 1

        backtrack(cur + 1, cnt)
        # restore s, w
        if S[cur] <= 0: cnt -= 1
        if S[i] <= 0: cnt -= 1
        S[cur] += W[i]
        S[i] += W[cur]
        

        
N = int(input())
S = []
W = []

max_cnt = 0

for _ in range(N):
    s, w = map(int, input().split())
    S.append(s)
    W.append(w)

backtrack(0, 0)
print(max_cnt)