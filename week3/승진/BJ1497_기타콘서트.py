import sys
from itertools import combinations;

input = sys.stdin.readline

N, M = map(int, input().split())

gi = []

for _ in range(N):
    temp = input().rstrip().split(" ")
    nums = ""
    for t in temp[1]:
        if t == "Y":
            nums += "1"
        else:
            nums += "0"
    temp[1] = nums
    gi.append(temp)

max_cnt = 0
max_idx = 0

for i in range(1, N + 1):
    combis = list(combinations(gi,i))
    for combi in combis:
        check_num = bin(0)
        for c in combi:
            check_num = bin(int(check_num,2) | int(c[1],2))
        if check_num == bin((2 ** M) - 1):
            print(i)
            exit(0)
            
        cnt = str(check_num).count("1")
        if max_cnt < cnt:
            max_cnt = cnt
            max_idx = i
            
if max_cnt == 0:
    print(-1)
else:
    print(max_idx)