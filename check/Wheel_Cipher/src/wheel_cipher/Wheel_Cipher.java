package wheel_cipher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Wheel_Cipher {

    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        String dataInput;

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter The Key: ");
        String key = scan.next();

        try{
            BufferedReader Input = new BufferedReader(new FileReader(dir+"\\input.txt"));
            BufferedWriter Output = new BufferedWriter(new FileWriter(dir+"\\output.txt"));

            while ( (dataInput = Input.readLine()) != null){
                Output.write(wheelCipher(dataInput,key.charAt(0),'a'));
                Output.newLine();
            }

            Input.close();
            Output.close();
        }
        catch (Exception ex){
            System.out.println("There is an Error in File");
        }
    }
    public static String wheelCipher(String dataLine, char key, char firstChar){

        String result = "";
        int keyNo;

        for (int i = 0; i < dataLine.length(); i++) {

            //Check Capitale
            if (Character.isUpperCase(dataLine.charAt(i))){
                firstChar = Character.toUpperCase(firstChar);
                key = Character.toUpperCase(key);
            }
            else {
                firstChar = Character.toLowerCase(firstChar);
                key = Character.toLowerCase(key);
            }

            keyNo = key - firstChar;
            if (dataLine.charAt(i) != ' ') {
                int old = dataLine.charAt(i) - firstChar;
                char res = (char) (firstChar +((old + keyNo)%26));
                result += res;
            }
            else
                result += " ";
        }
        return result;
    }
}
