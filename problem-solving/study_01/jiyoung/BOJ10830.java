package study_01.jiyoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10830 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int matrixSize = Integer.parseInt(st.nextToken());
        long squared = Long.parseLong(st.nextToken());
        long[][] target = new long[matrixSize][matrixSize];

        for (int row = 0; row < matrixSize; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < matrixSize; col++) {
                target[row][col] = Long.parseLong(st.nextToken());
            }
        }

        long[][] result = divide(target, squared);

        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < matrixSize; row++) {
            for (int col = 0; col < matrixSize; col++) {
                sb.append(result[row][col] % 1000).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    public static long[][] divide(long[][] target, long number) {
        if(number == 1){
            return target;
        }

        long[][] res = divide(target, number/2);

        if(number % 2 == 0) {
            return doSquare(res, res);
        }
        else {
            return doSquare(doSquare(res, target), res);
        }
    }

    public static long[][] doSquare(long[][] matrix, long[][] target) {
        long[][] result = new long[matrix.length][matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                long part = 0L;
                for (int idx = 0; idx < matrix.length; idx++) {
                    part += matrix[row][idx] * target[idx][col];
                }
                result[row][col] = part % 1000;
            }
        }

        return result;
    }

}
