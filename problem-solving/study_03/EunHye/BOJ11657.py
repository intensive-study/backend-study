# 모든 간선이 양수가 아닌 경우 벨만-포드 알고리즘 사용
import sys, collections

input = sys.stdin.readline
N, M = map(int, input().split())
dist = [1e9 for _ in range(N+1)] #1에서 idx로 가는 최단 거리
edges =  [list(map(int, input().split())) for _ in range(M)] #[시작도시, 도착도시, 버스]
print()
def bellmanFord(start):
    dist[start] = 0
    for i in range(N):
        for j in range(M):
            now, next, cost = edges[j]
            print(now,next,cost)
            
            # 현재 노드를 경유하며, 다음 노드로 가는 거리가 짧은 경우 갱신
            if dist[now] != 1e9 and dist[next] > dist[now] +  cost:
                dist[next] = dist[now] + cost
                
                if i == N -1: #negative 순환으로 무한히 반복되는 것을 체크
                    return True
    #negative 순환에 걸리지 않음
    return False
            
            
chkCycle = bellmanFord(1)

if chkCycle: # 음수 순환이 존재하는 경우
    print("-1")
    
else:
    for i in range(2, N + 1):
        if dist[i] != 1e9: #최소 거리로 갱신된 경우
            print(dist[i])
        else: #도달할 수 없는 노드인 경우
            print("-1")