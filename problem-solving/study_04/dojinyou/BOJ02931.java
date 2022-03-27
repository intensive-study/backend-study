import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ02931old {
  public static void main(String[] args) throws Exception {
    new BOJ02931old().solution();   
  }

  private void solution() throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 행과 열의 크기 = 1 ~ 25
    final int NUM_OF_ROW = Integer.parseInt(st.nextToken()); 
    final int NUM_OF_COLUMN = Integer.parseInt(st.nextToken());

    // TODO: 1. 삭제된 칸 찾기
    // TODO: 2. 삭제된 블럭 찾기

    Europe europe = new Europe(NUM_OF_ROW, NUM_OF_COLUMN);

    for(int row = 0; row < NUM_OF_ROW; row++) {
      st = new StringTokenizer(br.readLine());
      char[] columnBlockData = st.nextToken().toCharArray();
      europe.fill(row, columnBlockData);
    }

    System.out.println(europe.getBlankBlockInfo());
  }

  private class Europe {
    int numOfRow;
    int numOfColumn;
    char[][] europe;
    final Map<Character,boolean[]> blockConnection;

    Europe(int numOfRow, int numOfColumn) {
      this.numOfRow = numOfRow;
      this.numOfColumn = numOfColumn;
      this.europe = new char[numOfRow][numOfColumn];
      blockConnection = new HashMap<>() {
        {
          put('|', new boolean[] {true,false,true,false});
          put('-', new boolean[] {false,true,false,true});
          put('+', new boolean[] {true,true,true,true});
          put('1', new boolean[] {false,true,true,false});
          put('2', new boolean[] {true,true,false,false});
          put('3', new boolean[] {true,false,false,true});
          put('4', new boolean[] {false,false,true,true});
          put('.', new boolean[] {false,false,false,false});
          put('Z', new boolean[] {true,true,true,true});
          put('M', new boolean[] {true,true,true,true});
        }
      };
    }
    public String getBlankBlockInfo() {
      int[] blankBlockPosition = findBlankBlockPosition();
      return getBlockType(blankBlockPosition);
    }
    public void fill(int numOfRow, char[] columnBlockData) {
      this.europe[numOfRow] = columnBlockData;
    }

    private String getBlockType(int[] position) {
      int blankBlockRow = position[0];
      int blankBlockColumn = position[1];
      char blockType = ' ';
      boolean[] nearBlockInfo = getNearBlockInfo(blankBlockRow, blankBlockColumn);
      List<Character> keys = new ArrayList<>(this.blockConnection.keySet());
      List<boolean[]> values = new ArrayList<>(this.blockConnection.values());
      for (int i = 0; i < values.size(); i++) {
        boolean[] connectionValue = values.get(i);
        boolean equal = true;
        for (int j = 0; j < nearBlockInfo.length; j++) {
          if (connectionValue[j] != nearBlockInfo[j]) {
            equal = false;
            break;
          }
        }
        if(equal) {
          blockType = keys.get(i);
          if (blockType == 'M' || blockType == 'Z') {
            blockType = ' ';
            continue;
          }
          break;
        }
      }
      
      return (blankBlockRow+1) + " " + (blankBlockColumn+1) + " " + Character.toString(blockType);
    }

    private boolean isConnectable(char block, Direction direction)  {
      boolean[] blockConnection = this.blockConnection.get(block);
      return blockConnection[direction.toInt()];


    }
    public boolean[] getNearBlockInfo(int blankBlockRow, int blankBlockColumn) {
      boolean[] nearBlockInfo = new boolean[4];

      if(blankBlockRow-1 >= 0) {
        char upperBlock = europe[blankBlockRow-1][blankBlockColumn];
        nearBlockInfo[Direction.UP.toInt()] = isConnectable(upperBlock,Direction.DOWN);
      }
      if(blankBlockRow+1 < this.numOfRow) {
        char underBlock = europe[blankBlockRow+1][blankBlockColumn];
        nearBlockInfo[Direction.DOWN.toInt()] = isConnectable(underBlock,Direction.UP);
      }
      if(blankBlockColumn+1 < this.numOfColumn) {
        char rightBlock = europe[blankBlockRow][blankBlockColumn+1];
        nearBlockInfo[Direction.RIGHT.toInt()] = isConnectable(rightBlock,Direction.LEFT);
      }
      if(blankBlockColumn-1 >= 0) {
        char leftBlock = europe[blankBlockRow][blankBlockColumn-1];
        nearBlockInfo[Direction.LEFT.toInt()] = isConnectable(leftBlock,Direction.RIGHT);
      }
      return nearBlockInfo;
    }
    
    public int[] findBlankBlockPosition() {
      for (int i = 0; i < this.numOfRow; i++) {
        for (int j = 0; j < this.numOfColumn; j++) {
          if(isBlank(i, j)) return new int[]{i,j};
        }
      }
      return null;
    }
    
    public boolean isBlank(int r, int c) {
      if(this.europe[r][c] != '.') return false;
      int cnt = 0;
      for(boolean blockInfo:getNearBlockInfo(r,c)){
        if(blockInfo) cnt++;
      }
      return cnt >= 2;
    }
  }
  enum Direction {
    UP(0),RIGHT(1),DOWN(2),LEFT(3);

    private final int value;
    private Direction(int value){
      this.value = value;
    }

    public int toInt() {
      return value;
    }
  }
}


// 해당 알고리즘은 빈칸의 위치를 찾고 주변에 연결된 정보를 수집하여 적절한 블록을 선택한다.
// 이 알고리즘이 이 문제를 풀지 못한 이유는 M과 Z의 가변성 때문이다.
// 가령 예를 들어, 아래와 같은 예시가 있다고 가정하자.
//
// 3 4
// ..1M
// 1-+.
// Z.23
// 
// 위 예시에서 적절한 블록은 2 4 4이다.
// 하지만 해당 알고리즘으로 진행할 경우 2 4 위치의 연결 정보는 상하좌 3군데가 연결된 것으로 된다.
// 그 이유는 M과 Z가 4방향 모두 연결가능하다고 초기화하기 때문이다.
// 따라서 이를 해결하기 위해서는 M,Z의 연결정보를 동적으로 할당해주어야 한다.
// 이러한 수정사항보다는 Block의 흐름을 추적하는 것이 더 정확한 구현이 될 것이라고 생각한다.