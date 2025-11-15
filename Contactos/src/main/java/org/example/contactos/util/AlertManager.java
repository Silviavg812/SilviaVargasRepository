package org.example.contactos.util;

import javafx.scene.control.Alert;

public class AlertManager {

    public Alert errorAlert (String titleMessage, String contentMessage){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titleMessage);
        alert.setContentText(contentMessage);
        alert.showAndWait();

        return alert;
    }
    public Alert confirmationAlert (String titleMessage, String contentMessage){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titleMessage);
        alert.setContentText(contentMessage);
        alert.showAndWait();

        return alert;
    }

    public Alert informationAlert (String titleMessage, String contentMessage){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleMessage);
        alert.setContentText(contentMessage);
        alert.showAndWait();

        return alert;
    }
    public Alert warningAlert (String titleMessage, String contentMessage){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titleMessage);
        alert.setContentText(contentMessage);
        alert.showAndWait();

        return alert;
    }

}
