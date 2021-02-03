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

        try {
            Contact contact = contactListRepository.findById(id).get();
            return contact;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "ID entered in invalid");
        }
    }

    //Get a contact by name
    @GetMapping
    @RequestMapping("/findByName/{name}")
    public Contact getByName(@PathVariable String name)
    {
        List<Contact> existingContacts = contactListRepository.findAll();

        for(Contact contact : existingContacts)
        {
            if(name.equals(contact.getContact_name()))
                return contact;
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No contact with given name found");
    }


}
