package lk.ijsc.dep.fx.controler;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijsc.dep.fx.Model.Customer;
import lk.ijsc.dep.fx.Model.Items;
import lk.ijsc.dep.fx.Model.Order;
import lk.ijsc.dep.fx.Model.OrderDetail;
import lk.ijsc.dep.fx.Util.ManageCustomer;
import lk.ijsc.dep.fx.Util.ManageItems;
import lk.ijsc.dep.fx.Util.ManageOrders;
import lk.ijsc.dep.fx.Util.OrderDetailTM;

import java.time.LocalDate;
import java.util.ArrayList;

import static sun.plugin.ClassLoaderInfo.reset;

public class PlaceOrderFormControler<T> {

    public AnchorPane placeorderform;
    public Button btnPlaceOrderPlace;
    public Button btnSavePlace;
    public Button btnRemovePlace;
    public TextField txtCusnamePlace;
    public TextField txtDescriptionPlace;
    public TextField txtUnitpricePlace;
    public TextField txtOrderIdPlace;
    public TextField txtCustomerIdPlace;
    public TextField txtItemCodePlace;
    public TextField txtQtyOnhandPlace;
    public TableView<OrderDetailTM> tableOrderPlace;
    public TextField txtOrderDatePlace;
    public TextField txtQtyPlace;


    public void initialize() {

        tableOrderPlace.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tableOrderPlace.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tableOrderPlace.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tableOrderPlace.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tableOrderPlace.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));



        }





    public void btnSavePlaceOnAction(ActionEvent actionEvent) {

    }



    public void btnRemoveOnActionPlace(ActionEvent actionEvent) {

    }



    public void txtCustomerIdOnAction(ActionEvent actionEvent) {
     //   Items items = ManageItems.findItem(txt)
            // Customer customer=
        Customer customer = ManageCustomer.findCustomer(txtCustomerIdPlace.getText().trim());
        if (customer==null){
            new Alert(Alert.AlertType.ERROR,"coustomer field is null",ButtonType.OK).show();

        }


    }




    public void txtItemCodeOnAction(ActionEvent actionEvent) {

    }

    public void txtQtyOnAction(ActionEvent actionEvent) {

        }


        public boolean isInt (String number){

        return false;
        }

        public Items getItemFromTempDB (String itemCode){
        return null;
        }

        private void showInvaliddataMsgBox (String message){
            new Alert(Alert.AlertType.ERROR, message, ButtonType.OK).showAndWait();
            txtQtyPlace.requestFocus();
            txtQtyPlace.selectAll();
        }

        private OrderDetailTM isItemExist (String itemCode){
            ObservableList <OrderDetailTM> items = tableOrderPlace.getItems();
            for (OrderDetailTM item : items) {
                if (item.getCode().equals(itemCode)) {
                    return item;
                }
            }
            return null;
        }

        public void reset () {
            tableOrderPlace.refresh();
            txtItemCodePlace.clear();
            txtDescriptionPlace.clear();
            txtQtyPlace.clear();
            txtQtyOnhandPlace.clear();
            txtUnitpricePlace.clear();
            txtItemCodePlace.setEditable(true);
            btnRemovePlace.setDisable(true);
            tableOrderPlace.getSelectionModel().clearSelection();
            txtItemCodePlace.requestFocus();
        }

        private void setTempQty (String itemCode,int qty){

        }

        private void synchronizeQty (String code) {
        }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {

    }
}


