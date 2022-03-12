import sys
from collections import deque

def bfs():
    dir = [(-1,0),(1,0),(0,-1),(0,1)]
    q = deque([(start[0], start[1])])
    visited[start[0]][start[1]] = 0
    
    while q:
        r, c = q.popleft()
        
        for d in dir:
            nr, nc = r+d[0], c+d[1]
            
            while True:
                if nr < 0 or nc < 0 or nr >= H or nc >= W:
                    #범위를 벗어난 경우
                    break
                if board[nr][nc] == '*':
                    #벽인 경우
                    break
                if visited[nr][nc] < visited[r][c] + 1:
                    # 이미 더 작은 거울로 접근가능할 경우
                    break
                
                q.append((nr, nc))
                visited[nr][nc] = visited[r][c] + 1
                
                #같은 방향으로 전진
                nr += d[0] 
                nc += d[1]



if __name__=='__main__':
      
    input = sys.stdin.readline
    W, H = map(int, input().split())
    #지도 크기가 최대 100 * 100
    visited = [[10000] * W for _ in range(H)] 
    board = [] 
    cPos = []
    
    for row in range(H):
        board.append(list(input()))
        
        for col in range(W):
            if board[row][col] == 'C':
                cPos.append((row,col))
    
    start, end = cPos
    
    bfs()
    
    print(visited[end[0]][end[1]] - 1)

