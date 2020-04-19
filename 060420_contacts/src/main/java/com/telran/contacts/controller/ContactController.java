package com.telran.contacts.controller;

import com.telran.contacts.dto.Contact;
import com.telran.contacts.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ContactController {

    private ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // we create the page that will return a Form to create contacts
    //method -> get, address -> contact

    //    @GetMapping
    //    public ModelAndView home(){
    //        return new ModelAndView("");
    //    }
    @GetMapping("/contact")
    public String contactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact-form";
    }

    //method -> post, address -> contact
    @PostMapping("/contact")
    public String addContact(@ModelAttribute Contact contact) {
        if (contact.getId() == 0)
            contactService.add(contact);
        else
            contactService.edit(contact);
        return "redirect:/";
    }

//        System.out.println(contact.id);
//        System.out.println(contact.name);
//        System.out.println(contact.lastName);
//        System.out.println(contact.age);

    @GetMapping("/")
    public String home() {
        return "forward:/list of contacts";

    }

    @GetMapping("/contact/{id}")
    public String getContact(@PathVariable int id, Model model) {
        Contact contact = contactService.get(id);
        model.addAttribute("contact", contact);
        return "contact";
    }

    @DeleteMapping("/remove/contact/{id}")
    public String removeContactById(@PathVariable int id) {
        contactService.remove(id);
        return "redirect:/";
    }

    @DeleteMapping("/remove contact")
    public String remove(@ModelAttribute Contact contact) {
        contactService.remove(contact);
        return "contact";
    }

    @GetMapping("edit contact/{id}")
    public String editContactById(@PathVariable int id, Model model) {
        Contact contact = contactService.get(id);
        model.addAttribute("contact", contact);
        return "contact-form";
    }

    @GetMapping("/list of contacts") //page with contacts
    public String getAllContacts(Model model) {
        List<Contact> contacts = contactService.getAll();
        model.addAttribute("contacts", contacts);
        return "contacts";
    }
}
