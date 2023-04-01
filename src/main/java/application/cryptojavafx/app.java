package application.cryptojavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class app extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(app.class.getResource("ui.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600.0, 847.0);
        stage.setTitle("Crypto");
        stage.setScene(scene);
        stage.show();
    }
}