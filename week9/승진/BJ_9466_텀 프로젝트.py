import sys

sys.setrecursionlimit(10**6)

def dfs(n):
    global count

    visited[n] = True
    cycle_list.append(n)

    if visited[arr[n]] == True:
        if arr[n] in cycle_list:
            count -= len(cycle_list[cycle_list.index(arr[n]):])
        return

    else:
        dfs(arr[n])

T = int(input())

for _ in range(T):
    N = int(input())

    arr = [0]
    arr.extend([int(x) for x in sys.stdin.readline().rstrip().split()])

    visited = [False] * (N + 1)
    count = N
    for i in range(1, N + 1):
        if not visited[i]:
            cycle_list = []
            dfs(i)

    print(count)
