package contactmanager.dao;

import contactmanager.model.Contact;

import java.util.List;

public interface ContactDao {
    void addContact(Contact contact);
    void updateContact(Contact contact);
    void deleteContact(int id);
    Contact getContactById(int id);
    List<Contact> getAllContacts();
}