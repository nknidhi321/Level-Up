package recursion;

public class RatMazeMultipleJumps {

	public static void main(String[] args) {
		int[][] jumpMatrix = { {3, 1, 0, 1},
							   {3, 0, 0, 1},
							   {0, 1, 0, 1},
							   {0, 0, 0, 1} };
		
		solveMaze(jumpMatrix, 4);
	}

	public static int[][] dir = { { 1, 0 }, { 0, 1 } };

	public static void solveMaze(int[][] jumpMatrix, int n) {
		if (jumpMatrix[0][0] == 0) return;

		int[][] ansMatrix = new int[n][n];
		solveMaze_Util(0, 0, n, jumpMatrix, ansMatrix);
	}

	public static void solveMaze_Util(int sr, int sc, int n, int[][] jumpMatrix, int[][] ansMatrix) {
		if (sr == n - 1 && sc == n - 1) {
			ansMatrix[sr][sc] = 1; //Mark Path to print the last cell
			display2D(ansMatrix);
			ansMatrix[sr][sc] = 0; //Unmark Path so that it can become part of another path
			return;
		}

		//Using jumpMatrix as both visited and atmost jump value matrix
		//Storing the original value of jumpMatrix cell in temporary variable
		int jump = jumpMatrix[sr][sc]; //So as to use that cell as visited
		jumpMatrix[sr][sc] = 0; // Marking visited
		ansMatrix[sr][sc] = 1;  // Mark Path to form the ans

		for (int d = 0; d < dir.length; d++) {
			for (int rad = 1; rad <= jump; rad++) { //radius i.e jump
				int r = sr + rad * dir[d][0];
				int c = sc + rad * dir[d][1];

				//Within the matrix
				if (r <= n - 1 && c <= n - 1) {
					
					//Checking if the cell is already visited or jump == 0
					if (jumpMatrix[r][c] != 0) { //if yes then don't make that call
						solveMaze_Util(r, c, n, jumpMatrix, ansMatrix); //else make the call
					}
				} 
				
				//Out of the matrix for that diection
				else {
					//If we go out of bounds for a particular direction
					//say sr == 4  [and suppose n is also 4]
					//then, no need to check for the rest of the rad++ or jumps++ 
					//because it's obvious if sr + rad is false, 
					//then in next iteration when rad++ will take place
					//then also sr + rad(incremented value of rad) will be false only and so on.. 
					//So why to check the condition for rest of the rad++ 
					//hence break the loop for of that direction jump
					break;
				}
			}
		}
		jumpMatrix[sr][sc] = jump; //Backtrack, mark unvisited
		ansMatrix[sr][sc] = 0; // Unmark Path so that it can become part of another path
	}

	public static void display2D(int[][] arr) {
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
