package application.cryptojavafx.ciphers;

public class row_transposition {
    public static String cipherFunction(String plaintext, String key,int numberOfColumns, char cipherway) {

        while (plaintext.length()%numberOfColumns != 0) {
            plaintext +="F";
        }

        int x, k = 0, numberOfRows = plaintext.length()/numberOfColumns;
        String[] columnOrder = key.split(" "); String result = "";
        int[] rowOrder = new int[numberOfColumns];

        if (cipherway == 'E') {
            char[][] textMatrix = new char[numberOfRows][numberOfColumns];
            for (int i = 0; i < numberOfRows; i++) {
                for (int j = 0; j < numberOfColumns; j++) {
                    textMatrix[i][j] = plaintext.charAt(k++);
                }
            }
            for (int i = 0 ; i < numberOfColumns; i++) {
                x = Integer.parseInt(columnOrder[i])-1;
                for (int j = 0; j < numberOfRows; j++) {
                    result += textMatrix[j][x];
                }
            }
        }
        else {
            char[][] textMatrix = new char[numberOfColumns][numberOfRows];
            for (int i = 0; i < numberOfColumns; i++) {
                for (int j = 0; j < numberOfRows; j++) {
                    textMatrix[i][j] = plaintext.charAt(k++);
                }
            }
            mappingKey(rowOrder, columnOrder);
            for (int i = 0 ; i < numberOfRows; i++) {
                for (int j = 0; j < numberOfColumns; j++) {
                    x = rowOrder[j];
                    result += textMatrix[x][i];
                }
            }
        }

        System.out.println(result);
        return result;
    }
    public static void mappingKey(int[] rowOrder, String[] columnOrder){
        for (int i = 0; i < rowOrder.length; i++) {
            for (int j = 0; j < rowOrder.length; j++) {
                if (Integer.parseInt(columnOrder[j]) == i+1) {
                    rowOrder[i] = j;
                    continue;
                }
            }
        }
    }
}
