// ############################################################
// ------------------------------------------------------------
//
//
//
// ******************** Submitted Solution ********************
//
//
//
// ------------------------------------------------------------
// ############################################################

public class Main {

    // Shapes for the figures 'A' to 'E'
    static int[][][] shapes = {
        {{1}},                     // Shape A
        {{1, 1, 1}},               // Shape B
        {{1, 1}, {1, 1}},          // Shape C
        {{1, 0}, {1, 1}, {1, 0}},  // Shape D
        {{0, 1, 0}, {1, 1, 1}}     // Shape E
    };

    public static int[][] solution(int n, int m, char[] figures) {
        int[][] grid = new int[n][m];  // Initialize the grid with zeros

        // Iterate over each figure and place them on the grid
        for (int f = 0; f < figures.length; f++) {
            int figureIndex = figures[f] - 'A'; // Convert 'A'-'E' to index 0-4
            int[][] shape = shapes[figureIndex];
            placeFigure(grid, shape, f + 1); // Place figure number f + 1
        }

        return grid;
    }

    // Function to place the figure on the grid
    public static void placeFigure(int[][] grid, int[][] shape, int figureNum) {
        int n = grid.length;
        int m = grid[0].length;

        int bestRow = -1;
        int bestCol = -1;
       
        // Start with an invalid position, and update it to find the lowest available row
        for (int col = 0; col <= m - shape[0].length; col++) {
            for (int row = 0; row <= n - shape.length; row++) {
                if (canPlace(grid, shape, row, col)) {
                    if (bestRow == -1 || row < bestRow || (row == bestRow && col < bestCol)) {
                        bestRow = row;
                        bestCol = col;
                    }
                }
            }
        }

        // Place the figure at the best found position
        if (bestRow != -1 && bestCol != -1) {
            placeShape(grid, shape, bestRow, bestCol, figureNum);
        }
    }

    // Function to check if the shape can be placed at (row, col) on the grid
    public static boolean canPlace(int[][] grid, int[][] shape, int row, int col) {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] == 1 && grid[row + i][col + j] != 0) {
                    return false; // Overlap detected
                }
            }
        }
        return true;
    }

    // Function to place the shape on the grid
    public static void placeShape(int[][] grid, int[][] shape, int row, int col, int figureNum) {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] == 1) {
                    grid[row + i][col + j] = figureNum;
                }
            }
        }
    }

    // Utility function to print the grid (for testing purposes)
    public static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = 5, m = 5;
        // char[] figures = {'A','D', 'E'};
        char[] figures = {'E', 'A', 'B', 'C', 'D'};
       
        int[][] result = solution(n, m, figures);
        printGrid(result); // Print the result grid
    }
}






// ############################################################
// ------------------------------------------------------------
//
//
//
// ******************** Another Solution ********************
//
//
//
// ------------------------------------------------------------
// ############################################################


public class Main {
    public static void main(String[] args) {
        int[][] result = solution(5, 5, new char[]{'E', 'A', 'B', 'C', 'D'});
        for(int[] r : result)
            System.out.println(Arrays.toString(r));
    }
    
    private static int[][] solution(int n, int m, char[] figures) {

        Map<Character, int[][]> directions = new HashMap<>() {{
           put('A', new int[][] {{0, 0}}); 
           put('B', new int[][] {{0, 0}, {0, 1}, {0, 2}}); 
           put('C', new int[][] {{0, 0}, {0, 1}, {1, 0}, {1, 1}}); 
           put('D', new int[][] {{0, 0}, {1, 0}, {2, 0}, {1, 1}}); 
           put('E', new int[][] {{0, 1}, {1, 0}, {1, 1}, {1, 2}}); 
        }};

        int[][] matrix = new int[n][m];

        for(int ind=0; ind<figures.length; ind++) {
            char ch = figures[ind];
            int i=0, j=0;
            while(i<n || j<m) {
                if(doesFit(ch, directions, i, j, matrix)) {
                    fillUpMatrix(ind+1, ch, directions, i, j, matrix);
                    if(j==m) {
                        i++;
                        j=0;
                    }
                    else {
                        j++;
                    }
                    break;
                }      
                if(j==m) {
                    i++;
                    j=0;
                }
                else {
                    j++;
                }
            } 
        }

        return matrix;

    }

    private static boolean doesFit(char ch, Map<Character, int[][]> directions, int i, int j, int[][] matrix) {
        
        int[][] direction = directions.get(ch);

        for(int[] dir : direction) {
            int newI = i+dir[0];
            int newJ = j+dir[1];
            if(newI>=matrix.length || newJ>=matrix[0].length || newI<0 || newJ<0)
                return false;
            if(matrix[newI][newJ]!=0)
                return false;
        }
        
        return true;
    }

    private static void fillUpMatrix(int index, char ch, Map<Character, int[][]> directions, int i, int j, int[][] matrix) {
        int[][] direction = directions.get(ch);

        for(int[] dir : direction) {
            int newI = i+dir[0];
            int newJ = j+dir[1];
            matrix[newI][newJ] = index;   
        }
    }
}
