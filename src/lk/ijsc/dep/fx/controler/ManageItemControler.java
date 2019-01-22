package lk.ijsc.dep.fx.controler;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijsc.dep.fx.Model.Items;
import lk.ijsc.dep.fx.Util.ItemTM;
import lk.ijsc.dep.fx.Util.ManageItems;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManageItemControler {

    public Button btnSaveItem;
    public Button btnDeleteItem;
    public Button btnHomeItem;
    public TableView manageItem;
    public TextField txtItemCode;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtQTY;
    public TableView <ItemTM>tableItem;


    public void initialize() {


        tableItem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tableItem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tableItem.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tableItem.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        btnSaveItem.setDisable(true);
        btnDeleteItem.setDisable(true);

        ArrayList<Items> itemsDB = ManageItems.getItemsDB();
        ObservableList<Items> items = FXCollections.observableArrayList(itemsDB);
        ObservableList<ItemTM> itemTMS = FXCollections.observableArrayList();
        for (Items item : items) {
            itemTMS.add(new ItemTM(item.getCode(), item.getDescription(), item.getUnitPrice(),item.getQtyOnHand()));
        }
        tableItem.setItems(itemTMS);

        tableItem.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ItemTM>() {
            @Override
            public void changed(ObservableValue<? extends ItemTM> observable, ItemTM oldValue, ItemTM selectedItem) {

                if (selectedItem == null) {
                    // Clear Selection
                    return;
                }



                txtItemCode.setText(selectedItem.getCode());
                txtDescription.setText(selectedItem.getDescription());
                txtUnitPrice.setText(selectedItem.getUnitPrice() + "");
                txtQTY.setText(selectedItem.getQtyOnHand() + "");

                txtItemCode.setEditable(false);

                btnSaveItem.setDisable(false);
                btnDeleteItem.setDisable(false);

            }







            });


    }

    public void btnsaveItemOnAction(ActionEvent actionEvent) {

        if (txtItemCode.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Item Code is empty", ButtonType.OK).showAndWait();
            txtItemCode.requestFocus();
            return;
        }else if(txtDescription.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Item Description is empty",ButtonType.OK).showAndWait();
            txtDescription.requestFocus();
            return;
        }else if(txtUnitPrice.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Unit Price is empty",ButtonType.OK).showAndWait();
            txtUnitPrice.requestFocus();
            return;
        }else if (txtQTY.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Qty On Hand is empty", ButtonType.OK).showAndWait();
            txtQTY.requestFocus();
            return;
        }else if(!isDouble(txtUnitPrice.getText()) || Double.parseDouble(txtUnitPrice.getText())< 0){
            new Alert(Alert.AlertType.ERROR,"Invalid Unit Price",ButtonType.OK).showAndWait();
            txtUnitPrice.requestFocus();
            return;
        }else if(!isInt(txtQTY.getText())){
            new Alert(Alert.AlertType.ERROR, "Invalid Qty", ButtonType.OK).showAndWait();
            txtQTY.requestFocus();
            return;
        }

        if (tableItem.getSelectionModel().isEmpty()) {
            // New

            ObservableList<ItemTM> items = tableItem.getItems();
            for (ItemTM itemTM : items) {
                if (itemTM.getCode().equals(txtItemCode.getText())){
                    new Alert(Alert.AlertType.ERROR,"Duplicate Item Codes are not allowed").showAndWait();
                    txtItemCode.requestFocus();
                    return;
                }
            }

            ItemTM itemTM = new ItemTM(txtItemCode.getText(), txtDescription.getText(),
                    Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQTY.getText()));
            tableItem.getItems().add(itemTM);
            Items item = new Items(txtItemCode.getText(), txtDescription.getText(),
                    Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQTY.getText()));
            ManageItems.createItem(item);

            new Alert(Alert.AlertType.INFORMATION, "Item has been saved successfully",ButtonType.OK).showAndWait();
            tableItem.scrollTo(itemTM);

        } else {
            // Update

            ItemTM selectedItem = tableItem.getSelectionModel().getSelectedItem();
            selectedItem.setDescription(txtDescription.getText());
            selectedItem.setUnitPrice(Double.parseDouble(txtUnitPrice.getText()));
            selectedItem.setQtyOnHand(Integer.parseInt(txtQTY.getText()));
            tableItem.refresh();

            int selectedRow = tableItem.getSelectionModel().getSelectedIndex();

            ManageItems.updateItem(selectedRow,new Items(txtItemCode.getText(), txtDescription.getText(),
                    Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQTY.getText())));

            new Alert(Alert.AlertType.INFORMATION,"Item has been updated successfully", ButtonType.OK).showAndWait();
        }

        reset();

    }

    public void btnDeleteItemOnAction(ActionEvent actionEvent) {
        Alert confirmMsg = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this item?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = confirmMsg.showAndWait();

        if (buttonType.get() == ButtonType.YES) {
            int selectedRow = tableItem.getSelectionModel().getSelectedIndex();

            tableItem.getItems().remove(tableItem.getSelectionModel().getSelectedItem());
            ManageItems.deleteItem(selectedRow);
            reset();
        }
    }


    private void reset() {
        txtItemCode.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQTY.clear();
        txtItemCode.requestFocus();
        txtItemCode.setEditable(true);
        btnSaveItem.setDisable(false);
        btnDeleteItem.setDisable(true);
        tableItem.getSelectionModel().clearSelection();
    }

    public void btnGoHOmeItem(ActionEvent actionEvent) {
    }

    public void btnNewItemOnAction(ActionEvent actionEvent) {
reset();

    }

    private boolean isInt(String number){
//        try {
//            Integer.parseInt(number);
//            return true;
//        }catch (NumberFormatException ex){
//            return false;
//        }
        char[] chars = number.toCharArray();
        for (char aChar : chars) {
            if (!Character.isDigit(aChar)){
                return false;
            }
        }
        return true;
    }

    private boolean isDouble(String number){
        try {
            Double.parseDouble(number);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
}























