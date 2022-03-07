from math import inf
from collections import deque, defaultdict

T = 10

for t in range(1, T + 1):
    graph = defaultdict(lambda: [])
    n, start = map(int, input().split())
    temp = list(map(int, input().split()))
    for i in range(0, n, 2):
        x, y = temp[i:i + 2]
        graph[x].append(y)

    visited = defaultdict(lambda: inf)
    deq = deque()
    deq.append([start, 0])
    answer = -1
    answer_cnt = -1

    while deq:
        node, cnt = deq.popleft()

        if visited[node] > cnt:
            visited[node] = cnt
            if answer_cnt <= cnt:
                if answer_cnt == cnt:
                    if answer < node:
                        answer = node
                else:
                    answer_cnt = cnt
                    answer = node
            for next_node in graph[node]:
                deq.append([next_node, cnt + 1])

    # for i in graph:
    #     print(i, graph[i])
    # for i in visited:
    #     print(i, visited[i])
    print(f'#{t} {answer}')