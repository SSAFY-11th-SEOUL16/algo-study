import sys
input=sys.stdin.readline

dx=[-1,1,0,0]
dy=[0,0,-1,1]
N,M,K=map(int,input().split())

graph=[ list(input().rstrip()) for i in range(N) ] 

answer=0


def DFS(x,y,count):
    global answer

    if count==K and x==0 and y==M-1:
        answer+=1
    for i in range(4):
        nx=x+dx[i] ; ny=y+dy[i]

        if 0<=nx<N and 0<=ny<M and graph[nx][ny]=='.':
            graph[nx][ny]='T'
            DFS(nx,ny,count+1) 
            graph[nx][ny]='.' #이미왔던 지점은 막아주는대신에 , 끝까지 탐색한후에는 원래되로 되돌려놓는다. 

graph[N-1][0]='T'
DFS(N-1,0,1)
print(answer)