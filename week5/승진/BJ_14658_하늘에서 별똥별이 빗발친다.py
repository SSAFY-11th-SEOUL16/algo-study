import sys
input = sys.stdin.readline

N, M, L, K = map(int, input().split())
res = 0
star = []

for i in range(K):
    x, y = map(int, input().split())
    star.append([x, y])

for i in range(K):
    for j in range(K):
        cnt = 0
        for k in range(K):
            if star[i][0] <= star[k][0] and star[k][0] <= star[i][0]+L and star[j][1] <= star[k][1] and star[k][1] <= star[j][1]+L:
                cnt += 1
        res = max(res, cnt)
ans = K-res
print(ans)