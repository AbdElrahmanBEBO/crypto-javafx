package Main;

import AES.AES_Encrypt;
import DES.DES_Encrypt;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception{
        Scanner scan = new Scanner(System.in);
        while(true) {
            System.out.println("\n                A SYMMETRIC CRYPTO SYSTEM");
            System.out.println("==================================================================");
            System.out.println("MAIN MENU \n---------------------");
            System.out.println("1. Encrypt \n2. Decrypt \n3. Exit \n---------------------");

            System.out.print("Enter your choice: ");
            int cipher = scan.nextInt();
            if (cipher == 3) break;

            System.out.println("(1) File (2) Folder");
            System.out.print("Enter your choice: ");
            int file_type = scan.nextInt();

            System.out.print("Name: ");
            String file_name = scan.next();

            System.out.print("Algorithm (AES, DES): ");
            String algorithm = scan.next();

            System.out.print("Key: ");
            String key = scan.next();

            System.out.println("==================================================================");
            System.out.println("Done! File " + file_name + " is encrypted using " + algorithm);
            System.out.println("Output is " + file_name + " is encrypted using " + algorithm);
            System.out.println("==================================================================\n\n");
        }
    }
}
