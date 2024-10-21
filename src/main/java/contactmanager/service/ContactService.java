package contactmanager.service;

import contactmanager.model.Contact;

import java.util.List;

public interface ContactService {
    void addContact(Contact contact);
    void updateContact(Contact contact);
    void deleteContact(int id);
    Contact getContactById(int id);
    List<Contact> getAllContacts();
}
