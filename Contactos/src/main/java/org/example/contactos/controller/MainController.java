package org.example.contactos.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.example.contactos.HelloApplication;
import org.example.contactos.dao.ContactoDAOImp;
import org.example.contactos.model.Contact;
import org.example.contactos.util.AlertManager;
import org.example.contactos.util.WindowManager;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable,ControllerBase {

    // VARUIABLES
    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonReload;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonDetails;

    @FXML
    private Button buttonEdit;

    @FXML
    private Label labelNumberContacts;

    @FXML
    private ListView<Contact> listViewContacts;

    @FXML
    private TextField textFieldSearchBar;
    
    // VARIBLES OF CLASS
    private ObservableList<Contact> observableListContacts;
    private ContactoDAOImp contactoDAO;
    private AlertManager alertManager;
    private WindowManager windowManager;
    private ConfirmDeleteController confirmDeleteController;
    private int contadorContactos;

    private SeeDetailsController seeDetailsController;

    /// ////////////////////////////////////////////////////////////////////////////


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instances();
        setUpSearch();
        initGUI();
        actions();
    }

    @Override
    public void initGUI() {
        listViewContacts.setItems(observableListContacts);
        labelNumberContacts.setText(String.valueOf(contadorContactos));

    }

    @Override
    public void actions() {
        buttonAdd.setOnAction(new ManejoBotones());
        buttonDelete.setOnAction(new ManejoBotones());
        buttonEdit.setOnAction(new ManejoBotones());
        buttonDetails.setOnAction(new ManejoBotones());

        // cuando tengas un elemento y quieras eliminarlo puedes darle a el boton eliminar directamente lol
        listViewContacts.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.BACK_SPACE &&
                    listViewContacts.getSelectionModel().getSelectedIndex() != -1) {
                buttonDelete.fire();
            }
        });

    }

    @Override
    public void instances() {
        // cuando quieras hacer una conexion inmediata desde la base de datos asegurate de que la conexion esta creada en el constructor
        // del DAO porque sino te va a dar error y asegurate de meterlo en la lista.
        contactoDAO = new ContactoDAOImp();
        alertManager = new AlertManager(); // tienes que iniciar el alert manager para que pueda funcionar
        windowManager = new WindowManager();
        observableListContacts = FXCollections.observableArrayList(contactoDAO.obtainContact()); // para poder agragarla de la base de datos
        // IMPORTANTE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!    BASE DE DATOS ASIGNADA CON LA LISTA
        listViewContacts.setItems(observableListContacts); // ya aqui se pone en la listview
        contadorContactos = observableListContacts.size(); // ya que esta conectada a la base de datos siempre se actualiza
    }
    
    public void addContact (Contact contact){
        try {
            if (!searchName(contact.getName())){
                observableListContacts.add(contact);
                contactoDAO.addContact(contact);
                labelNumberContacts.setText(String.valueOf(observableListContacts.size()));


                // no deberia de estar aqui la alerta habria que pasar el buscar

            } else {
                alertManager.errorAlert("No se puede añadir contacto","Ya existe un contacto con ese nombre");
            }
        }catch (SQLException e){
            alertManager.errorAlert("Error añadiendo contacto", "No se pudo añadir el contacto");
        }
    }
    public void deleteContact (Contact contact){
        // un contacto se puede eliminar pulsandolo en el listView
        contactoDAO.deleteContact(contact.getName());
        observableListContacts.remove(contact);
        labelNumberContacts.setText(String.valueOf(observableListContacts.size()));

                // para la base de datos a lo mejor si que tienes que poner un tipo de dato



    }

    public void editContact (Contact contact){

        contactoDAO.getContactToEditInBBDD(contact);
        contactoDAO.editContact(contact);
        listViewContacts.refresh();
    }


    public boolean searchName (String name){
        for (Contact item : observableListContacts) {
            if (item.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    // SEARCH BAR //////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setUpSearch(){
        textFieldSearchBar.textProperty().addListener((observable,oldvalue,newValue) -> {
            searchContact(newValue);
        } );
        textFieldSearchBar.setPromptText("Buscar contacto...");
    }
    public void searchContact (String searchText){

        if (searchText == null || searchText.isEmpty()){
            listViewContacts.setItems(observableListContacts);
            return;
        }

        ObservableList<Contact> filtered = observableListContacts.filtered(contact ->
                contact.getName().contains(searchText) ||
                contact.getSurname().contains(searchText) ||
                contact.getEmail().contains(searchText) ||
                contact.getPhoneNumber().contains(searchText)
        );

        listViewContacts.setItems(filtered);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class ManejoBotones implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            if (actionEvent.getSource() == buttonAdd){

                Stage windowAdd = new Stage();
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("add-contact-view.fxml"));
                try {
                    Parent root = loader.load();
                    AddContactController addContactController = loader.getController();
                    addContactController.setMainController(MainController.this);
                    Scene scene = new Scene(root);
                    windowAdd.setScene(scene);
                    windowAdd.setTitle("Agregar nuevo contacto");
                    windowAdd.show();

                } catch (IOException e){
                    System.out.println(e.getMessage());
                }




            } else if (actionEvent.getSource() == buttonEdit) {


                if (listViewContacts.getSelectionModel().getSelectedIndex() != -1){

                    Stage windowEditContact = new Stage();
                    FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("edit-contact-view.fxml"));

                    try {
                        Parent root = loader.load();
                        EditContactController editContactController = loader.getController();
                        editContactController.setMainController(MainController.this);
                        Scene scene = new Scene(root);

                        // TODO hacer que se edite bien la puta tarea de los cojones dio que mal
                        windowEditContact.setScene(scene);
                        windowEditContact.setTitle("Edicion de contacto");
                        editContactController.getContactToEdit(listViewContacts.getSelectionModel().getSelectedItem());
                        windowEditContact.show();


                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    alertManager.warningAlert("Nada seleccionado"
                            ,"No se eligio ningun contacto para editar");
                }


            } else if (actionEvent.getSource() == buttonDelete) {
                if (listViewContacts.getSelectionModel().getSelectedIndex() != -1){

                    Stage windowDeleteConfirmation = new Stage();
                    FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("delete-confirmation-view.fxml"));

                    try {

                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        ConfirmDeleteController confirmDeleteController = loader.getController();
                        confirmDeleteController.setMainController(MainController.this);
                        windowDeleteConfirmation.setScene(scene);

                        // AQUI ESTAS ENVIANDO A EL OTRO CONTROLLER EL OBJETO QUE QUEIRES ELIMINAR PARA PODER ELIMINARLO MEDIANTE LA LOGICA DE CONFIRMACION
                        confirmDeleteController.setContactDelete(listViewContacts.getSelectionModel().getSelectedItem());
                        windowDeleteConfirmation.show();

                    } catch (Exception e) {
                        throw new RuntimeException(e);

                    }
                } else {
                    alertManager.informationAlert("No se selecciono ningun contacto"
                            ,"No se pudo eliminar el contacto porque no hay ningun contacto seleccionado");
                }

            } else if (actionEvent.getSource() == buttonDetails) {

                if (listViewContacts.getSelectionModel().getSelectedIndex() != -1){

                    Stage windowSeeDetails = new Stage();
                    FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("see-details-view.fxml"));


                    try {
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        seeDetailsController = loader.getController();
                        seeDetailsController.setMainController(MainController.this);
                        windowSeeDetails.setScene(scene);
                        windowSeeDetails.setTitle("Ver detalles.");
                        seeDetailsController.getContactDetails(listViewContacts.getSelectionModel().getSelectedItem());
                        windowSeeDetails.show();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    alertManager.warningAlert("Ningun contacto seleccionado"
                            ,"Selecciona un contacto para poder ver los detalles");
                }


            } else if (actionEvent.getSource() == buttonReload) {
                // no se si haria lo que quiero
                // queiro que si ha habido un error
                listViewContacts.setItems(observableListContacts);
                listViewContacts.refresh();
            }

        }
    }
}
