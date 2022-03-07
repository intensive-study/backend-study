import java.util.*;
/*
 * <<0/1 Knapsack>>
 * 한 물건의 무게와 가치를 입력 받을 때마다 0 - k 무게에서 가능한 최고 value를 배열에 저장하는 DP 알고리즘을 활용해 풀이
 * */
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        //i - 1 의 경우에 index out of range 에러를 피하기위해 행을 n + 1의 크기로 선언
        //무게 w - weight를 인덱스로 쓰기 위해 k + 1의 크기로 열을 선언
        int[][] dp = new int[n + 1][k + 1];
        int weight, value;
        for (int i = 1; i <= n; i++) {
            weight = sc.nextInt();
            value = sc.nextInt();

            //현재 입력된 물건까지 무게가 1일 때 부터 제한 무게 k 까지 가능한 가장 높은 가치를 가지도록 업데이트
            for (int w = 1; w <= k; w++) {
                //dp[i - 1][w]는 이전 입력까지 무게의 최대 value,
                //현재 입력받은 물건의 무게 weight보다 작은 무게에는 영향을 끼지지 않음
                if (w < weight) {
                    dp[i][w] = dp[i - 1][w];
                }
                //현재 입력된 물건의 무게가 포함된 무게 x에서 최댓값과, 이전 물건 입력까지의 무게 x에서의 최대값 비교
                else {
                    dp[i][w] = Math.max(dp[i - 1][w - weight] + value, dp[i - 1][w]);
                }
            }
        }
        System.out.println(dp[n][k]);
    }
}