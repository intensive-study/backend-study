package study_03.jiyoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2632 {
    static int targetSize;
    static final int PIZZA_A = 0;
    static final int PIZZA_B = 1;
    static int[][] pizza;
    static int[][] cases;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        targetSize = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        pizza = new int[2][];
        cases = new int[2][];
        pizza[PIZZA_A] = new int[Integer.parseInt(st.nextToken())];
        pizza[PIZZA_B] = new int[Integer.parseInt(st.nextToken())];
        cases[PIZZA_A] = new int[targetSize+1];
        cases[PIZZA_B] = new int[targetSize+1];

        for (int aIdx = 0; aIdx < pizza[PIZZA_A].length; aIdx++) {
            pizza[PIZZA_A][aIdx] = Integer.parseInt(br.readLine());
        }

        for (int bIdx = 0; bIdx < pizza[PIZZA_B].length; bIdx++) {
            pizza[PIZZA_B][bIdx] = Integer.parseInt(br.readLine());
        }

        searchCase(PIZZA_A);
        searchCase(PIZZA_B);

        System.out.println(countCase());

    }

    /**
        public static void searchCaseUsingTwoPointer(int type) {
            int start = 0;
            int end = 0;
            int partialSum = pizza[type][start];

            while(start < pizza[type].length && end < pizza[type].length * 2 - 1) {
                if(partialSum <= targetSize) {
                    cases[type][partialSum]++;
                    partialSum += pizza[type][++end % pizza[type].length];
                } else {
                    partialSum -= pizza[type][start++ % pizza[type].length];
                }
            }
        }
     */

    public static void searchCase(int type) {
        for (int start = 0; start < pizza[type].length; start++) {
            int partialSum = 0;
            for (int end = 0; end < pizza[type].length-1; end++) {
                partialSum += pizza[type][(start+end) % pizza[type].length];
                if (partialSum > targetSize) break;
                cases[type][partialSum]++;
            }
        }
        int totalSum = Arrays.stream(pizza[type]).sum();
        if (totalSum <= targetSize) cases[type][totalSum]++;
    }

    public static int countCase(){
        int count = 0;
        count += cases[PIZZA_A][targetSize];
        count += cases[PIZZA_B][targetSize];
        for (int caseIdx = 1; caseIdx < targetSize; caseIdx++) {
            count += cases[PIZZA_A][caseIdx] * cases[PIZZA_B][targetSize-caseIdx];
        }

        return count;
    }
}
