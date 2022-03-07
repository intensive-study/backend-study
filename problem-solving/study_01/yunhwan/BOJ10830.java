import java.util.*;

public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long b = sc.nextLong();

        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for(int j = 0;j< n;j++){
                // b 가 1이면 행렬의 원소에 1000이 들어가므로 입력할 때 부터 미리 나머지 연산
                matrix[i][j] = sc.nextInt() % 1000;
            }
        }
        //b = 100 일 떄 6
        int[][] answer = divide(matrix, b);

        //출력
        for (int i = 0; i < n; i++) {
            for(int j = 0;j< n;j++){
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] divide(int[][] matrix,long i){
        System.out.println("*");
        if(i == 1l){
            return matrix;
        }

        int[][] m = divide(matrix, i / 2);

        m = multiplyMatrix(m, m);
        if(i % 2 == 1) m = multiplyMatrix(m, matrix);

        return m;
    }

    private static int[][] multiplyMatrix(int[][] a, int[][] b) {
        int[][] matrix = new int[a.length][b[0].length];
        int sum;
        for (int i = 0; i < a.length; i++) {
            for(int j = 0;j< b[0].length;j++){
                sum = 0;
                for (int k = 0; k < b.length; k++) {
                    // ikkj
                    sum += a[i][k] * b[k][j];
                }
                matrix[i][j] = sum  % 1000;
            }
        }
        return matrix;
    }
}