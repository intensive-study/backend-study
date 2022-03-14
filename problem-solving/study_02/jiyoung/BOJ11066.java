package study_02.jiyoung;

import java.util.Arrays;

public class BOJ11066 {

    public static void main(String[] args) {
        solution(new int[] {1, 21, 3, 4, 5});
//        solution(new int[] {40, 30, 30, 50});
//        solution(new int[] {1, 21, 3, 4, 5, 35, 5, 4, 3, 5});
//        solution(new int[] {1, 21, 3, 4, 5, 35, 5, 4, 3, 5, 98, 21, 14, 17, 32});
    }

    public static int solution(int[] chapter) {
        int answer = 0;
        int[][] memoization = new int[chapter.length+1][chapter.length+1];

        int[] totalSum = new int[chapter.length+1];

        for (int idx = 1; idx <= chapter.length; idx++) {
            totalSum[idx] = totalSum[idx-1] + chapter[idx-1];
            Arrays.fill(memoization[idx], Integer.MAX_VALUE);
            memoization[idx][idx] = chapter[idx-1];
        }


        for (int range = 2; range <= chapter.length; range++) {
            for (int midPoint = range; midPoint > 1; midPoint--) {

                System.out.printf("1 - %d [1-%d-%d]\n", range, midPoint, range);
                for (int k = range; k > midPoint; k--) {
                    memoization[midPoint][range] = Math.min(memoization[midPoint][range], memoization[midPoint][k-1] + memoization[k][range] + totalSum[k] - totalSum[midPoint-1]);
                    System.out.printf("- %d - %d [%d-%d-%d] = %d + %d = %d\n", midPoint, range, midPoint, k, range, memoization[midPoint][k-1], memoization[k][range], memoization[midPoint][range]);
                }

                memoization[1][range] = Math.min(memoization[1][range], memoization[1][midPoint-1] + memoization[midPoint][range]);
                System.out.printf("1 - %d [1-%d-%d] = %d\n", range, midPoint, range, memoization[1][range]);
            }
        }
        System.out.println("memoization = " + Arrays.toString(memoization[1]));

        return answer;
    }

}
