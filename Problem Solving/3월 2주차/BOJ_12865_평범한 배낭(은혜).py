n, k = map(int, input().split())
dp = [[0] * (k+1) for _ in range(n+1)] #(n+1) X (k+1) 배열 초기화

#물건 하나씩 받으며 체크
for i in range(1, n+1):
    w, v = map(int, input().split())
    for j in range(1, k+1):
        if j < w :
            # 해당 물품이 배낭 제한 무게보다 클 때 이전 행 그대로
            dp[i][j] = dp[i-1][j]
        else :
            # 배낭에 들어갈 무게라면, 이전 행의 값과 새로 물건을 넣었을 때 값을 비교
            dp[i][j] = max(dp[i-1][j], dp[i-1][j-w] + v)
