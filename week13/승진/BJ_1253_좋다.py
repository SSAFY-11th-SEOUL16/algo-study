import sys
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))
arr.sort()

cnt = 0
for i in range(n):
    goal = arr[i]
    start = 0
    end = len(arr)-1
    while start < end:
        if arr[start] + arr[end] == goal:
            if start == i:
                start += 1
            elif end == i:
                end -= 1
            else:
                cnt += 1
                break
        elif arr[start] + arr[end] > goal:
            end -= 1
        elif arr[start] + arr[end] < goal:
            start += 1
            
print(cnt)