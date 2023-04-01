package application.cryptojavafx.ciphers;

public class playfair {

    static char[][] matrixKey = new char[5][5];
    static int x,y,x1,y1,x2,y2,xJ,yJ;

    public static String cipherFunction(String plaintext, String key, char cipherway) {
        plaintext = plaintext.toUpperCase();
        key = key.toUpperCase();
        char Letters = 'A';
        String result = "";

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (Letters == 'J') {
                    Letters++;
                    xJ = i; yJ = j;
                }

                if (5*i+j < key.length())
                    matrixKey[i][j] = key.charAt(5*i+j);
                else {
                    if (!key.contains(Letters+"")){
                        matrixKey[i][j] = Letters;
                    }
                    else {
                        if (j==0){
                            --i; j=5;
                        }
                        else
                            j--;
                    }
                    Letters++;
                }
            }
        }

        //Print the Key
        for (int k = 0; k < 5; k++) {
            for (int m = 0; m < 5; m++) {
                System.out.print(matrixKey[k][m]+" ");
            }
            System.out.println();
        }

        if (plaintext.length()%2!=0)
            plaintext+="X";

        for (int k = 0; k < plaintext.length()-1; k+=2) {
            Position(plaintext.charAt(k));
            x1 = x; y1 = y;
            Position(plaintext.charAt(k+1));
            x2 = x; y2 = y;

            if (x1 == x2){
                result += (cipherway=='E')?
                        (matrixKey[x1][(y1+1)%5]+""+matrixKey[x1][(y2+1)%5]):(matrixKey[x1][chickReminder(y1)]+""+matrixKey[x1][chickReminder(y2)]);
            }
            else if(y1 == y2){
                result += (cipherway=='E')?
                        (matrixKey[(x1+1)%5][y1]+""+matrixKey[(x2+1)%5][y1]):(matrixKey[chickReminder(x1)][y1]+""+matrixKey[chickReminder(x2)][y1]);
            }
            else
                result += matrixKey[x1][y2]+""+matrixKey[x2][y1];
        }
        System.out.println(result);
        return result;
    }
    public static int chickReminder(int number){
        if (number == 0)
            number = 4;
        else
            number--;

        return number;
    }
    public static void Position(char letter){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(letter == 'J'){
                    if (yJ != 0) {
                        x = xJ;
                        y = yJ - 1;
                    }
                    else{
                        x = xJ-1;
                        y = 5;
                    }
                }
                else if (matrixKey[i][j] == letter){
                    x = i; y = j;
                }
            }
        }
    }
}
