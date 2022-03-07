import java.util.*;

public class Main {

    //이미 연산한 행렬곱 저장
    private static Map<Long,int [][]> memmory;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long b = sc.nextLong();

        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for(int j = 0;j< n;j++){
                matrix[i][j] = sc.nextInt() % 1000;
            }
        }

        memmory = new HashMap<>();
        int[][] answer = dac(matrix, b);
        for (int i = 0; i < n; i++) {
            for(int j = 0;j< n;j++){
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] dac(int[][] original, long i){
        if(i == 1){
            return original;
        }

        //호출위치 변경
        if(memmory.containsKey(i)){
            return memmory.get(i);
        }

        if(i % 2 == 0){
            return multiplyMatrix(dac(original, i / 2), dac(original, i / 2), i);
        }
        else{
            return multiplyMatrix(dac(original, i / 2 + 1), dac(original, i / 2), i);
        }
    }

    private static int[][] multiplyMatrix(int[][] a, int[][] b, long n) {

        int[][] matrix = new int[a.length][b[0].length];
        int sum;
        for (int i = 0; i < a.length; i++) {
            for(int j = 0;j< b[0].length;j++){
                sum = 0;
                for (int k = 0; k < b.length; k++) {
                    sum += a[i][k] * b[k][j];
                }
                matrix[i][j] = sum  % 1000;
            }
        }
        memmory.put(n, matrix);
        return matrix;
    }
}