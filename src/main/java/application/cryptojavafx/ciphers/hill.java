package application.cryptojavafx.ciphers;

public class hill {
    public static String cipherFunction(String plaintext, String Keys, char cipherway){

        String result = "";
        char firstChar = 'a';
        String[] keySplit = Keys.split(" ");
        int keySize = (int) Math.sqrt(keySplit.length);

        while (plaintext.length()%keySize != 0)
            plaintext += "f";


        int Row = plaintext.length()/keySize;

        int[][] keyMatrix = new int[keySize][keySize];
        int[][] txtMatrix = new int[Row][keySize];
        char[][] resultMatrix = new char[Row][keySize];

        for (int i = 0; i < keySize; i++)
            for (int j = 0; j < keySize; j++)
                keyMatrix[i][j] = Integer.parseInt(keySplit[(i*keySize)+j]);

        int x = 0;
        for (int i = 0; i < Row; i++)
            for (int j = 0; j < keySize; j++)
                txtMatrix[i][j] = plaintext.charAt(x++) - firstChar;

        if (cipherway == 'D')
            matrixInverse(keyMatrix,keySize);

        int oneCell;
        for (int k = 0; k < keySize; k++) {
            for (int i = 0; i < Row ; i++) {
                oneCell = 0;
                for (int j = 0; j < keySize; j++) {
                    oneCell += txtMatrix[i][j] * keyMatrix[j][k];
                }
                while (oneCell<0)
                    oneCell += 26;
                resultMatrix[i][k] = (char)(oneCell%26);
            }
        }

        for (int i = 0; i < Row; i++) {
            for (int j = 0; j < keySize; j++) {
                result += (char)((int)resultMatrix[i][j]+firstChar);
            }
        }
        System.out.println(result);
        return result;
    }
    public static void matrixInverse(int[][] keyMatrix, int keySize){
        int[][] AdjMatrix = new int[keySize][keySize];
        int determent = 0;

        if(keySize == 3){
            determent = ((keyMatrix[0][0]*getDeterment(keyMatrix,"11","22","12","21"))
                       -(keyMatrix[0][1]*getDeterment(keyMatrix,"10","22","12","20"))
                       +(keyMatrix[0][2]*getDeterment(keyMatrix,"10","21","11","20")))%26;

            determent = positive(determent);
            determent = getInverseOfDeterment(determent);

            AdjMatrix[0][0] =  getDeterment(keyMatrix,"11","22","12","21")%26;
            AdjMatrix[0][1] = -getDeterment(keyMatrix,"10","22","12","20")%26;
            AdjMatrix[0][2] =  getDeterment(keyMatrix,"10","21","11","20")%26;

            AdjMatrix[1][0] = -getDeterment(keyMatrix,"01","22","02","21")%26;
            AdjMatrix[1][1] =  getDeterment(keyMatrix,"00","22","02","20")%26;
            AdjMatrix[1][2] = -getDeterment(keyMatrix,"00","21","01","20")%26;

            AdjMatrix[2][0] =  getDeterment(keyMatrix,"01","12","02","11")%26;
            AdjMatrix[2][1] = -getDeterment(keyMatrix,"00","12","02","10")%26;
            AdjMatrix[2][2] =  getDeterment(keyMatrix,"00","11","01","10")%26;

            transpose(AdjMatrix);

            for (int i = 0; i < keySize; i++)
                for (int j = 0; j < keySize; j++)
                    keyMatrix[i][j] = (determent*positive(AdjMatrix[i][j]))%26;
        }
        else if (keySize == 2){
            determent = getDeterment(keyMatrix,"00","11","10","01");
            determent = getInverseOfDeterment(determent);

            int temp = keyMatrix[0][0];
            keyMatrix[0][0] = keyMatrix[1][1];
            keyMatrix[1][1] = temp;

            keyMatrix[0][1] = -keyMatrix[0][1];
            keyMatrix[1][0] = -keyMatrix[1][0];

            for (int i = 0; i < keySize; i++)
                for (int j = 0; j < keySize; j++)
                    keyMatrix[i][j] = (determent*positive(keyMatrix[i][j]))%26;
        }

    }
    public static int getDeterment(int[][] keyMatrix,String x00,String x11,String x10,String x01){
        int determent = ((keyMatrix[Integer.parseInt(x00.substring(0,1))][Integer.parseInt(x00.substring(1,2))]
                         *keyMatrix[Integer.parseInt(x11.substring(0,1))][Integer.parseInt(x11.substring(1,2))])
                        -(keyMatrix[Integer.parseInt(x01.substring(0,1))][Integer.parseInt(x01.substring(1,2))]
                         *keyMatrix[Integer.parseInt(x10.substring(0,1))][Integer.parseInt(x10.substring(1,2))]))%26;

        while(determent < 0 )
            determent += 26;

        return determent;
    }
    public static int getInverseOfDeterment(int determent){
        for (int i = 0; i < 26; i++) {
            if((determent*i-1)%26 == 0){
                return i;
            }
        }
        return 0;
    }
    public static void multiplyTwoMatrix(int[][] plain, int[][] cipher, int[][] key, int length){
        for (int k = 0; k < length; k++) {
            for (int i = 0; i < length; i++) {
                for (int j = 0; j <length; j++) {
                    key[i][k] += plain[i][j]*cipher[j][i];
                }
            }
        }
    }
    public static int positive(int number){
        while(number < 0 )
            number += 26;
        return number;
    }
    public static void transpose(int[][] keyMatrix){
        int temp;

        temp = keyMatrix[0][1];
        keyMatrix[0][1] = keyMatrix[1][0];
        keyMatrix[1][0] = temp;

        temp = keyMatrix[0][2];
        keyMatrix[0][2] = keyMatrix[2][0];
        keyMatrix[2][0] = temp;

        temp = keyMatrix[1][2];
        keyMatrix[1][2] = keyMatrix[2][1];
        keyMatrix[2][1] = temp;
    }

    //Known Plaintext Attack
    public void getKey(String plaintext,String ciphertext,int length){
        int[][] plainMatrix = new int[length][length];
        int[][] cipherMatrix = new int[length][length];
        int[][] keyMatrix = new int[length][length];

        int x =0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                plainMatrix[i][j] = plaintext.charAt(x++)-'a';
                cipherMatrix[i][j] = ciphertext.charAt(x++)-'a';
            }
        }
        matrixInverse(plainMatrix,length);
        multiplyTwoMatrix(plainMatrix,cipherMatrix,keyMatrix,length);

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(keyMatrix[i][j]+"  ");
            }
            System.out.println();
        }
    }
}
