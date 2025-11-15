package org.example.contactos.dao;

import javafx.scene.control.Alert;
import org.example.contactos.controller.EditContactController;
import org.example.contactos.controller.MainController;
import org.example.contactos.database.DBConnector;
import org.example.contactos.database.SchemeDB;
import org.example.contactos.model.Contact;
import org.example.contactos.util.AlertManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactoDAOImp implements ContactoDAO{

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    EditContactController editContactController;
    Contact contactToEditInBBDD;

    private AlertManager alertManager;
    String nombreOldContacto = "";

    // CONSTRUCTOR ////////////////
    public ContactoDAOImp (){
        connection = DBConnector.getConnection(); // !!!!!!!!!!!!!!!!!!!!!!! sino no esta conectada a la base de dadtos para inicialixar el obtacinCONTACTS
        alertManager = new AlertManager();
        editContactController = new EditContactController();
        contactToEditInBBDD = new Contact();
    }


    // INTERFACE METHODS ////////////////
    @Override
    public void addContact(Contact contact) throws SQLException {
        connection = DBConnector.getConnection();

        String query = "INSERT INTO %s (%s,%s,%s,%s) VALUES (?,?,?,?)";
        String queryFormateada = String.format(query, SchemeDB.TAB_CONTACTS,
                SchemeDB.COL_NAME,SchemeDB.COL_SURNAME,SchemeDB.COL_NUMBER,SchemeDB.COL_EMAIL);

        if (!connection.isClosed()){

            preparedStatement = connection.prepareStatement(queryFormateada);

            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2,contact.getSurname());
            preparedStatement.setString(3,contact.getPhoneNumber());
            preparedStatement.setString(4,contact.getEmail());

            preparedStatement.execute();

        } else {
            System.out.println("Conexion cerrada");
        }

        if (connection!= null) connection.close();
        if (preparedStatement!= null) preparedStatement.close();

    }
    @Override
    public List<Contact> obtainContact() {

        List<Contact> bbddContactsList = new ArrayList<>();


        try {

            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement("SELECT * FROM "+SchemeDB.TAB_CONTACTS);
                resultSet =preparedStatement.executeQuery();


                while (resultSet.next()){
                    String name =resultSet.getString(SchemeDB.COL_NAME);
                    String surname =resultSet.getString(SchemeDB.COL_SURNAME);
                    String phoneNumber =resultSet.getString(SchemeDB.COL_NUMBER);
                    String mail =resultSet.getString(SchemeDB.COL_EMAIL);

                    Contact contact = new Contact(name,surname,phoneNumber,mail);
                    bbddContactsList.add(contact);


                }

            } else {
                alertManager.errorAlert("No se encuentra conexion","No se pudo conectar a la base de datos, conexion cerrada");
                System.out.println("Conexion cerrada");
            }

        } catch (SQLException e) {
            alertManager.errorAlert("Error cargando datos","No se pudieron encontrar los datos");
            System.out.println(e.getMessage());
        }

        return bbddContactsList;
    }
    @Override
    public int deleteContact(String name) {

        connection = DBConnector.getConnection();
        String query = "DELETE FROM %s WHERE %s = ?";
        String queryFormateada = String.format(query,SchemeDB.TAB_CONTACTS,SchemeDB.COL_NAME);

        try {

            if (!connection.isClosed()){

                preparedStatement = connection.prepareStatement(queryFormateada);
                preparedStatement.setString(1,name);
                int affectedRows = preparedStatement.executeUpdate();
                System.out.println("Filas afectadas "+affectedRows);

            } else {
                System.out.println("Conexion cerrada");
            }

        }catch (SQLException e){
            alertManager.errorAlert("Error al eliminar"
                    , "No se pudo eliminar el contacto debido a un error en la base de datos");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return 0;
    }

    @Override
    public void editContact(Contact contact) {
        connection = DBConnector.getConnection();

        // SE CAMBIAN CONSTANTE MENTE A EL NUEVO NOMBRE QUE PONES EN EL NUEVO FORMULARIO PARA EDITAR !!!!!!!!!!!!!!!!!!!
        // estoy intentando cogerlo y ponerlo en el propio
        //query para update
        // UPDATE table_name
        //SET column1 = value1, column2 = value2, column3 = value3..
        //WHERE condition

        String query = "UPDATE %s SET %s=?,%s=?,%s=?,%s=? WHERE %s=?";
        String queryFormateada = String.format(query,SchemeDB.TAB_CONTACTS
                ,SchemeDB.COL_NAME,SchemeDB.COL_SURNAME,SchemeDB.COL_NUMBER,SchemeDB.COL_EMAIL
                ,SchemeDB.COL_NAME);
        try {
            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement(queryFormateada);

                preparedStatement.setString(1,contact.getName());
                preparedStatement.setString(2,contact.getSurname());
                preparedStatement.setString(3,contact.getPhoneNumber());
                preparedStatement.setString(4,nombreOldContacto);
                // creo que nos e cambia porque se esta cambiando el nombre a la vez y no pilla exactamente
                // el antiguo nombre ara poder ediatrlo con el nuevo nombre

                preparedStatement.setString(5,contact.getName());
                preparedStatement.execute();
                int filasAfectadas = preparedStatement.executeUpdate();

                if (filasAfectadas == 0){
                    alertManager.errorAlert("No se edito correctamente"
                            ,String.format("No se pudo editar %s porque algun campo dio en la base de datos",nombreOldContacto));
                    System.out.println("Filas afectadas: "+filasAfectadas);
                } else if (filasAfectadas >=1) {
                    alertManager.informationAlert("Se edito con exito el contacto"
                            ,String.format("El contacto con nombre %s se edito conrrectamente",this.contactToEditInBBDD.getName()));
                    System.out.println("Filas afectadas: "+filasAfectadas);

                }

            } else {
                System.out.println("conexion cerrada");
            }
        } catch (SQLException e) {
            alertManager.errorAlert("Error en la base de datos"
                    ,"No se pudo editar el contacto debido a un error en la base de deatos");
            System.out.println(e.getMessage());
        }

    }

    public void getContactToEditInBBDD (Contact contact){
        if (contact != null){
            this.nombreOldContacto = contact.getName();
            this.contactToEditInBBDD = contact;
        } else {
            System.out.println("El contacto NO puede ser nulo ");
        }
    }


}
