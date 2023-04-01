package application.cryptojavafx.ciphers;

public class wheel {
    public static String cipherFunction(String plaintext, char key, char type){
        char firstChar = 'a';
        String result = "";
        int keyNo;

        for (int i = 0; i < plaintext.length(); i++) {

            keyNo = key - firstChar;
            //Check capital
            if (Character.isUpperCase(plaintext.charAt(i))){
                firstChar = Character.toUpperCase(firstChar);
                key = Character.toUpperCase(key);
            }
            else {
                firstChar = Character.toLowerCase(firstChar);
                key = Character.toLowerCase(key);
            }

            if (type == 'D')
                keyNo = -(key - firstChar);
            while (keyNo < 0)
                keyNo += 26;

            if (plaintext.charAt(i) != ' ') {
                int old = plaintext.charAt(i) - firstChar;
                char res = (char) (firstChar +((old +keyNo)%26));
                result += res;
            }
            else
                result += " ";
        }
        System.out.println(result);
        return result;
    }

}
