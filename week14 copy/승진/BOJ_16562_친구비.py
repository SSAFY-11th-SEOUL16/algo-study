import sys

sys.setrecursionlimit(10 ** 9)

input = sys.stdin.readline

N, M, K = map(int, input().split())

friends_money = list(map(int, input().split()))

friends = [-1 for _ in range(N)]


def find(x):

    if friends[x] >= 0:
        nx = find(friends[x])
        friends[x] = nx
        return nx
    else:
        return x    


def union(a,b):
    na = find(a)
    nb = find(b)

    if na == nb:
        return

    if friends_money[na] < friends_money[nb]:
       friends[na] += friends[nb]
       friends[nb] = na
    else:
        friends[nb] += friends[na]
        friends[na] = nb

for _ in range(M):
    a, b = map(int, input().split())
    union(a-1, b-1)

money = 0

for idx, friend in enumerate(friends):
    if friend < 0:
        money += friends_money[idx]

if K < money:
    print("Oh no")
else:
    print(money)