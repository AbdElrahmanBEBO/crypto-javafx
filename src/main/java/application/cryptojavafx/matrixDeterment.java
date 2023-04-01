package other;

import java.util.Scanner;

public class matrixDeterment {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the Matrix Size: ");
            int matrixSize = scan.nextInt();
            int[][] matrix = new int[matrixSize][matrixSize];

            System.out.println("Enter the Matrix ");
            for (int i = 0; i < matrix.length; i++)
                for (int j = 0; j < matrix.length; j++)
                    matrix[i][j] = scan.nextInt();

            System.out.println("The Determent is " + getMatrixDeterment(matrix, 0));
        }
    }
    public static int getMatrixDeterment(int[][] matrix, int determent){
        if (matrix.length == 1)
            return matrix[0][0];

        int[][] subMatrix = new int[matrix.length-1][matrix.length-1];
        for (int column = 0; column < matrix.length; column++) {
            for (int i = 1; i < matrix.length; i++)
                for (int j = 0, subMatrixColumn=0; j < matrix.length; j++)
                    if (j !=column)
                        subMatrix[i-1][subMatrixColumn++] = matrix[i][j];

            determent += (int)Math.pow(-1,column) * matrix[0][column] * getMatrixDeterment(subMatrix,determent);
        }
        return determent;
    }
}
