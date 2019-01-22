package lk.ijsc.dep.fx.controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormControler {


    public Button btnManageCustomer;
    public Button btnManageitem;
    public Button btnplaceOrder;

    public Button btnSettings;
    public Label mainform;
    public Button btnSearchOrder;

    public void btnmanageCusOnaction(ActionEvent actionEvent) throws IOException {
        Parent root= FXMLLoader.load(this.getClass().getResource("/lk/ijsc/dep/fx/view/ManageCustomerForm.fxml"));
        Scene mainControler=new Scene(root);
        Stage primary=(Stage)mainform.getScene().getWindow();
        primary.setScene(mainControler);
    }

    public void btnmangeItemOnaction(ActionEvent actionEvent) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijsc/dep/fx/view/ManageItemForm.fxml"));
        Scene mainControler=new Scene(root);
        Stage primary=(Stage)mainform.getScene().getWindow();
        primary.setScene(mainControler);
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijsc/dep/fx/view/PlaceOrderForm.fxml"));
        Scene mainControler=new Scene(root);
        Stage primary=(Stage)mainform.getScene().getWindow();
        primary.setScene(mainControler);
    }



    public void btnSettingsOnAction(ActionEvent actionEvent) {
    }

    public void btnsearchOrderOnaction(ActionEvent actionEvent) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijsc/dep/fx/View/PlaceOrderForm.fxml"));
        Scene mainControler=new Scene(root);
        Stage primary=(Stage)mainform.getScene().getWindow();
        primary.setScene(mainControler);
    }
}
