from itertools import combinations 
import sys
input = sys.stdin.readline

N, K = map(int, input().split()) #단어갯수 N, 영단어 K
# a:0, c:2, i:8, n:13, t:19 
contain = [0, 2, 8, 13, 19]
words = [input() for _ in range(N)]
all = set() #combination 할 목록
ans = 0

#string to bitmask
for i in range(N):
    word = words[i]
    words[i] = 0
    for c in word[4:-4]:
        bitPos = ord(c)-ord('a')
        all.add(bitPos)
        words[i] |= 1 << bitPos
    
if K > len(all.difference(contain)) + 5:
    #필요한 문자 이상 갯수를 고르는 경우, 모든 단어 가능
    ans = N
        
if K >= 5: #필수 문자를 포함하도록
    containBit = 0 #combi에서 포함하는 필수 문자
    for n in contain:
        containBit |= 1 << n
    
    for combi in combinations(all.difference(contain), K - 5):
        cnt = 0 #해당 조합으로 만들수 있는 단어
        compare = containBit
        for i in combi:
            compare |= 1 << i
            
        for word in words:
            if word & compare == word:
                cnt += 1
        
        ans = max(ans, cnt)
    
print(ans)