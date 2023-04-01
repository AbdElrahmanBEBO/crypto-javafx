module application.cryptojavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.datatransfer;
    requires java.desktop;

    opens application.cryptojavafx to javafx.fxml;
    exports application.cryptojavafx;
    exports application.cryptojavafx.ciphers;
    opens application.cryptojavafx.ciphers to javafx.fxml;
}