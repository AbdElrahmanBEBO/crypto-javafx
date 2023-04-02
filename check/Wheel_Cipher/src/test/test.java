package test;

import java.util.Scanner;

public class test {


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(true)
            System.out.println(romanToInt(scan.next()));
    }
    public static int romanToInt(String s) {
        int x = 0;
        char first, second;

        while(s.length() > 1){
            first = s.charAt(0);
            second = s.charAt(1);

            if( (first == 'I' && (second == 'V' || second == 'X')) ||
                    (first == 'X' && (second == 'L' || second == 'C')) ||
                    (first == 'C' && (second == 'D' || second == 'M'))
            ) {
                x += checkChar(second) - checkChar(first);
                s = s.substring(2);
            }
            else {
                x += checkChar(first);
                s = s.substring(1);
            }
        }
        if(s.length() == 1)
            x = checkChar(s.charAt(0));

        return x;
    }
    public static int checkChar(char ch) {
        if(ch == 'I')
            return 1;
        else if(ch == 'V')
            return 5;
        else if(ch == 'X')
            return 10;
        else if(ch == 'L')
            return 50;
        else if(ch == 'C')
            return 100;
        else if(ch == 'D')
            return 500;

        return 1000;

    }
}
