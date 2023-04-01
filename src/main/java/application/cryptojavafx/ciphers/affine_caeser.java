package application.cryptojavafx.ciphers;

public class affine_caeser {
    public static String cipherFunction(String plaintext, int key1 , int key2, char cipherway){
        int R, old, Inverse_Key1 = keyInverse(key1,26);
        char firstChar = 'a';
        String result = "";

        for (int i = 0; i < plaintext.length(); i++) {
            firstChar = (Character.isUpperCase(plaintext.charAt(i)))?
                Character.toUpperCase(firstChar):Character.toLowerCase(firstChar);

            if (plaintext.charAt(i) != ' ') {
                old = plaintext.charAt(i) - firstChar;

                R = ((old-key2)*Inverse_Key1);
                while (R<0)
                    R+=26;

                result += (char)(firstChar + ((cipherway=='E')?((key1*old)+key2): R) %26);
            }
            else
                result += " ";
        }
        System.out.println(result);
        return result;
    }

    public static int keyInverse(int key,int mod){
        for (int i = 1; i < mod; i++) {
            if ((i*key)%mod == 1){
                return i;
            }
        }
        return -1;
    }
}
