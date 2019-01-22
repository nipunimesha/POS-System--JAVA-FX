package lk.ijsc.dep.fx.controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class loginControler {
    public Button btnLogin;
    public TextField txtUsername;
    public TextField txtPassword;
    public AnchorPane loginForm;

    public void btnOnActionLogin(ActionEvent actionEvent) throws IOException {
        String password=txtUsername.getText();
        String username=txtPassword.getText();
        if (txtUsername.getText().equals("system1")&&txtPassword.getText().equals("123")){
            Parent root= FXMLLoader.load(this.getClass().getResource("/lk/ijsc/dep/fx/view/MainForm.fxml"));
            Scene mainlogin=new Scene(root);
            Stage primary=(Stage)loginForm.getScene().getWindow();
            primary.setScene(mainlogin);

        }
}
    }
