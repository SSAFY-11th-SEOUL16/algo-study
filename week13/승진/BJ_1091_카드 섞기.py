import sys




input = sys.stdin.readline

N = int(input().rstrip())

S = list(map(int, input().split()))
P = list(map(int, input().split()))

answer = []

for _ in range(N//3):
    answer.extend([0,1,2])


tmp = S[:]

def mix_card(arr):
    t = arr[:]

    for idx, i in enumerate(P):
        t[i] = arr[idx]
    return t

cnt = 0

while True:
    if answer == tmp:
       break

    tmp = mix_card(tmp)
    cnt += 1

    if S == tmp:
        cnt = -1
        break 
    
print(cnt)