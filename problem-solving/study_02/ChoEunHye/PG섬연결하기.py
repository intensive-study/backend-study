from cmath import cos


def solution(n, costs):
    
    def find(x):
        if x != root[x]:
            root[x] = find(root[x])
            return root[x]
        else:
            return x
        
    def union(x, y):   
        if x < y: 
            root[y] = x 
        else: 
            root[x] = y
        
    answer = 0
    root = [i for i in range(n)] 
    costs.sort(key = lambda x : x[2])
    
    for a, b, cost in costs:
        
        rootA = find(a)
        rootB = find(b)
        
        if rootA != rootB:
            answer += cost
            union(rootA, rootB) 
            
    
    return answer

n = 4
costs = [[0,1,1],[0,2,2],[1,2,5],[1,3,1],[2,3,8]]
print(solution(n, costs))