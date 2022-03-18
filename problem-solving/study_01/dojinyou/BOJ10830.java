import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10830 {
  class SquareMatrix {
    int size;
    int[][] matrixArray;
    SquareMatrix(int n) {
      this.size = n;
      this.matrixArray = new int[n][n];
      for (int i = 0; i<n;i++) {
        this.matrixArray[i][i] = 1;
      }
    }
    SquareMatrix(int[][] matrixArray) {
      this.size = matrixArray.length;
      this.matrixArray = matrixArray.clone();
    }

    public SquareMatrix pow() {
      SquareMatrix result = new SquareMatrix(this.matrixArray);
      result.mul(result);
      return result;
    }
    
    public void print() {
      StringBuilder sb = new StringBuilder();
      for (int i = 0;i<this.size;i++) {
        for (int j = 0;j<this.size;j++) {
          sb.append(this.matrixArray[j][i]).append(" ");
        }
        sb.append("\n");
      }
      System.out.print(sb);
    }

    public void mul(SquareMatrix other) {
      if (this.size==other.size) {
        int[][] result = new int[this.size][this.size];
        for (int i = 0;i<this.size;i++) {
          for (int j = 0;j<this.size;j++) {
            int sum = 0;
            for (int k = 0;k<this.size;k++) {
              sum += this.matrixArray[i][k] * other.matrixArray[k][j];
            }
            result[i][j] = sum%1000;
          }
        }
        this.matrixArray = result;
      }
    }
  }

  public void solution() throws Exception {
    // 입력을 받기 위한 인스턴스 생성
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 상수값 저장
    int N = Integer.parseInt(st.nextToken());
    long B = Long.parseLong(st.nextToken());

    int[][] matrix = new int[N][N];
    for(int i=0;i<N;i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0;j<N;j++) {
        matrix[j][i] = Integer.parseInt(st.nextToken());
      }
    }
    char[] binaryString = Long.toBinaryString(B).toCharArray();
    // System.out.println(binaryString);
    
    SquareMatrix[] matrixArray = new SquareMatrix[binaryString.length+1];
    matrixArray[1] = new SquareMatrix(matrix);
    // System.out.println(1+"번쨰 매트릭스");
    // matrixArray[1].print();
    for (int i = 1; i<binaryString.length;i++) {
      matrixArray[i+1] = matrixArray[i].pow();
      // System.out.println(i+1+"번쨰 매트릭스");
      // matrixArray[i+1].print();
    }

    SquareMatrix result = new SquareMatrix(N);
    for (int i = 0; i<binaryString.length;i++) {
      if (binaryString[i] == '1') {
        result.mul(matrixArray[binaryString.length-i]);
      }
    }
    result.print();
  }
  public static void main(String[] args) throws Exception{
    new BOJ10830().solution();
  }
}