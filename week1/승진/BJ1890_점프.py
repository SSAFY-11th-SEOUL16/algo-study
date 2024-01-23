import sys

input = sys.stdin.readline

N = int(input().rstrip())
maps = []
result = 0
for _ in range(N):
    maps.append(list(map(int,input().rstrip().split())))

dp = [[0 for _ in range(N)] for _ in range(N)]

dp[0][0] = 1 

for i in range(N):
    for j in range(N):

        if i == N - 1 and j == N - 1:
            print(dp[i][j])
            break

        if j + maps[i][j] < N:
            dp[i][j + maps[i][j]] += dp[i][j]

        if i + maps[i][j] < N:
            dp[i + maps[i][j]][j] += dp[i][j]

