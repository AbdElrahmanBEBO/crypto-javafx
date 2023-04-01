package application.cryptojavafx.ciphers;

public class general_caeser {
    public static String cipherFunction(String plaintext, int key, char cipherway){
        char firstChar = 'a';
        String result = "";

        if (cipherway == 'D')
            key = -key;

        while (key < 0)
            key += 26;

        for (int i = 0; i < plaintext.length(); i++) {

            //Check Capitale
            if (Character.isUpperCase(plaintext.charAt(i)))
                firstChar = Character.toUpperCase(firstChar);
            else
                firstChar = Character.toLowerCase(firstChar);

            if (plaintext.charAt(i) != ' ') {
                int old = plaintext.charAt(i) - firstChar;
                char res = (char) (firstChar +((old + key)%26));
                result += res;
            }
            else
                result += " ";
        }
        System.out.println(result);
        return result;
    }
}
