import sys
from bisect import bisect_left

input = sys.stdin.readline


up_stones = []
down_stones = []

N, H = map(int,input().split())

for i in range(N):
    h = int(input().rstrip())
    if i % 2 == 0:
        down_stones.append(h)
    else:
        up_stones.append(h)


down_stones.sort()
up_stones.sort()
cnt = 1

min_val = float('inf')

for h in range(1,H+1):
    t, b = bisect_left(up_stones,(H+1)-h),bisect_left(down_stones,h)
    total = N - (t+b)
    if total < min_val:
        min_val = total
        cnt = 1
    elif total == min_val:
        cnt += 1

print(min_val,cnt)