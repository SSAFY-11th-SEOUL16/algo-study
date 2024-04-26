import sys

input = sys.stdin.readline

N, D = map(int, input().split())

friends = dict()

for _ in range(N):
    P, V = map(int, input().split())
    
    if P in friends:
        friends[P] += V
    else:
        friends[P] = V

friends_arr = []

for K, V in friends.items():
    friends_arr.append((K,V))

friends_arr.sort()

left = 0
right = 0

max_V = 0

dp = [friends_arr[0][1]]

for i in range(1, len(friends_arr)):
    dp.append(dp[i-1]+friends_arr[i][1])


while right < len(dp):

    if friends_arr[right][0] - friends_arr[left][0] < D:
        max_V = max(max_V, dp[right] - dp[left] + friends_arr[left][1])
        right += 1
    else:
        left += 1

print(max_V)