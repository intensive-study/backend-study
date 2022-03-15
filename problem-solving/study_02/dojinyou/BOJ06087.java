// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.util.HashMap;
// import java.util.LinkedList;
// import java.util.Queue;
// import java.util.StringTokenizer;

// /**
//  * BOJ06087
//  */
// public class BOJ06087 {
//   public void solution() throws Exception{
//     Board board = new Board();
//     board.print();
//   }
//   public static void main(String[] args) throws Exception {
//     new BOJ06087().solution();
//   }
// }

// class Board {
//   private static class Point {
//     int x;
//     int y;
//     int direction; // 0(y++) / 1(x++) / 2(y--) / 3(x--)
//     int totalMirrors;

//     Point(int x, int y) {
//       this(x,y,0,0);
//     }
//     Point(int x, int y, int direction, int totalMirrors) {
//       this.x = x;
//       this.y = y;
//       this.direction = direction;
//       this.totalMirrors = totalMirrors;
//     }
//     Point(Point other) {
//       this.x = other.getNextX();
//       this.y = other.getNextY();
//       this.direction = other.direction;
//       this.totalMirrors = other.totalMirrors;
//     }

//     @Override
//     public boolean equals(Object other) {
//       if (other instanceof Point ) {
//         if (((Point)other).x == this.x && ((Point)other).y == this.y) {
//           return true;
//         }
//       }
//       return false;
//     }

//     public int getNextX() {
//       int nextX = x;
//       if (direction == 1) nextX++;
//       if (direction == 3) nextX--;
//       return nextX;
//     }

//     public int getNextY() {
//       int nextY = y;
//       if (direction == 0) nextY++;
//       if (direction == 2) nextY--;
//       return nextY;
//     }

//     public static Point getNextPoint(Point origin) {
//       return new Point(origin.getNextX(), origin.getNextY(), origin.direction, origin.totalMirrors);
//     }

//     public static Point getNextPoint(Point origin, int rotate) {
//       int nextDirection = (origin.direction + rotate + 4 ) % 4;
//       return new Point(origin.getNextX(), origin.getNextY(), nextDirection, origin.totalMirrors+1);
//     }
//   }
//   final int WIDTH;
//   final int HEIGHT;
//   char[][] board;
//   int[][] visited;
//   Point startPoint;
//   Point endPoint;

//   public Board() throws Exception {
//     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//     StringTokenizer st = new StringTokenizer(br.readLine());
//     WIDTH = Integer.parseInt(st.nextToken());;
//     HEIGHT = Integer.parseInt(st.nextToken());;
//     board = new char[HEIGHT][WIDTH];
//     visited = new int[HEIGHT][WIDTH];

//     for(int i=0;i<HEIGHT;i++) {
//       st = new StringTokenizer(br.readLine());
//       char[] tokens = st.nextToken().toCharArray();
//       for(int j=0;j<WIDTH;j++) {
//         board[i][j] = tokens[j];
//         if (board[i][j] == 'C') {
//           if (startPoint == null) {
//             startPoint = new Point(i,j);
//           } else {
//             endPoint = new Point(i,j);
//           }
//         }
//       }
//     }
//   }

//   public boolean isPossible(Point nextPoint) {
//     if (nextPoint.x >= WIDTH && nextPoint.x < 0) return false;
//     if (nextPoint.y >= HEIGHT && nextPoint.y < 0) return false;
//     if (visited[nextPoint.y][nextPoint.x] != 0) return false;
//     if (board[nextPoint.y][nextPoint.x] == '*') return false;
//     return true;
//   }
  
//   public void searchRoute() {
//     Queue<Point> q = new LinkedList<>();
//     Queue<Point> nextDirectPoints = new LinkedList<>();
//     Queue<Point> nextRotatePoints = new LinkedList<>();

//     // 
//     for (int i=0;i<4;i++) {
//       Point nextDirectionPoint = new Point(startPoint.x, startPoint.y,i,1);
//       Point nextPoint = Point.getNextPoint(nextDirectionPoint);
//       if (isPossible(nextPoint)) {
//         q.add(nextDirectionPoint);
//       }
//     }

//     while(!q.isEmpty()) {
//       // Point 추출
//       Point currentPoint = q.poll();
//       // 방문 기록
//       visited[currentPoint.y][currentPoint.x] = currentPoint.totalMirrors;
//       // 해당 포인트가 도착 지점이면 종료
//       if (currentPoint.equals(endPoint)) break;
//       // 현 포인트에서 갈 수 있는 직선 거리 모두 확인
//       // 직선 거리
//       Point nextPoint = Point.getNextPoint(currentPoint);
//       Point nextRightPoint;
//       Point nextLeftPoint;
//       while(!isPossible(nextPoint)) {
//         q.add(nextPoint);
//         nextRightPoint = Point.getNextPoint(Point.getNextPoint(currentPoint, 1));
//         nextLeftPoint = Point.getNextPoint(Point.getNextPoint(currentPoint, -1));

//         rightNextPoint
//         nextRotatePoints.add();


//       }


//     }
//   }
//   public void print() throws Exception {
//     for(int i=0;i<HEIGHT;i++) {
//       for(int j=0;j<WIDTH;j++) {
//         System.out.print(board[i][j]);
//       }
//       System.out.println();
//     }
//   }

  
// }