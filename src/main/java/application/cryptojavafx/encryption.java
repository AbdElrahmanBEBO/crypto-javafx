package application.cryptojavafx;
import application.cryptojavafx.ciphers.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class encryption {
    public encryption(String plaintextPath, String ciphertextPath, String[] key, int TypeEn, char cipherway){
        String plaintext;
        try{
            BufferedReader Input = new BufferedReader(new FileReader(plaintextPath));
            BufferedWriter Output = new BufferedWriter(new FileWriter(ciphertextPath));
            while ( (plaintext = Input.readLine()) != null){

                // Type of Cipher Algorithm
                if (TypeEn == 1) {
                    wheel cipherAlgo = new wheel();
                    Output.write(cipherAlgo.cipherFunction(plaintext, key[0].charAt(0), cipherway));
                }
                else if (TypeEn == 2){
                    general_caeser cipherAlgo = new general_caeser();
                    Output.write(cipherAlgo.cipherFunction(plaintext,Integer.parseInt(key[0]), cipherway));
                }
//                else if (TypeEn == "Affine Caeser"){
//                    affine_caeser cipherAlgo = new affine_caeser();
//                    Output.write(cipherAlgo.cipherFunction(plaintext, Integer.parseInt(key[0]), Integer.parseInt(key[1]),cipherway));
//                }
//                else if (TypeEn == "Mono Alphabetic"){
//                    mono_alphabetic cipherAlgo = new mono_alphabetic();
//                    Output.write(cipherAlgo.cipherFunction(plaintext,key[0],cipherway));
//                }
//                else if (TypeEn == "Playfair Cipher"){
//                    playfair cipherAlgo = new playfair();
//                    Output.write(cipherAlgo.cipherFunction(plaintext,key[0],cipherway));
//                }
//                else if (TypeEn == "Vigenere") {
//                    vigenere cipherAlgo = new vigenere();
//                    Output.write(cipherAlgo.cipherFunction(plaintext,key[0],cipherway));
//                }
//                else if (TypeEn == "AutoKey") {
//                    auto_key cipherAlgo = new auto_key();
//                    Output.write(cipherAlgo.cipherFunction(plaintext,key[0],cipherway));
//
//                }
//                else if(TypeEn == "Rail Fence"){
//                    rail_fence cipherAlgo = new rail_fence();
//                    Output.write(cipherAlgo.cipherFunction(plaintext,Integer.parseInt(key[0]),cipherway));
//                }
//                else if(TypeEn == "Row Transposition"){
//                    row_transposition cipherAlgo = new row_transposition();
//                    Output.write(cipherAlgo.cipherFunction(plaintext,key[0],Integer.parseInt(key[1]),cipherway));
//                }
//                else if(TypeEn == "Hill Cipher"){
//                    hill cipherAlgo = new hill();
//                    Output.write(cipherAlgo.cipherFunction(plaintext,key[0],cipherway));
//                }
                Output.newLine();
            }
            Input.close();
            Output.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
            System.out.println("There is an Error in File");
        }
    }

}
