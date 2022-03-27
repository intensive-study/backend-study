package study_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ7579 {
    static int N;
    static int targetMemory;
    static int totalCost;
    static int[][] maxMemoryManager;
    static int[] memory;
    static int[] cost;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        targetMemory = Integer.parseInt(st.nextToken());

        memory = new int[N+1];
        cost = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        totalCost = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            totalCost += cost[i];
        }

        maxMemoryManager = new int[2][totalCost+1];
        System.out.println(solution());


    }
    public static int solution() {
        int minCost = Integer.MAX_VALUE;
        for (int app = 1; app <= N; app++) {
            for (int i = cost[app]; i <= totalCost; i++) {
                // 이 부분은 구현이 불가능한게 직전의 값의 누적으로 값을 더해주므로 하나의 앱이 여러번 제거되는 상황이 발생한다.
                maxMemoryManager[0][i] = Math.max(maxMemoryManager[0][i], maxMemoryManager[0][i-cost[app]] + memory[app]);
//                if(maxMemoryManager[0][i] >= targetMemory) minCost = Math.min(minCost, i);

            }

            for (int i = totalCost; i >= cost[app]; i--) {
                // 뒤부터 줄여가면서 카운트해 중복을 제거하면서 일차원 배열로 최적의 값을 도출해 낼 수 있다.
                maxMemoryManager[1][i] = Math.max(maxMemoryManager[1][i], maxMemoryManager[1][i-cost[app]] + memory[app]);
//                if(maxMemoryManager[1][i] >= targetMemory) minCost = Math.min(minCost, i);
            }

        }

        // [10, 10, 10, 40, 50, 50, 60, 80, 80, 85, 100, 100, 115, 115, 115, 135]
        /**
         *
            [0, 0, 0, 0, 0, 0, 0, 0]
            [0, 30, 30, 30, 30, 30, 30, 30]
            [10, 40, 40, 40, 40, 40, 40, 40]
            [10, 40, 60, 60, 60, 60, 60, 60]
            [10, 40, 60, 60, 75, 95, 95, 95]
            [10, 40, 60, 80, 100, 100, 115, 135]
         */
        return minCost;
    }
}
