def dfs(id, computers):
    visited[id] = True
    for i in range(len(computers)):
        if computers[id][i] == 1 and not visited[i]:
            dfs(i, computers)

def solution(n, computers):
    answer = 0  
    global visited
    visited = [False] * n
    
    for i in range(n):
        if not visited[i]:
            answer += 1
            dfs(i, computers)            
    
    return answer