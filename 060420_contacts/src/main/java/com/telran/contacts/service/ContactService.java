package com.telran.contacts.service;

import com.telran.contacts.dto.Contact;
import com.telran.contacts.repository.IContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService { // performs business logic only
    /*
    Repository- is a storage of our Contact entities
    Repository: save new contact,change old contact,
    get contact by ID, delete contact by ID or by contact.
     */
    IContactRepository contactRepository;

    public ContactService(IContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void add(Contact contact) {
        contactRepository.add(contact);
    }

    public Contact get(int id) {
        return contactRepository.get(id);
    }

    public void edit(Contact contact) {
        contactRepository.edit(contact);
    }

    public boolean remove(Contact contact) {
        contactRepository.remove(contact);
        return false;
    }

    public void remove(int id) {
        contactRepository.remove(id);
    }

    public List<Contact> getAll() {
        return contactRepository.getAll();
    }
}
