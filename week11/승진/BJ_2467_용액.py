import sys

input = sys.stdin.readline

N = int(input().rstrip())

arr = list(map(int, input().rstrip().split()))

l = 0
r = len(arr) - 1

result_num = float('inf')
result_l = 0
result_r = len(arr) - 1

while l < r:
    tmp = arr[l] + arr[r]
    if tmp == 0:
        result_l = l
        result_r = r
        break
    else:
        if abs(result_num) >= abs(tmp):
            result_num = tmp
            result_l = l
            result_r = r
    
    if tmp > 0 :
        r -= 1
    else:
        l += 1
print(arr[result_l],arr[result_r])