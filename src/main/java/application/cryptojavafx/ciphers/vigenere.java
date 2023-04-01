package application.cryptojavafx.ciphers;

public class vigenere {

    public static String cipherFunction(String plaintext, String key, char cipherway){
        char[] Order = new char[plaintext.length()];
        String result = "";
        char res, FirstChar = 'A';
        int keyNo;

        plaintext = plaintext.toUpperCase();
        key = key.toUpperCase();

        for (int i = 0; i < Order.length; i++) {
            Order[i] = key.charAt(i%key.length());
        }


        for (int k = 0; k < plaintext.length(); k++) {
            if (plaintext.charAt(k) != ' ') {
                keyNo = Order[k];

                if (cipherway == 'D'){
                    keyNo = -keyNo + 26;
                }

                res = (char) (FirstChar +((plaintext.charAt(k) + keyNo)%26));
                result += res;
            }
            else
                result += " ";
        }
        System.out.println(result);
        return result;
    }
    public void getKey(String plaintext,String ciphertext){

    }
}
