import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        int[][] sudoku = {{5 , 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}};
        Stack<int[]> unusedCoordinate = new Stack<>();
        for (int i = 8; i >= 0; i--) {
            for (int j = 8; j >= 0; j--) {
                if(sudoku[i][j] ==0 ){
                    unusedCoordinate.push(new int[]{i, j});
                }
            }
        }
        Stack<int[]> usedCoordinate = new Stack<>();
        System.out.println("Unsolved Sudoku: ");
        print2DArray(sudoku);
        solver(sudoku, unusedCoordinate, usedCoordinate);
        System.out.println("\n Solved Sudoku: ");
        print2DArray(sudoku);

    }
    public static boolean solver(int[][] sudoku, Stack<int[]> unusedCoordinate, Stack<int[]> usedCoordinate ){
        if(unusedCoordinate.empty()){
            return true;
        }
        int[] tempCoord = unusedCoordinate.pop();
        boolean temp = false;
        while(!temp){
            sudoku[tempCoord[0]][tempCoord[1]]++;
            if(sudoku[tempCoord[0]][tempCoord[1]] >= 10){
                sudoku[tempCoord[0]][tempCoord[1]] = 0;
                unusedCoordinate.push(tempCoord);
                usedCoordinate.pop();
                //unusedCoordinate.push();
                return false;
            }
            if(check(sudoku, tempCoord)){
                usedCoordinate.push(tempCoord);
                temp = solver(sudoku, unusedCoordinate, usedCoordinate);
            }
        }
        return temp;
    }

    static boolean check(int[][] sudoku,int[] tempCoord ){
        int tempNum = sudoku[tempCoord[0]][tempCoord[1]];
        for(int i = 0; i < 9; i++){
            if(sudoku[tempCoord[0]][i] == tempNum && i != tempCoord[1] ) return false;
        }
        for(int i = 0; i < 9; i++){
            if(sudoku[i][tempCoord[1]] == tempNum && i != tempCoord[0]) return false;
        }
        for(int i =(tempCoord[0]/3) * 3;i < (tempCoord[0]/3) * 3 +3; i++){
            for(int j =(tempCoord[1]/3) * 3;j < (tempCoord[1]/3) * 3 +3; j++){
                if(i == tempCoord[0] && j == tempCoord[1]) continue;
                if(sudoku[i][j] == tempNum) return false;
            }
        }

        return true;
    }
    static  void printStack(Stack<int[]> stack){
        for (int[] i : stack) {
            System.out.println(i[0] + " " + i[1]);
        }
    }
    static void print2DArray(int[][] array) {
        final int BLOCK_SIZE = 3;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                sb.append(String.format(" %d ", array[i][j]));
                if (j % BLOCK_SIZE == BLOCK_SIZE - 1 && j != array[i].length - 1) {
                    sb.append("|");
                }
            }
            sb.append("\n");
            if (i % BLOCK_SIZE == BLOCK_SIZE - 1 && i != array.length - 1) {
                for (int j = 0; j < array[i].length * BLOCK_SIZE + 2; j++) {
                    sb.append("-");
                }
                sb.append("\n");
            }
        }

        System.out.print(sb.toString());
    }

}