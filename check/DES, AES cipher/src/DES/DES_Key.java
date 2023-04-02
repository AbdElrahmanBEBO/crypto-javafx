package DES;

import java.io.*;
import java.util.Scanner;

public class DES_Key {

    private final int noOfSubKeys = 16;

    public String[] desKey = new String[noOfSubKeys];

    //Permuted Choice One Array
    private int[] pc_1 = {
            57, 49, 41, 33, 25, 17,  9,
             1, 58, 50, 42, 34, 26, 18,
            10,  2, 59, 51, 43, 35, 27,
            19, 11,  3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
             7, 62, 54, 46, 38, 30, 22,
            14,  6, 61, 53, 45, 37, 29,
            21, 13,  5, 28, 20, 12,  4
    };

    //Permuted Choice Two Array
    private int[] pc_2 = {
            14, 17, 11, 24,  1,  5,  3, 28,
            15,  6, 21, 10, 23, 19, 12,  4,
            26,  8, 16,  7, 27, 20, 13,  2,
            41, 52, 31, 37, 47, 55, 30, 40,
            51, 45, 33, 48, 44, 49, 39, 56,
            34, 53, 46, 42, 50, 36, 29, 32
    };

    //Schedule of Left Shifts for 16 round
    private int[] leftShifts = {
            1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1
    };

    private String temp = "", binaryNumber = "", binaryAfterPC1 = "", leftKey, rightKey;

    public DES_Key(File keyFile) throws Exception{
        Scanner readKeyFile = new Scanner(keyFile);
        String keyString = readKeyFile.nextLine();
        int hexadecimalNumber;

        for (int i = 0; i < keyString.length(); i++) {
            hexadecimalNumber = Integer.parseInt(Character.toString(keyString.charAt(i)),16);
            temp = Integer.toBinaryString(hexadecimalNumber);

            while (temp.length()<4)
                temp = "0" + temp;

            binaryNumber += temp;
        }

        for (int i = 0; i < pc_1.length; i++)
            binaryAfterPC1 += Character.toString(binaryNumber.charAt(pc_1[i]-1));

        leftKey = binaryAfterPC1.substring(0,binaryAfterPC1.length()/2);
        rightKey = binaryAfterPC1.substring(binaryAfterPC1.length()/2);

        String fileDir = System.getProperty("user.dir") + "\\16keysFile.txt";
        File _16keysFile = new File(fileDir);

        if(!_16keysFile.exists())
            _16keysFile.createNewFile();

        PrintWriter writer = new PrintWriter(_16keysFile);
        for (int i = 0; i < noOfSubKeys; i++) {
            writer.println("KEY[" + (i+1) + "]");
            desKey[i] = leftShiftsMethod(leftShifts[i]);
            writer.println("  -->>  " + binaryToHexadecimal(desKey[i]));
        }
        writer.flush();
        writer.close();
    }

    private String leftShiftsMethod(int numOfShifts){
        String result = "";
        leftKey = leftKey.substring(numOfShifts) + leftKey.substring(0,numOfShifts);
        rightKey = rightKey.substring(numOfShifts) + rightKey.substring(0,numOfShifts);

        String afterShifting = leftKey + rightKey ;
        for (int i = 0; i < pc_2.length; i++)
            result += Character.toString(afterShifting.charAt(pc_2[i]-1));
        return result;
    }

    private String binaryToHexadecimal(String Binary){
        String hexadecimalNumber = "";
        int binaryNumber;

        for (int i = 0; i < Binary.length(); i+=4) {
            binaryNumber = Integer.parseInt(Binary.substring(i,i+4),2);
            temp = Integer.toHexString(binaryNumber);

            hexadecimalNumber += temp;
        }
        return hexadecimalNumber;
    }
}
