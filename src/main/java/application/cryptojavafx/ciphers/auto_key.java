package application.cryptojavafx.ciphers;

public class auto_key {
    public static String cipherFunction(String plaintext, String key, char cipherway){

        char[] Order = new char[plaintext.length()];
        String result = "";
        char res, FirstChar = 'A';
        int keyNo, keyLength = key.length();

        plaintext = plaintext.toUpperCase();
        key = key.toUpperCase();

        int j = 0;
        for (int i = 0; i < Order.length; i++) {
            if (i < key.length())
                Order[i] = key.charAt(i);
            else
                Order[i] = plaintext.charAt(j++);
        }
        if (cipherway == 'E') {
            for (int k = 0; k < plaintext.length(); k++) {
                    keyNo = Order[k];
                    res = (char) (FirstChar +((plaintext.charAt(k) + keyNo)%26));
                    result += res;
            }
        }
        else{
//            for (int a = 0; a < plaintext.length(); a+=keyLength) {
//                for (int l = a; l < plaintext.length() && l < key.length(); l++) {
//                        keyNo = key.charAt(l);
//                        keyNo = -keyNo + 26;
//                        res = (char) (FirstChar + ((plaintext.charAt(l) + keyNo) % 26));
//                        result += res;
//                }
//                key += result;
//            }
        }

        System.out.println(result);
        return result;
    }
}
