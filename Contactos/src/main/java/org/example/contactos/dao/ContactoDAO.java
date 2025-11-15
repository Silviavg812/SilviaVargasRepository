package org.example.contactos.dao;

import org.example.contactos.model.Contact;

import java.sql.SQLException;
import java.util.List;

public interface ContactoDAO {
    void addContact(Contact contact) throws SQLException;
    void editContact (Contact contact);
    List<Contact> obtainContact();
    int deleteContact(String name);
}
