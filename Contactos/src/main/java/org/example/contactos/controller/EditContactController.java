package org.example.contactos.controller;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.contactos.dao.ContactoDAOImp;
import org.example.contactos.model.Contact;
import org.example.contactos.util.AlertManager;
import org.example.contactos.util.WindowManager;

import javax.swing.plaf.ButtonUI;
import java.net.URL;
import java.util.ResourceBundle;

public class EditContactController implements Initializable,ControllerBase {


    @FXML
    private Button buttonCancelEdit;

    @FXML
    private Button buttonConfirmEdit;

    @FXML
    private TextField textFieldEditMail;

    @FXML
    private TextField textFieldEditName;

    @FXML
    private TextField textFieldEditNumber;

    @FXML
    private TextField textFieldEditSurname;

    private MainController mainController;
    private Contact contactToEdit;
    private AlertManager alertManager;
    private WindowManager windowManager;
    private ContactoDAOImp contactoDAOImp;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initGUI();
        instances();
        actions();
    }

    @Override
    public void initGUI() {
        if (contactToEdit != null){
            textFieldEditName.setText(contactToEdit.getName());

        }
    }

    @Override
    public void actions() {
        buttonCancelEdit.setOnAction(new ButtonManagement());
        buttonConfirmEdit.setOnAction(new ButtonManagement());
    }

    @Override
    public void instances() {
        alertManager = new AlertManager();
        windowManager = new WindowManager();
        contactoDAOImp = new ContactoDAOImp();
    }


    public void setMainController(MainController controller) {
        this.mainController = controller;
    }

    public void getContactToEdit (Contact contact){
        if (contact == null){{
            System.out.println("La tarea NO puede ser nula");}
        }
        this.contactToEdit = contact;

        //cargar los datos de el objeto de el otro controller aqui para que se acrage aa
        //la vez que estamos instanciando el objeto con el objeto que traemos de el otro controller


        showDataContactInTextFields();

    }
    public void showDataContactInTextFields (){
        // metodo para poder mostrar en los propios textfield los datos que hay en ese contacto

        if (contactToEdit.getName() !=null){
            textFieldEditName.setText(contactToEdit.getName());
        }

        if (contactToEdit.getSurname() != null){
            textFieldEditSurname.setText(contactToEdit.getSurname());
        }

        if (contactToEdit.getPhoneNumber() != null){
            textFieldEditNumber.setText(contactToEdit.phoneNumber);
        }
        if (contactToEdit.getEmail() != null){
            textFieldEditMail.setText(contactToEdit.getEmail());
        }
    }

    class ButtonManagement implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            if (actionEvent.getSource() == buttonConfirmEdit){

                // coges los datos que estas poniendo nuevos y LUEGO pones el metdoo para
                // que pueda coger los datos puestos en los textfields
                contactToEdit.setName(textFieldEditName.getText());
                contactToEdit.setSurname(textFieldEditSurname.getText());
                contactToEdit.setPhoneNumber(textFieldEditNumber.getText());
                contactToEdit.setEmail(textFieldEditMail.getText());

                mainController.editContact(contactToEdit);

            } else if (actionEvent.getSource() == buttonCancelEdit) {
                windowManager.closeWindow(buttonCancelEdit);
            }
        }
    }
}
