package application.cryptojavafx;

import java.io.FileReader;
import java.io.BufferedReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.awt.*;


public class controller implements Initializable {
    @FXML public ChoiceBox<String> cipherDropdown;
    @FXML public TextField key1, key2;
    @FXML public TextField plain_encryption_input, cipher_encryption_input, cipher_decryption_input, output_file;
    @FXML public TextField encryption_statement, decryption_statement, plain_attack_input;
    public File plainFile, cipherFile, cipherFileAfter, outputFile;
    @FXML public TextArea textareaField;

    public Ciphers[] ciphers = {
        new Ciphers(0, "wheel"),
        new Ciphers(1, "hill"),
        new Ciphers(2, "general caesar"),
        new Ciphers(3, "affine caesar"),
        new Ciphers(4, "mono alphabetic"),
        new Ciphers(5, "playfair"),
        new Ciphers(6, "vigenere"),
        new Ciphers(7, "autokey"),
        new Ciphers(8, "rail fence"),
        new Ciphers(9, "row transposition"),
    };

    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Ciphers c: ciphers) {
            cipherDropdown.getItems().add(c.id, c.name);
        }
    }
    void open_file(File file){
        try{
            Desktop desktop = Desktop.getDesktop();
            if(file.exists())
                desktop.open(file);
        } catch (Exception ex) {
            System.out.println("Something went wrong!");
        }
    }
    void browse_file(TextField tf, File f) {
        FileChooser FC = new FileChooser();
        f = FC.showOpenDialog(new Stage());
        tf.setText(f.getName());
    }

    @FXML void plain_encryption_open(ActionEvent event) {
        open_file(plainFile);
    }
    @FXML void cipher_encryption_open(ActionEvent event) {
        open_file(cipherFile);
    }
    @FXML void plain_decryption_open(ActionEvent event) {
        open_file(cipherFileAfter);
    }
    @FXML void cipher_decryption_open(ActionEvent event) {
        open_file(outputFile);
    }
    @FXML void plain_encryption_browse(ActionEvent event) {
        browse_file(plain_encryption_input, plainFile);
    }
    @FXML void cipher_encryption_browse(ActionEvent event) {
        browse_file(cipher_encryption_input, cipherFile);
    }
    @FXML void plain_decryption_browse(ActionEvent event) {
        browse_file(cipher_decryption_input, cipherFileAfter);
    }
    @FXML void cipher_decryption_browse(ActionEvent event) {
        browse_file(output_file, outputFile);
    }

    @FXML void EncryptStatement_Btn(ActionEvent event) {}

    @FXML void DecryptStatement_Btn(ActionEvent event) {}

    @FXML void Textarea_Btn(ActionEvent event) {
    }

    @FXML void Encryption_Btn(ActionEvent event) {
        System.out.println(cipherDropdown.getSelectionModel().getSelectedIndex());
        String[] keys = {key1.getText(), key2.getText()};
        encryption wc = new encryption(plainFile.getPath(), cipherFile.getPath(), keys, cipherDropdown.getSelectionModel().getSelectedIndex(), 'E');
        String text;
        try {
            BufferedReader Input = new BufferedReader(new FileReader(cipherFile.getPath()));
            while ((text = Input.readLine())!=null)
                textareaField.setText(text+"\n");
        }
        catch (Exception ex){
            System.out.println("Error");
        }
    }

    @FXML void Decryption_Btn(ActionEvent event) {
        String[] keys = {key1.getText(), key2.getText()};
        encryption wc = new encryption(cipherFileAfter.getPath(), outputFile.getPath(), keys, cipherDropdown.getSelectionModel().getSelectedIndex() ,'D');
        String text;
        try {
            BufferedReader Input = new BufferedReader(new FileReader(outputFile.getPath()));
            while ((text = Input.readLine())!=null)
                textareaField.setText(text+"\n");
        }
        catch (Exception ex){
            System.out.println("Error");
        }
    }

    @FXML void BruteForce_Btn(ActionEvent event) {
        bruteforce wc = new bruteforce(
                cipherFileAfter.getPath(),
                outputFile.getPath(), cipherDropdown.getValue() , 'D'
        );
    }

    @FXML void plaintextAttack_Btn(ActionEvent event) {
        String[] keys = {key1.getText(), key2.getText()};
        ptAttack_Main wc = new ptAttack_Main(plain_attack_input.getText(), cipherDropdown.getValue(),keys,'E');
    }
}

