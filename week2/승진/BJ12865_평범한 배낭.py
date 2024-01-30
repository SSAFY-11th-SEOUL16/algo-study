import sys

input = sys.stdin.readline

N, K = map(int,input().rstrip().split(" "))

arr = [0 for _ in range(K+1)]

items_arr = []
for _ in range(N):
    k, v = map(int,input().rstrip().split(" "))    
    items_arr.append((k,v))
items_arr.sort(reverse=True)

for item in items_arr:
    for i in range(K, item[0]-1,-1):
        arr[i] = max(arr[i],arr[i-item[0]] + item[1])

print(arr[-1])