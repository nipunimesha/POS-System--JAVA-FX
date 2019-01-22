package lk.ijsc.dep.fx.Main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Appinitializer extends Application {
    public Button btnLogin;
    public TextField txtUsername;
    public TextField txtPassword;

    public static void main(String[] args) {
        launch(args);



    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijsc/dep/fx/View/MainForm.fxml"));

        Scene mainScene = new Scene(root);

        primaryStage.setTitle("IJSE FX POS - In Memory DB");
        primaryStage.setScene(mainScene);
        primaryStage.setResizable(false);

        primaryStage.show();

    }


}
