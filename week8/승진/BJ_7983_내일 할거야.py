import sys
input=sys.stdin.readline

n=int(input())
arr=[]
for i in range(n):
    d,t=map(int,input().split())
    arr.append([d,t])
arr.sort(key=lambda arr:arr[1],reverse=True)

time=arr[0][1]

for i in range(n):
    s=arr[i][0]
    e=arr[i][1]
    
    if time>=e:
        time=e-s
    else:
        time=time-s
print(time)