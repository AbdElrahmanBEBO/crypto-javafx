package application.cryptojavafx;

import application.cryptojavafx.ciphers.hill;
import application.cryptojavafx.ciphers.vigenere;

public class ptAttack_Main {
    public ptAttack_Main(String plaintext,String cipherType,String[] key,char cipherway){
        String ciphertext;
        if (cipherType == "Vigenere") {
            vigenere cipherAlgo = new vigenere();
            ciphertext = cipherAlgo.cipherFunction(plaintext,key[0],cipherway);
            cipherAlgo.getKey(plaintext,ciphertext);
        }
        else if(cipherType == "Hill Cipher"){
            hill cipherAlgo = new hill();
            ciphertext = cipherAlgo.cipherFunction(plaintext,key[0],cipherway);
            cipherAlgo.getKey(plaintext,ciphertext,Integer.parseInt(key[1]));
        }

    }
}
