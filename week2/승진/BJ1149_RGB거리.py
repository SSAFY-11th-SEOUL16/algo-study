import sys

house_count = int(sys.stdin.readline().rstrip())

dp = [[0 for _ in range(3)] for _ in range(house_count)]

dp[0] = list(map(int,sys.stdin.readline().rstrip().split()))

for i in range(1,house_count):
    R, G, B = map(int,sys.stdin.readline().rstrip().split())
    
    dp[i][0] = min(dp[i-1][1] + R,dp[i-1][2] + R)
    dp[i][1] = min(dp[i-1][0] + G,dp[i-1][2] + G)
    dp[i][2] = min(dp[i-1][0] + B,dp[i-1][1] + B)

print(min(dp[house_count-1][0],dp[house_count-1][1],dp[house_count-1][2]))