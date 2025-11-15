package org.example.contactos.util;

import javafx.scene.Node;
import javafx.stage.Stage;

public class WindowManager {

    public void closeWindow (Node node){
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}
