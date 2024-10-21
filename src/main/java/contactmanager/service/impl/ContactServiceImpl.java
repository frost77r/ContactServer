package contactmanager.service.impl;

import contactmanager.service.ContactService;
import contactmanager.dao.ContactDao;
import contactmanager.dao.impl.ContactDaoImpl;
import contactmanager.model.Contact;

import java.util.List;

public class ContactServiceImpl implements ContactService {
    private ContactDao contactDao = new ContactDaoImpl();

    @Override
    public void addContact(Contact contact) {
        contactDao.addContact(contact);
    }

    @Override
    public void updateContact(Contact contact) {
        contactDao.updateContact(contact);
    }

    @Override
    public void deleteContact(int id) {
        contactDao.deleteContact(id);
    }

    @Override
    public Contact getContactById(int id) {
        return contactDao.getContactById(id);
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }
}
