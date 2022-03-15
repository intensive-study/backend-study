import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {

            int K = Integer.parseInt(br.readLine());
            int[] files = new int[K];
            int[] sum = new int[K];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < K; j++) {
                files[j] = Integer.parseInt(st.nextToken());
            }

            //파일들의 합
            sum[0] = files[0];
            for (int j = 1; j < K; j++) {
                sum[j] = files[j] + sum[j - 1];
            }

            //합치기전 연속되는 두 파일들의 병합 비용 dp
            int[][] dp = new int[K][K];
            for (int i = 0; i < K - 1; i++) {
                dp[i][i + 1] = files[i] + files[i + 1];
            }

            for (int j = 2; j < K; j++) {
                for (int i = 0; i + j < K; i++) {
                    for (int k = i; k < i + j; k++) {
                        if (dp[i][i + j] == 0) { //dp가 비어있으면
                            dp[i][i + j] = dp[i][k] + dp[k + 1][i + j] + sum(sum, i, i + j);
                        } else {
                            dp[i][i + j] = Math.min(dp[i][i + j], dp[i][k] + dp[k + 1][i + j] + sum(sum, i, i + j));
                        }
                    }
                }
            }


            System.out.println(dp[0][K - 1]);

        }
    }

    private static int sum(int[] sum, int start, int end) {
        if (start == 0) {
            return sum[end];
        }
        return sum[end] - sum[start - 1];
    }
}
