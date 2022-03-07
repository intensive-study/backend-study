import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            int weight = sc.nextInt();
            int value = sc.nextInt();
            for (int w = 1; w <= k; w++) {
                if (w < weight) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    dp[i][w] = Math.max(dp[i - 1][w - weight] + value, dp[i - 1][w]);
                }
            }
        }
        System.out.println(dp[n][k]);
    }
}
