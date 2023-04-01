package application.cryptojavafx;
import application.cryptojavafx.ciphers.general_caeser;
import application.cryptojavafx.ciphers.affine_caeser;
import application.cryptojavafx.ciphers.wheel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class bruteforce {
    public bruteforce(String plaintextPath, String ciphertextPath, String TypeEn, char cipherway){
        String ciphertext;
        try{
            BufferedReader Input = new BufferedReader(new FileReader(plaintextPath));
            BufferedWriter Output = new BufferedWriter(new FileWriter(ciphertextPath));
            while ( (ciphertext = Input.readLine()) != null){

                // Type of Cipher Algorithm
                if (TypeEn == "Wheel Cipher") {
                    wheel cipherAlgo = new wheel();
                    char key = 'a';
                    for (int i = 0; i < 26; i++) {
                        Output.write("KEY["+(char)key+"] ----------");
                        Output.newLine();
                        Output.write("\t"+cipherAlgo.cipherFunction(ciphertext,key++,cipherway));
                        Output.newLine();
                    }
                }
                else if (TypeEn == "General Caeser"){
                    general_caeser cipherAlgo = new general_caeser();
                    for (int i = 0; i < 26; i++) {
                        Output.write("KEY["+i+"] ----------");
                        Output.newLine();
                        Output.write("\t"+cipherAlgo.cipherFunction(ciphertext,i, cipherway));
                        Output.newLine();
                    }
                }
                else if (TypeEn == "Affine Caeser"){
                    affine_caeser cipherAlgo = new affine_caeser();
                    for (int i = 0; i < 26; i++) {
                        for (int j = 0; j < 26; j++) {
                            Output.write("KEY[" + i + "][" + j + "] ----------");
                            Output.newLine();
                            Output.write("\t" + cipherAlgo.cipherFunction(ciphertext, i, j, cipherway));
                            Output.newLine();
                        }
                    }
                }
                Output.newLine();
            }
            Input.close();
            Output.close();
        }
        catch (Exception ex){
            System.out.println("There is an Error in File");
        }
    }
}
