tc = int(input())

for _ in range(tc):
    K = int(input())
    arr = list(map(int, input().split()))
    dp = [[0] * (K + 1) for _ in range(K + 1)]
    cSum = [0 for _ in range(K + 1)] #누적합
    for i in range(K):
        cSum[i + 1] = cSum[i] + arr[i]
        
    for len in range(1, K): #len 만큼 연속해 합친 파일
        for start in range(1, K-len+1): 
            end = start+len
            total = cSum[end] - cSum[start-1]
            dp[start][end]= min([dp[start][start+mid] + dp[start+mid+1][end] for mid in range(len)]) + total
        
    print(dp[1][K]) #1부터 끝까지 합쳤을 때 값
    
