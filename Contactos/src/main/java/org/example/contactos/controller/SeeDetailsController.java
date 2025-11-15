package org.example.contactos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.contactos.model.Contact;

import java.net.URL;
import java.util.ResourceBundle;

public class SeeDetailsController implements Initializable,ControllerBase {


    @FXML
    private Label labelNameDetails;

    @FXML
    private TableColumn<?, ?> tableColumnDetailsEmail;

    @FXML
    private TableColumn<?, ?> tableColumnDetailsID;

    @FXML
    private TableColumn<?, ?> tableColumnDetailsName;

    @FXML
    private TableColumn<?, ?> tableColumnDetailsPhoneNumber;

    @FXML
    // En un tableView se
    private TableColumn<?,?> tableColumnDetailsSurname;

    @FXML
    private TableView<Contact> tableViewSeeDetails;

    private MainController mainController;
    private Contact contactToSeeDetails;
    private ObservableList<Contact> listaContact;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initGUI();
        instances();
        actions();
    }

    @Override
    public void initGUI() {
        tableViewSeeDetails.setItems(listaContact);
    }

    @Override
    public void actions() {
        labelNameDetails.setText(this.contactToSeeDetails.getName());
    }

    @Override
    public void instances() {
        contactToSeeDetails = new Contact();
        listaContact = FXCollections.observableArrayList();
        listaContact.add(this.contactToSeeDetails);
        if (contactToSeeDetails != null){
            tableViewSeeDetails.setItems(listaContact);

        } else {
            System.out.println("El contacto pasado en nulo");
        }
    }
    public void setMainController (MainController controller){
        this.mainController = controller;
    }
    public void getContactDetails(Contact contact){
        this.contactToSeeDetails = contact;
    }
}
