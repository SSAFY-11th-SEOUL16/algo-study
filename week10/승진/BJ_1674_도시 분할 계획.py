import sys

input = sys.stdin.readline

V, E = map(int, input().split())

gans = []

for _ in range(E):
    s, e, v = map(int,input().split())

    gans.append((s,e,v))

parents = [-1 for _ in range(V+1)]
distance=[]

def find(x):
    if parents[x] < 0:
        return x
    parents[x] = find(parents[x])
    return parents[x]

def union(a, b):
    # a = find(a)
    # b = find(b)
    if parents[a] >= parents[b]:
        parents[a] += parents[b]
        parents[b] = a
    else:
        parents[b] += parents[a]
        parents[a] = b

gans.sort(key=lambda x:-x[2])

while gans:
    s, e, v = gans.pop()
    s = find(s)
    e = find(e)
    if s == e:
        continue
    else:
        union(s,e)
        distance.append(v)
print(sum(distance[:-1]))
