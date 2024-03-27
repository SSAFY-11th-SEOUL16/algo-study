n,m = map(int,input().split())
tmpArray = list(map(int,input().split()))

superior = [0 for _ in range(n + 1)]
ans = list(0 for _ in range(n+1))

for s in range(1,n):
    superior[s+1]=tmpArray[s]
        
for _ in range(m):
    i,w = map(int,input().split())
    ans[i] += w
    
for i in range(2,n+1):
    ans[i] += ans[superior[i]]    
    
ans.pop(0)           
print(*ans)