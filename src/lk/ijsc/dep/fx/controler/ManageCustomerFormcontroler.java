package lk.ijsc.dep.fx.controler;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijsc.dep.fx.Model.Customer;
import lk.ijsc.dep.fx.Util.CustomerTM;
import lk.ijsc.dep.fx.Util.ItemTM;
import lk.ijsc.dep.fx.Util.ManageCustomer;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManageCustomerFormcontroler<T> {


    public AnchorPane managecustomer;
    public TableView <CustomerTM>tableCustomer;
    public TextField txtIdManageCus;
    public TextField txtnameManageCus;
    public TextField txtaddressManagecus;
    public Button btnsavemanageCus;
    public Button btndeleteManagecus;
    public Button btnNewCus;
    public Button btnHomeManagecus;
    public Button btnReport;

    public void initialize() {
        tableCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tableCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tableCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

        btnsavemanageCus.setDisable(true);
        btndeleteManagecus.setDisable(true);

        ArrayList<Customer> customersDB =ManageCustomer.getCustomersDB();
        ObservableList<Customer> customers = FXCollections.observableArrayList(customersDB);
        ObservableList<CustomerTM> tblItems = FXCollections.observableArrayList();
        for (Customer customer : customers) {
            tblItems.add(new CustomerTM(customer.getId(), customer.getName(), customer.getAddress()));
        }
        tableCustomer.setItems(tblItems);

        tableCustomer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustomerTM>() {
            @Override
            public void changed(ObservableValue<? extends CustomerTM> observable, CustomerTM oldValue, CustomerTM selectedCustomer) {

                if (selectedCustomer == null) {
                    // Clear Selection
                    return;
                }

                txtIdManageCus.setText(selectedCustomer.getId());
                txtnameManageCus.setText(selectedCustomer.getName());
                txtaddressManagecus.setText(selectedCustomer.getAddress());

                txtnameManageCus.setEditable(false);

                btnsavemanageCus.setDisable(false);
                btndeleteManagecus.setDisable(false);

            }
        });


    }




    public void btnsaveOnaction(ActionEvent actionEvent) {


        if (txtIdManageCus.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Customer ID is empty", ButtonType.OK).showAndWait();
            txtIdManageCus.requestFocus();
            return;
        }else if(txtnameManageCus.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Customer Name is empty",ButtonType.OK).showAndWait();
            txtnameManageCus.requestFocus();
            return;
        }else if(txtaddressManagecus.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Customer Address is empty",ButtonType.OK).showAndWait();
            txtaddressManagecus.requestFocus();
            return;
        }

        if (tableCustomer.getSelectionModel().isEmpty()) {
            // New

            ObservableList<CustomerTM> items = tableCustomer.getItems();
            for (CustomerTM customerTM : items) {
                if (customerTM.getId().equals(txtIdManageCus.getText())){
                    new Alert(Alert.AlertType.ERROR,"Duplicate Customer IDs are not allowed").showAndWait();
                    txtIdManageCus.requestFocus();
                    return;
                }
            }

            CustomerTM customerTM = new CustomerTM(txtIdManageCus.getText(), txtnameManageCus.getText(), txtaddressManagecus.getText());
            tableCustomer.getItems().add(customerTM);
            Customer customer = new Customer(txtIdManageCus.getText(), txtnameManageCus.getText(), txtaddressManagecus.getText());
            ManageCustomer.createCustomer(customer);

            new Alert(Alert.AlertType.INFORMATION, "Customer has been saved successfully",ButtonType.OK).showAndWait();
            tableCustomer.scrollTo(customerTM);

        } else {
            // Update

            CustomerTM selectedCustomer =tableCustomer.getSelectionModel().getSelectedItem();
            selectedCustomer.setName(txtnameManageCus.getText());
            selectedCustomer.setAddress(txtaddressManagecus.getText());
            tableCustomer.refresh();

            int selectedRow = tableCustomer.getSelectionModel().getSelectedIndex();

            ManageCustomer.updateCustomer(selectedRow,new Customer(txtIdManageCus.getText(),
                    txtnameManageCus.getText(),
                    txtaddressManagecus.getText()));

            new Alert(Alert.AlertType.INFORMATION,"Customer has been updated successfully", ButtonType.OK).showAndWait();
        }

        reset();

    }

    public void btnDeleteOnaction(ActionEvent actionEvent) {
        Alert confirmMsg = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this customer?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = confirmMsg.showAndWait();

        if (buttonType.get() == ButtonType.YES) {
            int selectedRow = tableCustomer.getSelectionModel().getSelectedIndex();

            tableCustomer.getItems().remove(tableCustomer.getSelectionModel().getSelectedItem());
            ManageCustomer.deleteCustomer(selectedRow);
            reset();
        }
    }

    public void btnNewCusonaction(ActionEvent actionEvent) {
        reset();
    }

    private void reset() {
        txtIdManageCus.clear();
        txtnameManageCus.clear();
        txtaddressManagecus.clear();
        txtIdManageCus.requestFocus();
        txtIdManageCus.setEditable(true);
        btnsavemanageCus.setDisable(false);
        btndeleteManagecus.setDisable(true);
        tableCustomer.getSelectionModel().clearSelection();
    }

    public void btngoHomeOnAction(ActionEvent actionEvent) throws IOException {
        Parent root= FXMLLoader.load(this.getClass().getResource("/lk/ijsc/dep/fx/view/MainForm.fxml"));
        Scene mainControler=new Scene(root);
        Stage primary=(Stage)managecustomer.getScene().getWindow();
        primary.setScene(mainControler);
    }


    public void btnReportOnAction(ActionEvent actionEvent) throws JRException {
        File file = new File("Report/PosCustomer.jasper");

        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);

        DefaultTableModel dtm=new DefaultTableModel(new Object[]{"Id","Name","Address"},0);
        ObservableList<CustomerTM> customer=tableCustomer.getItems();

        for (CustomerTM costomer : customer) {
            Object[] rowdata={costomer.getId(),costomer.getName(),costomer.getAddress()};
            dtm.addRow(rowdata);

        }




        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), new JRTableModelDataSource(dtm));
        JasperViewer.viewReport(jasperPrint);

    }
}
