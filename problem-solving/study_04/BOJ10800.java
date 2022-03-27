package study_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ10800 {
    static final int INDEX = 0;
    static final int COLOR = 1;
    static final int SIZE = 2;
    static int size;
    //전체 누적액
    static int prefixSum = 0;

    static int[][] balls;
    static int[] prefixSumByColor;
    // 전체 누적액에서 같은 색 공의 누적액 제거
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        size = Integer.parseInt(br.readLine());
        // 3: idx, color, size
        balls = new int[size][3];
        answer = new int[size];
        prefixSumByColor = new int[size+1];

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            balls[i] = new int[] {i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        Arrays.sort(balls, Comparator.comparingInt((int[] v) -> v[SIZE]));

        for (int i = 0, j = 0; i < size; i++) {
            /**
             * 전체 누적합에서 본인과 동일한 컬러를 가진 볼들을 제거 + 직전까지의 합 중 본인과 동일한 크기를 가진 다른 컬러의 공들도 제거가 되어야 함.
             * 하지만, 본인과 동일한 컬러를 가진 공들 중, 본인들과 같은 크기를 가진 볼들도 있기 마련임으로 이를 관리하는 것이 사실 어려움
             * 그래서 본인보다 작은 값들만 누적합에 포함시키고, 보인과 동일한 값은 이후에 포함되도록 하는 것이 핵심.
             * 즉, 본인과 동일한 값은 애초에 포함시키지 않는다.
             *
             * 자, 그런데 본인까지의 모든 누적합을 계산하기 위해서 앞선 누적된 내용을 모두 조사해야하는데 그렇다면 최악의 경우, N^2 의 시간 복잡도가 걸리지 않느냐 하면
             * 이는 j 라는 변수를 하나 더 두어 이전에 계산한 값 이후부터 조건에 맞는다면 값을 누적시키면 되기때문에 n의 시간 복잡도를 가질 수 있다.
             **/

            while (balls[j][SIZE] < balls[i][SIZE]) {
                prefixSum += balls[j][SIZE];
                prefixSumByColor[balls[j][COLOR]] += balls[j][SIZE];
                j++;
            }

            answer[balls[i][INDEX]] = prefixSum - prefixSumByColor[balls[i][COLOR]];

        }

        for (int ans : answer) {
            System.out.println(ans);
        }
    }


}
