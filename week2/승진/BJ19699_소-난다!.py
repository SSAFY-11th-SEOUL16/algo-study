import sys
from itertools import combinations
import math

def check_num(n):
    for i in range(2,int(math.sqrt(n))+1):
        if n % i == 0:
            return False
    return True

input = sys.stdin.readline

N, M = map(int,input().rstrip().split(" "))

cows = list(map(int,input().rstrip().split()))

cow_w_set = dict()


for cow in combinations(cows,M):
    cow_w_set[sum(cow)] = 1

result_arr = []

for cow in cow_w_set:
    if check_num(cow):
        result_arr.append(cow)
        
result_arr.sort()
if len(result_arr) >0:
    print(*result_arr)
else:
    print(-1)