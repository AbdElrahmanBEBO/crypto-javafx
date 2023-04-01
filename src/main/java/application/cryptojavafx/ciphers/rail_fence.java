package application.cryptojavafx.ciphers;

public class rail_fence {
    public static String cipherFunction(String plaintext, int key, char cipherway){

        String result = "";
        int k = 1;

        String[][] plaintextMatrix = new String[key][plaintext.length()];

        int[] rowIndex = new int[2*(key-1)];

        for (int i = 0; i < rowIndex.length/2; i++) {
            rowIndex[i] = i+1;
        }
        for (int i = rowIndex.length/2,j = key-2; i < rowIndex.length; i++) {
            rowIndex[i] = j--;
        }

        if (cipherway == 'E') {
            plaintextMatrix[0][0] = Character.toString(plaintext.charAt(0));
            for (int i = 0; k < plaintext.length(); i++, k++)
                plaintextMatrix[rowIndex[i % (2 * (key - 1))]][k] = Character.toString(plaintext.charAt(k));

            for (int i = 0; i < key; i++)
                for (int j = 0; j < plaintext.length(); j++)
                    if (plaintextMatrix[i][j] != null)
                        result += plaintextMatrix[i][j];
        }
        else {

        }
        System.out.println(result);
        return result;
    }
}
