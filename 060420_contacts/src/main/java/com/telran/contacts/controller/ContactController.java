package com.telran.contacts.controller;

import com.telran.contacts.dto.Contact;
import com.telran.contacts.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContactController {

    private ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // we create the page that will return a Form to create contacts
    //method -> get, address -> contact
    @GetMapping("contact")
    public String contactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact-form";
    }

    //method -> post, address -> contact
    @PostMapping("contact")
    public String add(@ModelAttribute Contact contact) {
//        System.out.println(contact.id);
//        System.out.println(contact.name);
//        System.out.println(contact.lastName);
//        System.out.println(contact.age);
//
       //service can save the contact
       contactService.add(contact);
        return "contacts";
    }

    @GetMapping("contact/{id}")
    public String getContact(@PathVariable int id, Model model) {
        Contact contact = contactService.get(id);
        model.addAttribute("contact", contact);
        return "contact";
    }

    //TODO: delete contact by id
    @GetMapping("contact/{id}")
    public String removeContactById(@ModelAttribute int id) {
        contactService.remove(id);
        return "contact";
    }

    //TODO: remove contact
    @GetMapping("remove contact")
    public String remove(@ModelAttribute Contact contact) {
        contactService.remove(contact);
        return "contact";
    }

    //TODO: edit contact
    @GetMapping("edit contact/{id}")
    public String editContactById(@PathVariable int id, Model model ) {
        Contact contact = contactService.get(id);
        model.addAttribute("contact", contact);
        return "contact";
    }

    //TODO: get list of contact
    @GetMapping("list of contacts")
    public String getAllContacts(Model model) {
        List<Contact> contacts = contactService.getAll();
        model.addAttribute("contacts", contacts);
        return "contacts";
    }
}
