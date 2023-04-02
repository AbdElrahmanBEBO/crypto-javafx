package DES;

import java.io.*;
import java.util.Scanner;

public class DES_Encrypt {
    private int[][][] S_Box = {
            {
                {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
            },
            {
                {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
            },
            {
                {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
                {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
                {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
            },
            {
                {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
                {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
                {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
                {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
            },
            {
                {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
                {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
                {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
                {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
            },
            {
                {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
                {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
                {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
                {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
            },
            {
                {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
                {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
            },
            {
                {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
                {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
                {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
                {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
            }
    };

    //Initial Permutation (IP)
    private int[] IP = {
            58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17,  9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7
    };
    //Inverse Initial Permutation (IP Inverse)
    private int[] IP_Inverse = {
            40, 8, 48, 16, 56, 24, 64, 32,
            39, 7, 47, 15, 55, 23, 63, 31,
            38, 6, 46, 14, 54, 22, 62, 30,
            37, 5, 45, 13, 53, 21, 61, 29,
            36, 4, 44, 12, 52, 20, 60, 28,
            35, 3, 43, 11, 51, 19, 59, 27,
            34, 2, 42, 10, 50, 18, 58, 26,
            33, 1, 41,  9, 49, 17, 57, 25
    };
    //Expansion Permutation (ExP)
    private int[] Exp = {
            32,  1,  2,  3,  4,  5,
             4,  5,  6,  7,  8,  9,
             8,  9, 10, 11, 12, 13,
            12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21,
            20, 21, 22, 23, 24, 25,
            24, 25, 26, 27, 28, 29,
            28, 29, 30, 31, 32,  1
    };
    //Permutation Function (P Function)
    private int[] P_Function = {
            16,  7, 20, 21, 29, 12, 28, 17,
             1, 15, 23, 26,  5, 18, 31, 10,
             2,  8, 24, 14, 32, 27,  3,  9,
            19, 13, 30,  6, 22, 11,  4, 25
    };

    final int noOfRoundAndSubKeys = 17, sizeOfPlaintext = 64;
    String[] keys_generation;
    int[][] right = new int[noOfRoundAndSubKeys][sizeOfPlaintext/2], left = new int[noOfRoundAndSubKeys][sizeOfPlaintext/2];

    public DES_Encrypt(File keyFile, File plaintextFile) throws Exception{

        keys_generation = new DES_Key(keyFile).desKey;

        int[] plaintext = hexToBinaryArray(new Scanner(plaintextFile).next());

        int[] after_IP = makePermutation(plaintext,IP);

        for (int i = 0; i < plaintext.length/2 ; i++) {
            left[0][i] = after_IP[i];
            right[0][i] = after_IP[plaintext.length/2+i];
        }

        for (int i = 1; i < noOfRoundAndSubKeys; i++) {
            left[i] = right[i-1];
            right[i] = XOR(left[i-1],F(right[i-1],stringToIntegerMatrix(keys_generation[i-1])));
        }

        for (int i = 0; i < plaintext.length/2 ; i++) {
            plaintext[i] = right[noOfRoundAndSubKeys-1][i];
            plaintext[plaintext.length/2 + i] = left[noOfRoundAndSubKeys-1][i];
        }

        int[] after_InverseIP = makePermutation(plaintext,IP_Inverse);

        String ciphertext = binaryToHexadecimal(after_InverseIP);

        System.out.println(ciphertext);
    }

    // convert string to Binary Array Numbers
    public int[] stringToIntegerMatrix(String stringType){
        int[] integerMatrix = new int[stringType.length()];
        for (int i = 0; i < stringType.length(); i++)
            integerMatrix[i] = Integer.parseInt(Character.toString(stringType.charAt(i)));
        return integerMatrix;
    }

    // make any permutation to message matrix with indexes in permutation matrix.
    public int[] makePermutation(int[] messageMatrix, int[] permutationMatrix){
        int[] newMessageMatrix = new int[permutationMatrix.length];

        for (int i = 0; i < newMessageMatrix.length; i++)
            newMessageMatrix[i] = messageMatrix[permutationMatrix[i]-1];

        return newMessageMatrix;
    }

    // make XOR Operation and the result will be in the first matrix
    public int[] XOR(int[] firstMatrix, int[] secondMatrix){
        int[] newMatrix = new int[firstMatrix.length];
        for (int i = 0; i < firstMatrix.length; i++)
            newMatrix[i] = firstMatrix[i] ^ secondMatrix[i];
        return newMatrix;
    }

    public int[] hexToBinaryArray(String hexadecimalString){
        int[] Binary = new int[sizeOfPlaintext];
        String temp; int x = 0;
        for (int i = 0; i < hexadecimalString.length(); i++) {
           temp = Integer.toBinaryString(Integer.parseInt(Character.toString(hexadecimalString.charAt(i)),16));

           while (temp.length() < 4)
               temp = "0" +temp;

            for (int j = 0; j < temp.length(); j++)
                Binary[x++] = Integer.parseInt(Character.toString(temp.charAt(j)));
        }
        return Binary;
    }

    private String binaryToHexadecimal(int[] BinaryMatrix){
        String hexadecimalNumber = "",Binary ="";
        int binaryNumber; String temp;

        for (int i = 0; i < BinaryMatrix.length; i++) {
            Binary += BinaryMatrix[i];
        }

        for (int i = 0; i < Binary.length(); i+=4) {
            binaryNumber = Integer.parseInt(Binary.substring(i,i+4),2);
            temp = Integer.toHexString(binaryNumber);

            hexadecimalNumber += temp;
        }
        return hexadecimalNumber;
    }

    // convert each 6-bits to 4-bits using 8 S-Box
    public int[] SBox(int[] matrix){
        int[] newMatrix = new int[sizeOfPlaintext/2];
        String row, column, bitsOf4 = "";
        int indexInSBox, x = 0;
        for (int i = 0; i < S_Box.length; i++) {
            row = matrix[i*6] +""+ matrix[i*6 +5];
            column = matrix[i*6 +1] +""+ matrix[i*6 +2] +""+ matrix[i*6 +3] +""+ matrix[i*6 +4];
            indexInSBox = S_Box[i][Integer.parseInt(row, 2)][Integer.parseInt(column, 2)];

            for (int j = 3; j >= 0; j--) {
                if(indexInSBox - Math.pow(2,j) >= 0) {
                    indexInSBox -= Math.pow(2,j);
                    bitsOf4 += "1";
                }
                else {
                    bitsOf4 += "0";
                }
            }

            for (int j = 0; j < bitsOf4.length(); j++) {
                newMatrix[x++] = Integer.parseInt(Character.toString(bitsOf4.charAt(j)));
            }
            bitsOf4 = "";
        }
        return newMatrix;
    }

    //make the Function between right -> i-1 and key -> i
    public int[] F(int[] right, int[] keyI){
        int[] rightAfterExp = makePermutation(right,Exp);
        int[] after_XOR = XOR(rightAfterExp,keyI);
        int[] after_SBOX = SBox(after_XOR);
        int[] after_Permutation = makePermutation(after_SBOX, P_Function);

        return after_Permutation;
    }
}
