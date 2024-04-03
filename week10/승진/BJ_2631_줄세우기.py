import sys
import bisect


input = sys.stdin.readline

x = int(input().rstrip())
arr = []

for _ in range(x):
    arr.append(int(input().rstrip()))

dp = [arr[0]]

for i in range(x):
    if arr[i] > dp[-1]:
        dp.append(arr[i])
    else:
        idx = bisect.bisect_left(dp, arr[i])
        dp[idx] = arr[i]

print(x-len(dp))