import sys


input = sys.stdin.readline

N = int(input().rstrip())

jobs = [0 for _ in range(N+1)]

for i in range(1,N+1):
    tmp = list(map(int, input().rstrip().split()))
    time = tmp[0]
    before_cnt = tmp[1]

    max_time = time

    for j in range(before_cnt):
        max_time = max(max_time,jobs[tmp[j+2]]+time)
    jobs[i] = max_time
print(max(jobs))