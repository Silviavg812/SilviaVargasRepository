package org.example.contactos.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import org.example.contactos.model.Contact;
import org.example.contactos.util.AlertManager;
import org.example.contactos.util.WindowManager;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmDeleteController implements Initializable,ControllerBase {
    @FXML
    private Button buttonCancelDelete;

    @FXML
    private Button buttonConfirmDelete;

    private MainController mainController;
    private Contact contactDelete;
    private WindowManager windowManager;
    private AlertManager alertManager;


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
        buttonConfirmDelete.setOnAction(new ButtonManagementDelete());
        buttonCancelDelete.setOnAction(new ButtonManagementDelete());


    }

    @Override
    public void instances() {
        windowManager = new WindowManager();
        alertManager = new AlertManager();
    }

    public void setMainController (MainController controller){
        this.mainController = controller;
    }




    // SI QUIERES PASAR UN OBJETO EN ESPECIFICO DE UN CONTROLLER A OTRO TIENES QUE HACERLO MEDIANTE METDOOS ????? CREO
    public void setContactDelete (Contact contact){
        if (contact == null){
            System.out.println("La tarea no puede ser null");
        }
        this.contactDelete = contact;
    }


    class ButtonManagementDelete implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            if (actionEvent.getSource() == buttonConfirmDelete){

                mainController.deleteContact(contactDelete);
                windowManager.closeWindow(buttonConfirmDelete);
                alertManager.informationAlert("Contacto eliminado correctamente"
                        ,String.format("Se ha eliminado correctamente el contacto %s",contactDelete.getName()));


            } else if (actionEvent.getSource() == buttonCancelDelete) {
                windowManager.closeWindow(buttonCancelDelete);
            }
        }
    }


}
