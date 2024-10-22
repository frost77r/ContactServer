package contactmanager.dao.impl;

import contactmanager.dao.ContactDao;
import contactmanager.model.Contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContactDaoImpl implements ContactDao {
    private static final String URL = "jdbc:mysql://localhost:3306/addressbook?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    @Override
    public void addContact(Contact contact) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO addressbook.contacts (name, phone, email) VALUES (?,?,?)")) {

            statement.setString(1, contact.getName());
            statement.setString(2, contact.getPhone());
            statement.setString(3, contact.getEmail());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateContact(Contact contact) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("UPDATE addressbook.contacts SET name =?, phone =?, email =? WHERE id =?")) {

            statement.setString(1, contact.getName());
            statement.setString(2, contact.getPhone());
            statement.setString(3, contact.getEmail());
            statement.setInt(4, contact.getId());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteContact(int id) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM addressbook.contacts WHERE id =?")) {

            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Contact getContactById(int id) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM addressbook.contacts WHERE id =?")) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Contact contact = new Contact();
                    contact.setId(resultSet.getInt("id"));
                    contact.setName(resultSet.getString("name"));
                    contact.setPhone(resultSet.getString("phone"));
                    contact.setEmail(resultSet.getString("email"));
                    return contact;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM addressbook.contacts");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setId(resultSet.getInt("id"));
                contact.setName(resultSet.getString("name"));
                contact.setPhone(resultSet.getString("phone"));
                contact.setEmail(resultSet.getString("email"));
                contacts.add(contact);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contacts;
    }
}
