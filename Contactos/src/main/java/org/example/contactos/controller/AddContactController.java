package org.example.contactos.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.example.contactos.model.Contact;
import org.example.contactos.util.AlertManager;
import org.example.contactos.util.WindowManager;
import java.net.URL;
import java.util.ResourceBundle;

public class AddContactController implements Initializable,ControllerBase{


    // FXML -> add-contact-view.fxml

    // VARIABLES OF FXML ///////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    private Button buttonCancelAdd;

    @FXML
    private Button buttonConfirmAdd;

    @FXML
    private TextField textFieldMail;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldNumber;

    @FXML
    private TextField textFieldSurname;
    /// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private MainController mainController;
    private WindowManager windowManager;
    private AlertManager alertManager;


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instances();
        initGUI();
        actions();
    }

    @Override
    public void initGUI() {

    }

    @Override
    public void actions() {
        buttonConfirmAdd.setOnAction(new ButtonManagement());
        buttonCancelAdd.setOnAction(new ButtonManagement());

        // Cunado le das a entre se pasa de textField
        KeyboardManagement keyboardManagement = new KeyboardManagement();
        keyboardManagement.enterMechanic(textFieldName,textFieldSurname);
        keyboardManagement.enterMechanic(textFieldSurname,textFieldNumber);
        keyboardManagement.enterMechanic(textFieldNumber,textFieldMail);
        textFieldMail.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER){
                buttonConfirmAdd.fire(); // hace que se ejecute directamente la logica que teiens puesta para el boton que pones
            }
        });




    }

    @Override
    public void instances() {
        alertManager = new AlertManager();
        windowManager = new WindowManager();
    }

    /// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setMainController (MainController controller){
        this.mainController = controller;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    class KeyboardManagement implements EventHandler<KeyEvent>{
        @Override
        public void handle(KeyEvent keyEvent) {
        }
        public void enterMechanic (TextField oldTextField, TextField nextTextField){
            oldTextField.setOnKeyPressed(event ->{
                if (event.getCode() == KeyCode.ENTER){
                    nextTextField.requestFocus();
                    event.consume(); // hace que no se propague el evento
                }
            });
        }
    }


    class ButtonManagement implements EventHandler<ActionEvent> {


        @Override
        public void handle(ActionEvent actionEvent) {

            if (actionEvent.getSource() == buttonConfirmAdd){

                String name = textFieldName.getText();
                String surname = textFieldSurname.getText();
                String phoneNumber = textFieldNumber.getText();
                String mail = textFieldMail.getText();

                if (!textFieldName.getText().isEmpty() && !textFieldNumber.getText().isEmpty() ){
                    Contact contact = new Contact(name,surname,phoneNumber,mail);
                    if (!mainController.searchName(contact.getName())){
                        mainController.addContact(contact);
                        windowManager.closeWindow(buttonConfirmAdd);
                        alertManager.confirmationAlert("Nuevo contacto agregado"
                                , String.format("El contacto de %s fue agregado correctamente.",contact.getName()));

                    } else {
                        alertManager.warningAlert("Contacto con nombre ya existente"
                                ,String.format("No se puede agreagra este contacto porque %s ya esta agregado a tu lista de contactos",name));
                    }

                } else {
                    alertManager.warningAlert("Falta datos en los campos"
                            ,"Introduce datos en los campos obligatorios");
                }



            } else if (actionEvent.getSource() == buttonCancelAdd) {
                windowManager.closeWindow(buttonCancelAdd);

            }



        }
    }
}
