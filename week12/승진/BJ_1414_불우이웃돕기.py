import sys
input=sys.stdin.readline

def Find(x):

    if x!=disjoint[x]:
        disjoint[x]=Find(disjoint[x])
    return disjoint[x]

def Union(a,b):

    a=Find(a)
    b=Find(b)

    if a>b:
        disjoint[a]=b
    else:
        disjoint[b]=a

N=int(input())
String=[ list(input().rstrip()) for _ in range(N) ]
disjoint=[ _ for _ in range(N+1) ]

edge=[] ; total=0

for i in range(N):
    for j in range(N):

        if String[i][j]==0:
            edge.append((0,i+1,j+1))
        else:
            if ord('a')<=ord(String[i][j])<=ord('z'):

                edge.append((ord(String[i][j])-ord('a')+1 , i+1 , j+1))
                total+=ord(String[i][j])-ord('a')+1

            elif ord('A')<=ord(String[i][j])<=ord('Z'):

                edge.append((ord(String[i][j])-ord('A')+27 , i+1,j+1))
                total+=ord(String[i][j])-ord('A')+27

edge.sort(key=lambda x:x[0])

for value,x,y in edge:

    if value==0:
        continue

    if Find(x)!=Find(y):
        Union(x,y)
        total-=value

check=set()
for i in range(1,N+1):
    if Find(i) not in check:
        check.add(Find(i))

if len(check)==1:
    print(total)
else:
    print(-1)