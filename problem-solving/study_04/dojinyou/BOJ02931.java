import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ02931 {
  public static void main(String[] args) throws Exception {
    new BOJ02931().solution();   
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
          put('Z', new boolean[] {false,false,false,false});
          put('M', new boolean[] {false,false,false,false});
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
          break;
        }
      }
      
      return (blankBlockRow+1) + " " + (blankBlockColumn+1) + " " + Character.toString(blockType);
    }

    private boolean isConnected(char block, Direction direction)  {
      boolean[] blockConnection = this.blockConnection.get(block);
      return blockConnection[direction.toInt()];


    }
    public boolean[] getNearBlockInfo(int blankBlockRow, int blankBlockColumn) {
      boolean[] nearBlockInfo = new boolean[4];
      if(blankBlockRow-1 > 0) {
        char upperBlock = europe[blankBlockRow-1][blankBlockColumn];
        nearBlockInfo[0] = isConnected(upperBlock,Direction.DOWN);
      }
      if(blankBlockRow+1 < this.numOfRow) {
        char underBlock = europe[blankBlockRow+1][blankBlockColumn];
        nearBlockInfo[2] = isConnected(underBlock,Direction.UP);
      }
      if(blankBlockColumn+1 < this.numOfColumn) {
        char rightBlock = europe[blankBlockRow][blankBlockColumn+1];
        nearBlockInfo[1] = isConnected(rightBlock,Direction.LEFT);
      }
      if(blankBlockColumn-1 > 0) {
        char leftBlock = europe[blankBlockRow][blankBlockColumn-1];
        nearBlockInfo[3] = isConnected(leftBlock,Direction.RIGHT);
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
      for(boolean blockInfo:getNearBlockInfo(r,c)){
        if(blockInfo) return true;
      }
      return false;
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
