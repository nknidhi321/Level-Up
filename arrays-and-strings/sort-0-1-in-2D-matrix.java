/*

Given a 2d array containing only 0s and 1s, I was asked to sort it in a single pass.
Size of all rows is same.
Eg :
{{1,0,1},
 {0,0,0},
 {1,1,0}}

becomes

{{0,0,0},
 {0,0,1},
 {1,1,1}}

Approach : This can be done using two pointers:
one for placing 0s at the beginning and another for placing 1s at the end

To turnInto 1D = i * m + j
To calculate row = rowNum / No. of col
To calculate col = rowNum % No. of col

*/

public class SortBinaryMatrix {

    public static void sortBinaryMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int start = 0; // Pointer for 0s
        int end = rows * cols - 1; // Pointer for 1s

        while (start <= end) { 
            int startRow = start / cols;
            int startCol = start % cols;
            int endRow = end / cols;
            int endCol = end % cols;

            // Swap matrix[startRow][startCol] and matrix[endRow][endCol]
            if (matrix[startRow][startCol] == 1 && matrix[endRow][endCol] == 0) {
                int temp = matrix[startRow][startCol];
                matrix[startRow][startCol] = matrix[endRow][endCol];
                matrix[endRow][endCol] = temp;
                start++;
                end--;
            } else {
                if (matrix[startRow][startCol] == 0) {
                    start++;
                }
                if (matrix[endRow][endCol] == 1) {
                    end--;
                }
            }
        }
    }

}
