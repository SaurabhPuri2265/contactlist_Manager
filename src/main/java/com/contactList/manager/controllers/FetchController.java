package com.contactList.manager.controllers;

import com.contactList.manager.models.Contact;
import com.contactList.manager.repositories.ContactListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping({"/contacts","/contacts/"})
public class FetchController {

    @Autowired
    private ContactListRepository contactListRepository;

    //Get all the contacts in the contact list
    @GetMapping
    public List<Contact> list() {
        return contactListRepository.findAll();
    }


    //Get a contact by id
    @GetMapping
    @RequestMapping("/findById/{id}")
    public Contact getById(@PathVariable Integer id) {

        Contact contact = contactListRepository.findById(id).get();
        return contact;
    }

    //Get a contact by name
    @GetMapping
    @RequestMapping("/findByName/{name}")
    public Contact getByName(@PathVariable String name)
    {
        List<Contact> existingContacts = contactListRepository.findAll();
        System.out.println(existingContacts);

        for(Contact contact : existingContacts)
        {
            if(name.equals(contact.getContact_name()))
                return contact;
        }
        return null;
    }


}
