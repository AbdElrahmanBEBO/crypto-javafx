package application.cryptojavafx.ciphers;

public class mono_alphabetic {

    static char[] Order = new char[26];

    public static String cipherFunction(String plaintext, String key, char cipherway){

        String result = "";
        char FirstChar = 'A', Alphbitic = 'A';
        int keyNo, i, j;

        plaintext = plaintext.toUpperCase();
        key = key.toUpperCase();

        for (j = 0; j < key.length(); j++) {
            Order[j] = key.charAt(j);
        }
        i = j;
        while (i<Order.length){
            if (! key.contains(Alphbitic+"")){
                Order[i] = Alphbitic;
                i++;
            }
            Alphbitic++;
        }

        for (int k = 0; k < plaintext.length(); k++) {
            if (plaintext.charAt(k) != ' ') {

                if (cipherway == 'E')
                    keyNo = Order[plaintext.charAt(k)-FirstChar];
                else
                    keyNo = decrytionMap(plaintext.charAt(k))+FirstChar;

                result += (char)keyNo;
            }
            else
                result += " ";
        }
        System.out.println(result);
        return result;
    }
    public static int decrytionMap(char text){
        for (int l = 0; l < Order.length; l++) {
            if (Order[l] == text){
                return l;
            }
        }
        return -6;
    }
}
